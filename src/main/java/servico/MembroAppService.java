package servico;

import dao.MembroDAO;
import dao.TimeDAO;
import excecao.MembroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Membro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MembroAppService {
    @Autowired
    private TimeDAO timeDAO;

    @Autowired
    private MembroDAO membroDAO;

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
}
