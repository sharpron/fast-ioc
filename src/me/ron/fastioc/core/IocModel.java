package me.ron.fastioc.core;

public final class IocModel {

    private final Model model;
    private final Object object;

    public IocModel(Model model, Object object) {
        this.model = model;
        this.object = object;
    }

    public String getName() {
        return model.getName();
    }

    public Class<?> getType() {
        return model.getType();
    }

    public Object getObject() {
        return object;
    }
}
