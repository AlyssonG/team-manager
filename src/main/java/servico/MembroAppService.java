package servico;

import dao.MembroDAO;
import dao.TimeDAO;
import excecao.MembroJaExistenteException;
import excecao.MembroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Membro;
import modelo.Time;
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
        //incluir regra de membros em um s� time
        //membroDAO.find(membro)
        //se n�o achar, inclui
        for(Time t : timeDAO.recuperaTimesEMembros()){
            for(Membro m : t.getMembros()){
                if(m.getNome().equalsIgnoreCase(umMembro.getNome())){
                    throw new MembroJaExistenteException("Membro j� existente");
                }
            }
        }
        Membro membro = membroDAO.inclui(umMembro);
        return membro.getId();
    }

    @Transactional
    public void exclui(Membro umMembro) throws MembroNaoEncontradoException {
        try {
            membroDAO.exclui(umMembro);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MembroNaoEncontradoException("Membro n�o encontrado.");
        }
    }

    public Membro recuperaUmMembro(long numero)
            throws MembroNaoEncontradoException, TimeNaoEncontradoException {
        try {
            return membroDAO.recuperaUmMembro(numero);
        } catch (ObjetoNaoEncontradoException e) {
            throw new MembroNaoEncontradoException("Membro n�o encontrado");
        } catch (TimeNaoEncontradoException e) {
            throw new TimeNaoEncontradoException("Time nao encontrado!");
        }
    }

    public List<Membro> recuperaMembros() {
        return membroDAO.recuperaMembros();
    }

}
