package ada.tech.agenda.operacoes;

import ada.tech.agenda.exception.NaoExisteContatoException;
import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;

import java.util.Scanner;

public class RemoverContato {
    public Contato[] executar(Contato[] contatos, Scanner entrada) throws NaoExisteContatoException {
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
        // Cria novo array para armazenar os contatos atualizados, exceto o removido
        Contato[] contatosAtualizados = new Contato[contatos.length - 1];
        int j = 0;

        // Preenche o novo array com contatos que não foram removidos
        for (int i = 0; i < contatos.length; i++) {
            if (i != indexToRemove) {
                contatosAtualizados [j] = contatos[i];
                j++;
            }
        }
        // Atualiza os IDs dos contatos restantes
        for (int i =0; i < contatosAtualizados.length; i++) {
            contatosAtualizados[i].setId(i+1);
        }

        Util.escrever("Contato removido!");

        return contatosAtualizados;
    }
}
