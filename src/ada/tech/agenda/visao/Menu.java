package ada.tech.agenda.visao;

import ada.tech.agenda.exception.ContatoNaoEncontradoException;
import ada.tech.agenda.exception.EmailInvalidoException;
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
        this.contatos = new Contato[100];
        this.nextId = 1;
        this.totalContatos = 0;
    }

    public void iniciar() {

        int opcao = 0;

        do {

            String lista = "";

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

                    """.formatted(lista.toString());

            Util.escrever(opcoes);
            opcao = Integer.parseInt(Util.ler(entrada, "Digite a opcao:"));
            try {

                switch (opcao) {
                    case 1:
                        adicionarContato();
                        break;

                    case 2:
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
            } catch (TelefoneExistenteException | ContatoNaoEncontradoException | NaoExisteContatoException |
                     EmailInvalidoException e) {
                Util.erro("Erro: " + e.getMessage());
            }

        } while (opcao != 5);
    }

    // Adicionar contato
    private void adicionarContato() throws TelefoneExistenteException, EmailInvalidoException {
        String telefone = Util.ler(entrada, "Digite o telefone: ");
        Util.contatoExiste(this.contatos, telefone);

        String primeiroNome = Util.ler(entrada, "Digite o nome do contato: ");
        if (primeiroNome.contains(" ")) {
            primeiroNome = primeiroNome.split(" ")[0];
        }

        String sobrenome = Util.ler(entrada, "Digite o sobrenome do contato: ");
        if (sobrenome.contains(" ")) {
            sobrenome = sobrenome.split(" ")[0];
        }

        String email = Util.ler(entrada, "Digite o email: ");
        if (!email.contains("@")) {
            throw new EmailInvalidoException();
        }

        Contato novoContato = new Contato(nextId, primeiroNome, sobrenome, telefone, email);
        this.contatos[this.totalContatos++] = novoContato;
        nextId++;

        Util.escrever("Contato adicionado com sucesso!");
    }

    // Detalhar contato

    // Editar contato

    // Remoção de contatos
    private void removerContato() throws NaoExisteContatoException {
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
