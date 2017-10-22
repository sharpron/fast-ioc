package me.ron.fastioc.core;

import me.ron.fastioc.core.annotation.parser.impl.BeanParserImpl;
import me.ron.fastioc.core.loader.GenerateLoader;
import me.ron.fastioc.core.util.Classes;
import org.apache.log4j.Logger;


public final class FastIoc {

    private static final Ioc IOC = new IocImpl();
    private static final Logger LOGGER = Logger.getLogger(FastIoc.class);

    private FastIoc() {
        throw new AssertionError();
    }

    public static void start() {
        long startedTime = System.currentTimeMillis();
        LOGGER.info("starting ioc...");
        new GenerateLoader(IOC, new BeanParserImpl()).load(Classes.getClassRootPath());
        LOGGER.info(String.format("started ioc in %dms", System.currentTimeMillis() - startedTime));
    }

    public static <T> T fetch(Class<T> tClass) {
        return IOC.fetch(tClass);
    }

    public static <T> T fetch(Class<T> tClass, String name) {
        return IOC.fetch(tClass, name);
    }
}
