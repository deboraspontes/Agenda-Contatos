package ada.tech.agenda.utilitario;

import ada.tech.agenda.exception.TelefoneExistenteException;
import ada.tech.agenda.modelo.Contato;

import java.util.Scanner;

public class Util {

    public static void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public static void erro(String mensagem) {
        System.err.println(mensagem);
    }

    public static String ler(Scanner entrada, String questao) {
        System.out.println(questao);
        return entrada.nextLine();
    }

    public static void contatoExiste(Contato[] contatos, String telefone) throws TelefoneExistenteException {
        for (Contato contato : contatos) {
            if (contato != null && contato.getTelefone().equals(telefone)) {
                throw new TelefoneExistenteException("O telefone já está cadastrado.");
            }
        }
    }
}
