package me.ron.fastioc.core.annotation.parser;

import me.ron.fastioc.core.BeanModel;

public interface BeanParser {

    BeanModel parse(Class<?> clazz);
}
