package excecao;

import anotacao.ConstraintViolada;
import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class ExecucaoDeMetodoSemARespectivaPermissaoException extends Exception {
    private final static long serialVersionUID = 1;

    public ExecucaoDeMetodoSemARespectivaPermissaoException() {
        super();
    }

    public ExecucaoDeMetodoSemARespectivaPermissaoException(String msg) {
        super(msg);
    }
}	