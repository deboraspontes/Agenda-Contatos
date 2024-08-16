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

        String telefone = Util.ler(entrada, "Digite o telefone: ");
        Util.contatoExiste(contatos, telefone);

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
        if (contato != null) {
            Util.escrever("Nome: " + contato.getNome() + contato.getSobreNome());
            Util.escrever("Telefone: " + contato.getTelefone());
            Util.escrever("Email: " + contato.getEmail());

        } else {
            throw new ContatoNaoEncontradoException();
        }
    }


    //Editar contato

    private void editarContato() throws ContatoNaoEncontradoException, TelefoneExistenteException {

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

        String novoNome = Util.ler(entrada, "Digite o novo nome: ");
        String novoTelefone = Util.ler(entrada, "\nDigite o novo telefone: ");

        if (!novoNome.isBlank()) {
            contato.setNome(novoNome);
        }

        if (!novoTelefone.isBlank() && !novoTelefone.equals(telefone)) {
            for (int i = 0; i < this.totalContatos; i++) {
                if (contatos[i].getTelefone().equals(novoTelefone)) {
                    throw new TelefoneExistenteException();
                }
            }
            contato.setTelefone(novoTelefone);
        }

        contatos[indexToEdit] = contato;
        Util.escrever("Contato atualizado com sucesso.");
    }

    // Remoção de contatos
    private void removerContato() throws Exception {
        String telefone = Util.ler(entrada, "Digite o telefone do contato para remover: ");
        int indexToRemove = -1;

        for (int i = 0; i < contatos.length; i++) {
            if(contatos[i].getTelefone().equals(telefone)) {
                System.out.println("aqui" + contatos[i].getTelefone());
                indexToRemove = i;
                break;
            } else {
                throw new NaoExisteContatoException();
            }
        }

        Contato[] contatosAtualizados = new Contato[contatos.length - 1];
        for (int i = 0, j = 0; i < contatos.length; i++) {
                if (indexToRemove == i){
                    continue;
                };
                contatosAtualizados[j++] = contatos[i];
                contatos = contatosAtualizados;
            }

            Util.escrever("Contato removido!");
        }


//        if (indexToRemove != -1) {
//            for (int i = indexToRemove; i < totalContatos - 1; i++) {
//                contatos[i] = contatos[i + 1];
//            }
//            contatos[totalContatos--] = null;
//            for (int i = 0; i < contatos.length; i++){
//                System.out.println(contatos[i].toString());
//            }

        }

