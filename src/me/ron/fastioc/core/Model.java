package me.ron.fastioc.core;

import com.google.common.base.Strings;
import me.ron.fastioc.core.util.Objects;

public final class Model {

    private final String name;
    private final Class<?> type;

    public Model(String name, Class<?> type) {
        Objects.checkNull(name, type);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;
        if (Strings.isNullOrEmpty(model.getName()) || Strings.isNullOrEmpty(name)) {
            return type == model.type;
        }
        return type == model.type && name.equals(model.name);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
