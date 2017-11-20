package dao;

import anotacao.RecuperaConjunto;
import anotacao.RecuperaUm;
import excecao.ObjetoNaoEncontradoException;
import modelo.Time;

import java.util.Set;

public interface TimeDAO extends DaoGenerico<Time, Long> {

    @RecuperaUm
    Time recuperaUmTime(long numero) throws ObjetoNaoEncontradoException;

    @RecuperaUm
    Time recuperaUmTimeEMembros(long numero) throws ObjetoNaoEncontradoException;

    @RecuperaConjunto
    Set<Time> recuperaTimesEMembros();
}
