package dao.impl;

import dao.MembroDAO;
import modelo.Membro;

public abstract class MembroDAOImpl extends JPADaoGenerico<Membro, Long> implements MembroDAO{
    public MembroDAOImpl() {
        super(Membro.class);
    }
}
