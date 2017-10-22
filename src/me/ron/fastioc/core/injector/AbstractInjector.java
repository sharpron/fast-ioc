package me.ron.fastioc.core.injector;

import me.ron.fastioc.core.Injector;

public abstract class AbstractInjector implements Injector {

    protected final Object injectedObject;

    public AbstractInjector(Object injectedObject) {
        this.injectedObject = injectedObject;
    }
}
