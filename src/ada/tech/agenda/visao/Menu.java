package ada.tech.agenda.visao;

import ada.tech.agenda.controlador.Controlador;
import ada.tech.agenda.utilitario.Util;

import java.util.Scanner;

public class Menu {

    private final Agenda agenda;
    private final Scanner entrada;

    public Menu() {
        this.entrada = new Scanner(System.in);
        this.agenda = new Agenda(this.entrada);
    }

    public void iniciar() {

            int opcao = 0;

            do {

                String lista = agenda.mostrarContatos();

                String opcoes = STR."""

                    ##################
                    ##### AGENDA #####
                    ##################

                    >>>> Contatos <<<<
                    \{lista}

                    >>>> Menu <<<<
                    1 - Adicionar Contato
                    2 - Detalhar Contato
                    3 - Editar Contato
                    4 - Remover Contato
                    5 - Sair

                    """;

                Util.escrever(opcoes);
                opcao =  Integer.parseInt(Util.ler(entrada, "Digite a opcao:"));

                switch (opcao){
                    case 1:
                        agenda.criarContato();
                        break;

                    case 2:
                        agenda.detalharContato();
                        break;

                    case 3:
                        agenda.editarContato();
                        break;

                    case 4:
                        agenda.removerContato();
                        break;

                    default:
                        Util.erro("Opcao invalida");
                        break;
                }

            } while(opcao <= 4);

    }

}