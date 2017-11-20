package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class TimeNaoEncontradoException extends Exception {
    private final static long serialVersionUID = 1;

    public TimeNaoEncontradoException() {
        super();
    }

    public TimeNaoEncontradoException(String msg) {
        super(msg);
    }
}	