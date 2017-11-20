package servico;

import dao.MembroDAO;
import dao.TimeDAO;
import excecao.MembroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Membro;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MembroAppService {

    private TimeDAO timeDAO = null;
    private MembroDAO membroDAO = null;

    public void setTimeDAO(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    public void setMembroDAO(MembroDAO membroDAO) {
        this.membroDAO = membroDAO;
    }

    @Transactional
    public long inclui(Membro umMembro) throws TimeNaoEncontradoException {
        //incluir regra de membros em um só time
        //membroDAO.find(membro)
        //se não achar, inclui
        Membro membro = membroDAO.inclui(umMembro);
        return membro.getId();
    }

    @Transactional
    public void exclui(Membro umMembro) throws MembroNaoEncontradoException {
        try {
            membroDAO.exclui(umMembro);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }
    }

    public Membro recuperaUmMembro(long numero)
            throws MembroNaoEncontradoException, TimeNaoEncontradoException {
        try {
            return membroDAO.recuperaUmMembro(numero);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MembroNaoEncontradoException("Membro não encontrado");
        } catch (TimeNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time nao encontrado!");
        }
    }

    public List<Membro> recuperaMembros() {
        return membroDAO.recuperaMembros();
    }

}
