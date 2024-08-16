package ada.tech.agenda.operacoes;

import ada.tech.agenda.exception.ContatoNaoEncontradoException;
import ada.tech.agenda.exception.TelefoneExistenteException;
import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;

import java.util.Scanner;

public class EditarContato {
    public void executar (Contato[] contatos, Scanner entrada) throws ContatoNaoEncontradoException, TelefoneExistenteException{
        String telefone = Util.ler(entrada, "Digite o telefone do contato que deseja editar: ");
        Contato contato = null;
        int indexToEdit = -1;

        for (int i = 0; i < contatos.length; i++) {
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
        String novoSobrenome = Util.ler(entrada, "\nDigite o novo sobrenome: ");
        String novoTelefone = Util.ler(entrada, "\nDigite o novo telefone: ");
        String novoEmail = Util.ler(entrada, "\nDigite o novo email: ");

        for (Contato c : contatos) {
            if (c.getTelefone().equals(novoTelefone)) {
                throw new TelefoneExistenteException();
            }
        }

        if (!novoNome.isBlank()) {
            contato.setNome(novoNome);
        }

        if (!novoTelefone.isBlank()) {

            contato.setTelefone(novoTelefone);
        }

        if (!novoSobrenome.isBlank()) {
            contato.setSobreNome(novoSobrenome);
        }

        if (!novoEmail.isBlank()) {
            contato.setEmail(novoEmail);
        }

        contatos[indexToEdit] = contato;
        Util.escrever("Contato atualizado com sucesso.");
    }
}
