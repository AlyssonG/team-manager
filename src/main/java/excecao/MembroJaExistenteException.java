package excecao;

public class MembroJaExistenteException extends Throwable {
    private final static long serialVersionUID = 1;

    public MembroJaExistenteException() {
        super();
    }

    public MembroJaExistenteException(String msg) {
        super(msg);
    }
}
