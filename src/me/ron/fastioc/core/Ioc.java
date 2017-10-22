package me.ron.fastioc.core;

import java.util.List;

public interface Ioc {

    void addAll(List<IocModel> models);

    <T> T fetch(Class<T> clazz, String name);

    <T> T fetch(Class<T> clazz);
}
