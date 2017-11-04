package dao.impl;

import modelo.Time;

public abstract class TimeDAOImpl extends JPADaoGenerico<Time,Long> {
    public TimeDAOImpl() {
        super(Time.class);
    }
}
