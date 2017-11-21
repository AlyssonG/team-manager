package excecao;

import anotacao.ConstraintViolada;
import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
@ConstraintViolada(nome = "MEMBRO_UN")
public class MembroNaoEncontradoException extends Exception {
    private final static long serialVersionUID = 1;

    public MembroNaoEncontradoException() {
        super();
    }

    public MembroNaoEncontradoException(String msg) {
        super(msg);
    }
}	