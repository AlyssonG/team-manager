package dao;

import excecao.ObjetoNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * A interface GenericDao b�sica com os m�todos CRUD. Os m�todos
 * de busca s�o adicionados por heran�a de interface.
 */
public interface DaoGenerico<T, PK extends Serializable> {
    @Transactional
    T inclui(T obj);

    @Transactional
    void altera(T obj) throws ObjetoNaoEncontradoException;

    @Transactional
    void exclui(T obj) throws ObjetoNaoEncontradoException;

    T getPorId(PK id) throws ObjetoNaoEncontradoException;

    T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;
}
