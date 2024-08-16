package ada.tech.agenda.visao;

import ada.tech.agenda.operacoes.*;
import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;
import java.util.Scanner;

public class Menu {

    private final Scanner entrada;
    private Contato[] contatos;
    private int nextId;
    private int totalContatos;

    public Menu() {
        this.entrada = new Scanner(System.in);
        this.contatos = new Contato[0];
        this.nextId = 1;
        this.totalContatos = 0;
    }

    public void iniciar() {

        int opcao = 0;
        String lista = "";

        do {
            lista = "";
            if (contatos.length > 0) {
                    for (int i = 0; i < contatos.length; i++) {
                        if (contatos[i] != null) {
                            int id = contatos[i].getId();
                            String primeiroNome = contatos[i].getNome().split(" ")[0];
                            String[] sobrenomes = contatos[i].getSobreNome().split(" ");
                            String ultimoSobrenome = sobrenomes[sobrenomes.length - 1];
                            String telefone = contatos[i].getTelefone();
                            String email = contatos[i].getEmail();

                    lista += String.format(
                            """
                            *___________________________________________________________________*
                            | Id:         %s
                            | Nome:       %s
                            | Sobrenome:  %s
                            | Telefone:   %s
                            | E-mail:     %s
                            *___________________________________________________________________*
                            """, id, primeiroNome, ultimoSobrenome, telefone, email);
                        }
                }
            } else {
                lista = "Nenhum contato disponível.";
            }


            String opcoes = """

                    ##################
                    ##### AGENDA #####
                    ##################

                    >>>> Contatos <<<<
                    %s
                    
                    >>>> Menu <<<<
                    1 - Adicionar Contato
                    2 - Detalhar Contato
                    3 - Editar Contato
                    4 - Remover Contato
                    5 - Sair

                    """.formatted(lista);

            Util.escrever(opcoes);
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    opcao = Integer.parseInt(Util.ler(entrada, "Digite a opcao:"));

                    if (opcao < 1 || opcao > 5) {
                        Util.erro("Opção inválida! Selecione as opções entre 1 e 5.");
                    } else {
                        entradaValida = true;
                    }
                } catch (NumberFormatException e) {
                    Util.erro("Entrada inválida! Por favor, insira um número (1-5).");
                }
            }

            try {
                switch (opcao) {
                    case 1:
                        AdicionarContato adicionar = new AdicionarContato();
                        contatos = adicionar.executar(contatos, entrada, nextId);
                        nextId++;
                        totalContatos++;
                        break;

                    case 2:

                        DetalharContato detalhar = new DetalharContato();
                        detalhar.executar(contatos, entrada);
                        boolean isDetalharOutroTrue = true;
                        while (isDetalharOutroTrue) {
                            detalharContato();
                            String option = Util.ler(entrada, "Deseja detalhar outro contato? (S = Sim, N = Não): ");
                            if (option.equalsIgnoreCase("n")) {
                                isDetalharOutroTrue = false;
                            }
                        }

                        break;

                    case 3:
                        EditarContato editar = new EditarContato();
                        editar.executar(contatos, entrada);
                        break;

                    case 4:
                        RemoverContato remover = new RemoverContato();
                        contatos = remover.executar(contatos, entrada);
                        totalContatos--;
                        break;

                    case 5:
                        Sair sair = new Sair();
                        sair.executar();
                        break;

                    default:
                        Util.erro("Opção inválida");
                        break;
                }
            } catch (Exception e) {
                Util.erro("Erro: " + e.getMessage());
            }

        } while (opcao != 5);
    }

    // Adicionar contato


    public void adicionarContato() throws Exception {

        String telefone = Util.ler(entrada, "Digite o telefone: ");
        Util.contatoExiste(contatos, telefone);

        String primeiroNome = Util.ler(entrada, "Digite o nome do contato: ");

        String sobrenome = Util.ler(entrada, "Digite o sobrenome do contato: ");

        String email = Util.ler(entrada, "Digite o email: ");
        boolean isEmailValid = false;
        while(!isEmailValid) {
            if(email.contains("@")) {
                isEmailValid = true;
            } else{
                 System.err.println("Formato de email inválido. Tente novamente!");
                 email = Util.ler(entrada, "Digite novamente o email: ");
            }
        }

        Contato[] contatosAtualizados = new Contato[contatos.length + 1];
        for (int i = 0; i < contatos.length; i++) {
            contatosAtualizados[i] = contatos[i];
        }

        Contato novoContato = new Contato(nextId, primeiroNome, sobrenome, telefone, email);
        contatosAtualizados[contatos.length] = novoContato;
        contatos = contatosAtualizados;
        nextId++;
        totalContatos++;
        Util.escrever("Contato adicionado com sucesso!");
    }





    // Detalhar contato


    private void detalharContato() throws Exception {
        String telefone = Util.ler(entrada, "Digite o telefone do contato");


        for (int i=0; i<totalContatos; i++) {
            if (contatos[i].getTelefone().equals(telefone)){
                System.out.print(contatos[i]);
                break;
            }
        }
    }



    //Editar contato



    // Remoção de contatos

}

