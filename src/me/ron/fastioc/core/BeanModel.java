package me.ron.fastioc.core;

import java.lang.reflect.Constructor;

public final class BeanModel {

    private final Model model;
    private final Model[] constructorArguments;
    private final Constructor<?> constructor;

    public BeanModel(Model model, Model[] constructorArguments, Constructor<?> constructor) {
        this.model = model;
        this.constructorArguments = constructorArguments;
        this.constructor = constructor;
    }

    public Model getModel() {
        return model;
    }

    public Model[] getConstructorArguments() {
        return constructorArguments;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }
}
