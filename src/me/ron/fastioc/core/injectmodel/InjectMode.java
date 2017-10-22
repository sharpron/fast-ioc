package me.ron.fastioc.core.injectmodel;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public interface InjectMode<T extends Member> {

    Class<?> getType();

    String getName();

}
