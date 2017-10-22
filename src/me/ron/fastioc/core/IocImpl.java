package me.ron.fastioc.core;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.ron.fastioc.core.exception.NotFoundModelException;
import me.ron.fastioc.core.util.Objects;

import java.util.List;
import java.util.Map;

class IocImpl implements Ioc {

    private final Map<Class<?>, List<IocModel>> iocContainer = Maps.newHashMap();

    @Override
    public void addAll(List<IocModel> models) {
        Objects.checkNull(models);
        for (IocModel model : models) {
            add(model);
        }
    }

    private void add(IocModel model) {
        Class<?> modelType = model.getType();
        List<IocModel> list = Lists.newArrayList();
        if (iocContainer.containsKey(modelType)) {
            list = iocContainer.get(modelType);
        }
        list.add(model);
        iocContainer.put(modelType, list);
    }

    @Override
    public <T> T fetch(Class<T> clazz, String name) {
        if (iocContainer.containsKey(clazz)) {
            List<IocModel> models = iocContainer.get(clazz);
            if (Strings.isNullOrEmpty(name)) {
                return (T) models.get(0).getObject();
            }
            for (IocModel model : models) {
                if (model.getName().equals(name)) {
                    return (T) model.getObject();
                }
            }
            throw new NotFoundModelException();
        }
        return null;
    }

    @Override
    public <T> T fetch(Class<T> clazz) {
        return fetch(clazz, null);
    }
}
