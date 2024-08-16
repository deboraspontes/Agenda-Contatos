package ada.tech.agenda.operacoes;

import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;
import java.util.Scanner;

public class DetalharContato {
    public void executar(Contato[] contatos, Scanner entrada) {
        String telefone = Util.ler(entrada, "Digite o telefone do contato").trim();

        boolean contatoEncontrado= false;

        //Percorre a lista de contatos
        for (Contato contato : contatos) {
            if (contato.getTelefone().trim().equals(telefone)) {
                System.out.println(contato);
                contatoEncontrado = true;
                return;
            }
        }

        if(!contatoEncontrado) {
            Util.erro("Contato não encontrado");
        }
    }
}
