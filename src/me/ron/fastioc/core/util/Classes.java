package me.ron.fastioc.core.util;

import com.google.common.collect.Lists;
import me.ron.fastioc.core.exception.NotFoundMehtodException;
import me.ron.fastioc.core.exception.NotHaveDefaultConstructor;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Classes {

    public static final String SETTER_METHOD_PREFIX = "set";
    public static final String JAVA_CLASS_SUFFIX = ".class";

    private Classes() {throw new AssertionError();}

    public static void checkDefaultConstructor(Class<?> clazz) {
        Objects.checkNull(clazz);
        if (!hasDefaultConstructor(clazz)) {
            throw new NotHaveDefaultConstructor("have not default and public constructor");
        }
    }

    private static boolean hasDefaultConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0)
                return true;
        }
        return false;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?> ...parameterType) {
        Objects.checkNull(clazz, name, parameterType);
        try {
            return clazz.getMethod(name, parameterType);
        } catch (NoSuchMethodException e) {
            throw new NotFoundMehtodException();
        }
    }

    public static List<Method> getSetterMethod(Class<?> clazz) {
        Objects.checkNull(clazz);
        List<Method> setterMethods = Lists.newArrayList();
        for (Method method : clazz.getMethods()) {
            if (isSetterMethod(method)) {
                setterMethods.add(method);
            }
        }
        return setterMethods;
    }

    private static boolean isSetterMethod(Method method) {
        return method.getName().startsWith(SETTER_METHOD_PREFIX) && method.getParameterCount() == 1;
    }

    public static List<Class<?>> findAllClass(String basePath) {
        File baseDir = new File(basePath);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            throw new RuntimeException("please basePath is correct");
        }
        final List<File> fileContainer = Lists.newArrayList();
        findAndAddJavaFile(fileContainer, baseDir);
        try {
            return parseJavaFile(baseDir.getPath(), fileContainer);
        } catch (ClassNotFoundException e) {
            throw new AssertionError();
        }
    }

    private static void findAndAddJavaFile(List<File> fileContainer, File file) {
        if (file.isDirectory()) {
            for (File newFile : file.listFiles()) {
                findAndAddJavaFile(fileContainer, newFile);
            }
        } else {
            if (file.getName().endsWith(JAVA_CLASS_SUFFIX))
                fileContainer.add(file);
        }
    }

    private static List<Class<?>> parseJavaFile(String basePath, List<File> fileContainer) throws ClassNotFoundException {
        List<Class<?>> classes = Lists.newArrayList();
        for (File file : fileContainer) {
            String className = file.getPath()
                    .replace(basePath + File.separator, "")
                    .replace(File.separatorChar, '.')
                    .replace(JAVA_CLASS_SUFFIX, "");
            classes.add(Class.forName(className));
        }
        return classes;
    }

    public static Object invoke(Method method, Object object, Object ...parameters) {
        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setField(Field field, Object object, Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object newInstance(Constructor<?> constructor, Object ...params) {
        try {
            return constructor.newInstance(params);
        } catch (InstantiationException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        } catch (InvocationTargetException e) {
            throw new AssertionError();
        }
    }

    public static String getClassRootPath() {
        return Classes.class.getClassLoader().getResource("").getPath();
    }

}
