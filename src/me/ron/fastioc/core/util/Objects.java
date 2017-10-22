package me.ron.fastioc.core.util;

import java.util.List;

public class Objects {

    private Objects() {throw new AssertionError();}

    public static void checkNull(Object object) {
        if (object == null)
            throw new IllegalArgumentException("parameter is null");
        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            for (Object element : array) {
                checkNull(element);
            }
            return;
        }
        if (object instanceof List) {
            List<Object> objects = (List<Object>) object;
            for (Object element : objects) {
                checkNull(element);
            }
        }
    }

    public static void checkNull(Object object, Object ...objects) {
        checkNull(object);
        for (Object o : objects) {
            checkNull(o);
        }
    }

    public static Object produceObject(Class<?> clazz) {
        Classes.checkDefaultConstructor(clazz);
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        }
    }
}
