package servico;

import anotacao.RoleAdmin;
import dao.MembroDAO;
import dao.TimeDAO;
import excecao.*;
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
    public long inclui(Membro umMembro) throws TimeNaoEncontradoException, MembroJaExistenteException {
        Membro membro = membroDAO.inclui(umMembro);
        return membro.getId();
    }

    @Transactional
    public void altera(Membro umMembro){
        try {
            membroDAO.altera(umMembro);
        } catch (ObjetoNaoEncontradoException e) {
            System.out.println("Membro não encontrado");
        }
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

    @RoleAdmin
    public List<Membro> recuperaMembros() throws ExecucaoDeMetodoSemARespectivaPermissaoException {
        return membroDAO.recuperaMembros();
    }

}
