package me.ron.fastioc.core.fastioctest.constructor;

import me.ron.fastioc.core.annotation.Bean;
import me.ron.fastioc.core.annotation.ConstructorParam;
import me.ron.fastioc.core.annotation.Inject;

@Bean(constructorParams = @ConstructorParam(type = Dao.class))
public class Service {

    private Dao dao;

    public Service(Dao dao) {
        this.dao = dao;
    }

    public Dao getDao() {
        return dao;
    }

}
