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



    // Detalhar contato


    //Editar contato



    // Remoção de contatos

}

