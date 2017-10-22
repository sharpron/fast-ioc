package me.ron.fastioc.core.annotation.parser;

import com.google.common.collect.Lists;
import me.ron.fastioc.core.InjectModel;
import me.ron.fastioc.core.annotation.parser.impl.FieldInjectParser;
import me.ron.fastioc.core.annotation.parser.impl.MethodInjectParser;
import me.ron.fastioc.core.util.Objects;

import java.util.Collections;
import java.util.List;

public class CommonInjectParser implements InjectParser {

    private final List<InjectParser> injectParsers = Lists.newArrayList();

    private CommonInjectParser() {
        injectParsers.add(new FieldInjectParser());
        injectParsers.add(new MethodInjectParser());
    }

    @Override
    public List<InjectModel> parse(Class<?> clazz) {
        Objects.checkNull(clazz);
        List<InjectModel> injectModelGroup = Lists.newArrayList();
        for (InjectParser injectParser : injectParsers) {
            injectModelGroup.addAll(injectParser.parse(clazz));
        }
        injectModelGroup.removeAll(Collections.singleton(null));
        return injectModelGroup;
    }

    public static List<InjectModel> parseInjectModel(Class<?> clazz) {
        return new CommonInjectParser().parse(clazz);
    }


}
