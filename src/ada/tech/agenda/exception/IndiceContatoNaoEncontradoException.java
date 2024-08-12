package ada.tech.agenda.exception;

public class IndiceContatoNaoEncontradoException extends Exception {

    public IndiceContatoNaoEncontradoException() {
        super("Nenhum indice de contato na agenda");
    }

}
