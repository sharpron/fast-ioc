package me.ron.fastioc.core.fastioctest.setter;

import me.ron.fastioc.core.annotation.Bean;
import me.ron.fastioc.core.annotation.Inject;

@Bean
public class Service {

    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    @Inject
    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
