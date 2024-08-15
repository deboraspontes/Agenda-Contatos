package ada.tech.agenda.exception;

import ada.tech.agenda.modelo.Contato;

public class EmailInvalidoException extends Exception {

    public EmailInvalidoException() {
        super("Formato de email inválido.");
    }

}