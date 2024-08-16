package ada.tech.agenda.controlador;

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
                        editarContato();
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
                Util.erro("Erro: " + e.getMessage());
            }

        } while (opcao != 5);
    }

    // Adicionar contato
    public void adicionarContato() throws Exception {
        String telefone;
        boolean isValidPhone = false;
        do {
            telefone = Util.ler(entrada, "Digite o telefone: ");
            if (telefone.matches("\\d+")) {
                isValidPhone = true;
            } else {
                Util.erro("O telefone deve conter apenas números. Tente novamente.");
            }
        } while (!isValidPhone);

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

    private void editarContato() throws Exception {


        String telefone = Util.ler(entrada, "Digite o telefone do contato que deseja editar: ");
        Contato contato = null;
        int indexToEdit = -1;


        for (int i = 0; i < this.totalContatos; i++) {
            if (contatos[i].getTelefone().equals(telefone)) {
                contato = contatos[i];
                indexToEdit = i;
                break;
            }
        }


        if (contato == null) {
            throw new ContatoNaoEncontradoException();
        }


        String opcoesEdicao = """
           >>>> Editar <<<<
           1 - Nome
           2 - Sobrenome
           3 - Telefone
           4 - Email
           """;
        Util.escrever(opcoesEdicao);
        int opcao = Integer.parseInt(Util.ler(entrada, "Digite a opção que desejada para edição"));

        switch (opcao) {
            case 1:
                String novoNome = Util.ler(entrada, "Digite o novo nome: ");
                if (!novoNome.isBlank()) {
                    contato.setNome(novoNome);
                }
                break;
            case 2:
                String novoSobrenome = Util.ler(entrada, "\nDigite o novo sobrenome: ");
                if (!novoSobrenome.isBlank()) {
                    contato.setSobreNome(novoSobrenome);
                }
                break;
            case 3:
                String novoTelefone = Util.ler(entrada, "\nDigite o novo telefone: ");
                for (int i = 0; i < this.totalContatos; i++) {
                    if (contatos[i].getTelefone().equals((novoTelefone))) {
                        throw new TelefoneExistenteException();
                    }
                }
                if (!novoTelefone.isBlank()) {
                    contato.setTelefone(novoTelefone);
                }
                break;
            case 4:
                String novoEmail = Util.ler(entrada, "\nDigite o novo email: ");
                if (!novoEmail.isBlank()) {
                    contato.setEmail(novoEmail);
                }
                break;
            default:
                Util.erro("Opão inváloda");
                break;


        }

        contatos[indexToEdit] = contato;
        Util.escrever("Contato atualizado com sucesso.");
    }

    // Remoção de contatos
    private void removerContato() throws Exception {
        String telefone = Util.ler(entrada, "Digite o telefone do contato para remover: ");
        int indexToRemove = -1;

        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i].getTelefone().equals(telefone)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new NaoExisteContatoException();
        }

        Contato[] contatosAtualizados = new Contato[contatos.length - 1];
        for (int i = 0, j = 0; i < contatos.length; i++) {
            if (i == indexToRemove) {
                continue;
            }
            contatosAtualizados[j++] = contatos[i];
        }
        contatos = contatosAtualizados;
        Util.escrever("Contato removido!");
    }

}

