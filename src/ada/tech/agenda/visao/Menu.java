package ada.tech.agenda.visao;

import ada.tech.agenda.exception.ContatoNaoEncontradoException;
import ada.tech.agenda.exception.NaoExisteContatoException;
import ada.tech.agenda.exception.TelefoneExistenteException;
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
            if (totalContatos > 0) {
                    for (int i = 0; i < totalContatos; i++) {
                        if (contatos[i] != null) {
                            int id = contatos[i].getId();
                            String nome = contatos[i].getNome().split(" ")[0];
                            String sobrenome = contatos[i].getSobreNome();
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
                            """, id, nome, sobrenome, telefone, email);
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
            opcao = Integer.parseInt(Util.ler(entrada, "Digite a opcao:"));
            try {

                switch (opcao) {
                    case 1:
                        boolean isAddAnotherTrue = true;
                        while (isAddAnotherTrue) {
                            adicionarContato();
                            String option = Util.ler(entrada, "Deseja adicionar outro contato? (S = Sim, N = Não): ");
                            if (option.equalsIgnoreCase("n")) {
                                isAddAnotherTrue = false;
                            }
                        }
                        break;

                    case 2:
                        detalharContato();
                        break;

                    case 3:
                        break;

                    case 4:
                        removerContato();
                        break;

                    case 5:
                        Util.escrever("Saindo...");
                        break;

                    default:
                        Util.erro("Opção inválida");
                        break;
                }
            } catch (Exception e) {
                Util.erro(STR."Erro: \{e.getMessage()}");
            }

        } while (opcao != 5);
    }

    // Adicionar contato
    public void adicionarContato() throws Exception {

        String telefone = Util.ler(entrada, "Digite o telefone: ");
        Util.contatoExiste(this.contatos, telefone);

        String primeiroNome = Util.ler(entrada, "Digite o nome do contato: ");
        if (primeiroNome.contains(" ")) {
            primeiroNome = primeiroNome.split(" ")[0];
        }

        String sobrenome = Util.ler(entrada, "Digite o sobrenome do contato: ");
        if (sobrenome.contains(" ")) {
            if(sobrenome.split(" ")[0].length() > 3){
                sobrenome = sobrenome.split(" ")[0];
            };
        }

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

        Contato contato = null;

        for (int i=0; i<totalContatos; i++) {
            if (contatos[i].getTelefone().equals(telefone)){
                contato=contatos[i];
                break;
            }
        }
        if (contato == null) {
            Util.escrever(STR."Nome:\{contato.getNome()}");
            Util.escrever(STR."Telefone\{contato.getTelefone()}");
            Util.escrever(STR."Email\{contato.getEmail()}");

        } else {
            throw new ContatoNaoEncontradoException();
        }
    }


    //Editar contato


    // Remoção de contatos
    private void removerContato() throws Exception {
        String telefone = Util.ler(entrada, "Digite o telefone do contato para remover: ");
        int indexToRemove = -1;

        for (int i = 0; i < totalContatos; i++) {
            if (contatos[i].getTelefone().equals(telefone)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < totalContatos - 1; i++) {
                contatos[i] = contatos[i + 1];
            }
            contatos[--totalContatos] = null;
            Util.escrever("Contato removido!");
        } else {
            throw new NaoExisteContatoException("Não existe contato com o telefone fornecido: " + telefone + ".");
        }
    }
}
