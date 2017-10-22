package me.ron.fastioc.core.annotation.parser;

import me.ron.fastioc.core.InjectModel;

import java.util.List;

public interface InjectParser {

    List<InjectModel> parse(Class<?> clazz);

}
