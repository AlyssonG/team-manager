package dao.impl;

import modelo.Membro;

public abstract class MembroDAOImpl extends JPADaoGenerico<Membro,Long>{
    public MembroDAOImpl() {
        super(Membro.class);
    }
}
