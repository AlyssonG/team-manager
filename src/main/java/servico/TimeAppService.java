package servico;

import dao.TimeDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public class TimeAppService {
    private TimeDAO timeDAO = null;

    @Autowired
    public void setTimeDAO(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    @Transactional
    public long inclui(Time umTime) {
        Time time = timeDAO. inclui(umTime);
        return time.getId();
    }

    @Transactional
    public void altera(Time umTime) throws TimeNaoEncontradoException {
        try {
            timeDAO.altera(umTime);
        } catch (ObjetoNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time não encontrado");
        }
    }

    @Transactional
    public void exclui(Time umTime)
            throws TimeNaoEncontradoException {
        try {
            Time time = null;//timeDAO.recuperaUmMembroETimes(umTime.getId());

            if (time.getMembros().size() > 0) {
                throw new TimeNaoEncontradoException("Este time possui lances e não pode ser removido");
            }

            timeDAO.exclui(time);
        } catch (ObjetoNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time não encontrado");
        }
    }

    public Time recuperaUmTime(long numero)
            throws TimeNaoEncontradoException {
        try {
            return timeDAO.recuperaUmTime(numero);
        } catch (ObjetoNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time não encontrado");
        }
    }

    public Time recuperaUmTimeEMembros(long numero)
            throws TimeNaoEncontradoException {
        try {
            return timeDAO.recuperaUmTime(numero);
        } catch (ObjetoNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time não encontrado");
        }
    }

    public Set<Time> recuperaTimesEMembros() {
        return timeDAO.recuperaTimesEMembros();
    }

}
