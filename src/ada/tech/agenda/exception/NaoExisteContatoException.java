package ada.tech.agenda.exception;

public class NaoExisteContatoException extends Exception {

    public NaoExisteContatoException() {
        super("Nao há um contato com este número. Tente novamente!");
    }

}
