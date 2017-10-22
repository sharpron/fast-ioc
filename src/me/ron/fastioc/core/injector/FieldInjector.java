package me.ron.fastioc.core.injector;

import me.ron.fastioc.core.Model;
import me.ron.fastioc.core.util.Classes;

import java.lang.reflect.Field;

public class FieldInjector extends AbstractInjector {

    private final Field field;

    public FieldInjector(Model injectedModel, Field field) {
        super(injectedModel);
        this.field = field;
    }


    @Override
    public void inject(Object object) {
        Classes.setField(field, injectedObject, object);
    }
}
