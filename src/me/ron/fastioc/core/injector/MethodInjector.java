package me.ron.fastioc.core.injector;

import me.ron.fastioc.core.util.Classes;

import java.lang.reflect.Method;

public class MethodInjector extends AbstractInjector {
    private final Method method;

    public MethodInjector(Object injectedObject, Method method) {
        super(injectedObject);
        this.method = method;
    }

    @Override
    public void inject(Object object) {
        Classes.invoke(method, injectedObject, object);
    }
}
