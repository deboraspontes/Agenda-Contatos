package ada.tech.agenda.exception;

public class NaoExisteContatoException extends Exception {

    public NaoExisteContatoException(String s) {
        super("Nao a contatos existentes");
    }

}
