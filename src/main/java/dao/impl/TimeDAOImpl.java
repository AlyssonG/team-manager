package dao.impl;

import dao.TimeDAO;
import modelo.Time;

public abstract class TimeDAOImpl extends JPADaoGenerico<Time, Long> implements TimeDAO{
    public TimeDAOImpl() {
        super(Time.class);
    }
}
