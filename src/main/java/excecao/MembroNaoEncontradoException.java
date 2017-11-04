package excecao;

public class MembroNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public MembroNaoEncontradoException()
	{	super();
	}

	public MembroNaoEncontradoException(String msg)
	{	super(msg);
	}
}	