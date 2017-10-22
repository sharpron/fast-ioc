package me.ron.fastioc.core.annotation.parser.impl;

import com.google.common.collect.Lists;
import me.ron.fastioc.core.InjectModel;
import me.ron.fastioc.core.annotation.Inject;
import me.ron.fastioc.core.annotation.parser.InjectParser;
import me.ron.fastioc.core.util.Classes;

import java.lang.reflect.Method;
import java.util.List;

public class MethodInjectParser implements InjectParser {

    @Override
    public List<InjectModel> parse(Class<?> clazz) {
        List<InjectModel> injectModelGroup = Lists.newArrayList();
        for (Method method : Classes.getSetterMethod(clazz)) {
            Inject inject = method.getAnnotation(Inject.class);
            if (inject != null) {
                injectModelGroup.add(
                        InjectModel.Builder.start()
                                .injectMethod(method)
                                .injectType(method.getParameterTypes()[0])
                                .name(inject.name())
                                .build()
                );
            }
        }
        return injectModelGroup;
    }
}
