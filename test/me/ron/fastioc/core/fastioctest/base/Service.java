package me.ron.fastioc.core.fastioctest.base;

import me.ron.fastioc.core.annotation.Bean;
import me.ron.fastioc.core.annotation.Inject;

@Bean
public class Service {

    @Inject
    private Dao dao;

    public Dao getDao() {
        return dao;
    }
}
