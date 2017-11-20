package excecao;

import anotacao.ConstraintViolada;
import anotacao.ExcecaoDeAplicacao;

@ConstraintViolada(nome = "MEMBRO_UN")
public class MembroJaExistenteException extends Throwable {
    private final static long serialVersionUID = 1;

    public MembroJaExistenteException() {
        super();
    }

    public MembroJaExistenteException(String msg) {
        super(msg);
    }
}
