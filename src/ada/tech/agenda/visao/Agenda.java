package ada.tech.agenda.visao;

import ada.tech.agenda.controlador.Controlador;
import ada.tech.agenda.exception.TelefoneExistenteException;
import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;

import java.util.Scanner;

public class Agenda {

    private final Scanner entrada;
    private final Controlador controlador;

    public Agenda(Scanner entrada) {
        this.entrada = entrada;
        this.controlador = new Controlador();

    /*    Contato contato = new Contato("Alex", "Araujo", "1111", "alex@ada.tech");

        try {
            controlador.adicionarContato(contato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

    }

    public void criarContato() {

        String resposta;
        do {
            try {
                String nome = Util.ler(entrada, "Digite o nome do contato");
                String sobreNome = Util.ler(entrada, "Digite o sobrenome do contato");
                String telefone = Util.ler(entrada, "Digite o numero do telefone do contato");
                String email = Util.ler(entrada, "Digite o e-mail do contato");
                controlador.adicionarContato(new Contato(nome, sobreNome, telefone, email));
            } catch (Exception e) {
                Util.erro(e.getMessage());
            }
            resposta = Util.ler(entrada, "Deseja continuar? S - Sim, N - Nao");
        } while ("S".equalsIgnoreCase(resposta));

    }


    public void removerContato() {
        String resposta;
        do {
            String telefoneARemover = Util.ler(entrada,"Digite o numero do telefone do contato");
            System.out.println(this.controlador.removerContatoPorTelefone(telefoneARemover));
            resposta = Util.ler(entrada, "Deseja continuar? S - Sim, N - Nao");
        } while ("S".equalsIgnoreCase(resposta));

    }

    public void detalharContato() {
        String resposta;
        do {
            String telefoneABuscar = Util.ler(entrada,"Digite o numero do telefone do contato");
            mostrarContatoPorTelefone(telefoneABuscar);
            resposta = Util.ler(entrada, "Deseja continuar? S - Sim, N - Nao");
        } while ("S".equalsIgnoreCase(resposta));
    }

    public String mostrarContatos() {
        try {
            this.controlador.verificarTamanho();
            return montarAgenda();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void editarContato() {
        String resposta;
        do {
            try {
                String telefoneABuscar = Util.ler(entrada,"Digite o numero do telefone do contato");
                Contato contato = this.controlador.buscarContatoPorTelefone(telefoneABuscar);
                Util.escrever(contato.toString());

                String nome = Util.ler(entrada, "Digite o nome do contato");
                String sobreNome = Util.ler(entrada,"Digite o sobrenome do contato");
                String telefone = Util.ler(entrada,"Digite o numero do telefone do contato");
                String email = Util.ler(entrada, "Digite o e-mail do contato");

                Contato contatoEditado = new Contato(nome, sobreNome, telefone, email);
                Util.escrever(this.controlador.atualizarContatoPorTelefone(contatoEditado, telefoneABuscar));
            } catch (Exception e) {
                Util.erro(e.getMessage());
            }
            resposta = Util.ler(entrada, "Deseja continuar? S - Sim, N - Nao");
        } while ("S".equalsIgnoreCase(resposta));

    }

    private String montarAgenda() {
        String listagem = "Id | Nome        | Telefone | E-mail \n";
        for (int i = 0; i < this.controlador.getContatos().length; i++) {
            Contato contato = this.controlador.getContatos()[i];
            listagem += STR."""
                    \{ i+1 }  | \{ contato.getNome() } \{ contato.getSobreNome() } | \{ contato.getTelefone() } | \{ contato.getEmail() }
                    """;
        }
        return listagem;
    }

    private void mostrarContatoPorTelefone(String telefone) {
        try {
            Contato contato = this.controlador.buscarContatoPorTelefone(telefone);
            Util.escrever(contato.toString());
        } catch (Exception e) {
            Util.erro(e.getMessage());
        }
    }

}
