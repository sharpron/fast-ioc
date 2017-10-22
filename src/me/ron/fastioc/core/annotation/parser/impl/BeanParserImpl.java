package me.ron.fastioc.core.annotation.parser.impl;

import me.ron.fastioc.core.BeanModel;
import me.ron.fastioc.core.Model;
import me.ron.fastioc.core.annotation.Bean;
import me.ron.fastioc.core.annotation.ConstructorParam;
import me.ron.fastioc.core.annotation.parser.BeanParser;
import me.ron.fastioc.core.exception.NoSuchConstructorException;
import me.ron.fastioc.core.util.Objects;

public class BeanParserImpl implements BeanParser {

    @Override
    public BeanModel parse(Class<?> clazz) {
        Objects.checkNull(clazz);
        Bean bean = clazz.getAnnotation(Bean.class);
        if (bean == null) {
            return null;
        }
        try {
            return new BeanModel(new Model(bean.name(), clazz), parseConstructorArg(bean), clazz.getConstructor(getParamTypes(bean)));
        } catch (NoSuchMethodException e) {
            throw new NoSuchConstructorException("params of constructor and constructor is not match!", e);
        }
    }

    private Model[] parseConstructorArg(Bean bean) {
        ConstructorParam[] constructorParams = bean.constructorParams();
        Model[] params = new Model[constructorParams.length];
        for (int i = 0; i < constructorParams.length; i++) {
            ConstructorParam constructorParam = constructorParams[i];
            params[i] = new Model(constructorParam.name(), constructorParam.type());
        }
        return params;
    }

    private Class<?>[] getParamTypes(Bean bean) {
        ConstructorParam[] constructorParams = bean.constructorParams();
        Class<?>[] params = new Class<?>[constructorParams.length];
        for (int i = 0; i < constructorParams.length; i++) {
            params[i] = constructorParams[i].type();
        }
        return params;
    }
}
