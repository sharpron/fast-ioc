package me.ron.fastioc.core.annotation.parser.impl;

import com.google.common.collect.Lists;
import me.ron.fastioc.core.InjectModel;
import me.ron.fastioc.core.annotation.Inject;
import me.ron.fastioc.core.annotation.parser.InjectParser;

import java.lang.reflect.Field;
import java.util.List;

public class FieldInjectParser implements InjectParser {

    @Override
    public List<InjectModel> parse(Class<?> clazz) {
        List<InjectModel> injectModelGroup = Lists.newArrayList();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null) {
                injectModelGroup.add(
                        InjectModel.Builder.start()
                                .injectField(field)
                                .injectType(field.getType())
                                .name(inject.name())
                                .build()
                );
            }
        }
        return injectModelGroup;
    }

}
