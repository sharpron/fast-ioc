package me.ron.fastioc.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectModel {

    private final Field injectField;
    private final Method injectMethod;
    private final Class<?> injectType;
    private final String name;


    private InjectModel(Builder builder) {
        this.injectField = builder.injectField;
        this.injectMethod = builder.injectMethod;
        this.injectType = builder.injectType;
        this.name = builder.name;
    }

    public Field getInjectField() {
        return injectField;
    }

    public Method getInjectMethod() {
        return injectMethod;
    }

    public Class<?> getInjectType() {
        return injectType;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Field injectField;
        private Method injectMethod;
        private Class<?> injectType;
        private String name;

        public Builder injectField(Field injectField) {
            this.injectField = injectField;
            return this;
        }

        public Builder injectMethod(Method injectMethod) {
            this.injectMethod = injectMethod;
            return this;
        }

        public Builder injectType(Class<?> injectType) {
            this.injectType = injectType;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public InjectModel build() {
            return new InjectModel(this);
        }

        public static Builder start() {
            return new Builder();
        }

    }
}
