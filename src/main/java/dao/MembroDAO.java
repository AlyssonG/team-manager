package dao;

import anotacao.RecuperaLista;
import anotacao.RecuperaUm;
import excecao.ObjetoNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Membro;

import java.util.List;

public interface MembroDAO extends DaoGenerico<Membro, Long> {
    @RecuperaUm
    Membro recuperaUmMembro(Long id) throws ObjetoNaoEncontradoException,
            TimeNaoEncontradoException;

    @RecuperaLista
    List<Membro> recuperaMembros();
}
