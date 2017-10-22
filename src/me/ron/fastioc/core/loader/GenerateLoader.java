package me.ron.fastioc.core.loader;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.ron.fastioc.core.*;
import me.ron.fastioc.core.annotation.parser.BeanParser;
import me.ron.fastioc.core.annotation.parser.CommonInjectParser;
import me.ron.fastioc.core.util.Classes;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GenerateLoader implements Loader {

    private static final Logger LOGGER = Logger.getLogger(GenerateLoader.class);
    private final Ioc ioc;
    private final BeanParser beanParser;

    public GenerateLoader(Ioc ioc, BeanParser beanParser) {
        this.ioc = ioc;
        this.beanParser = beanParser;
    }

    @Override
    public void load(String path) {
        List<Class<?>> classes = Classes.findAllClass(path);
        LOGGER.info("creating dependencies...");
        List<BeanModel> models = createDependency(classes);
        LOGGER.info("created dependencies.");
        LOGGER.info("creating objects.");
        List<IocModel> iocModels = createObject(models);
        ioc.addAll(iocModels);
        LOGGER.info("created objects.");
        LOGGER.info("start inject manage");
        injectManage(iocModels);
        LOGGER.info("finish inject");
    }

    private List<BeanModel> createDependency(List<Class<?>> classes) {
        List<BeanModel> models = Lists.newArrayList();
        for (Class<?> clazz : classes) {
            BeanModel beanModel = beanParser.parse(clazz);
            models.add(beanModel);
            LOGGER.info("read class " + clazz.getName());
        }
        models.removeAll(Collections.singleton(null));
        return models;
    }

    private List<IocModel> createObject(List<BeanModel> beanModels) {
        Map<Model, BeanModel> beanModelMap = GenerateLoader.wrap(beanModels);
        Map<Model, Object> objectPool = Maps.newHashMap();
        for (BeanModel beanModel : beanModels) {
            if (!objectPool.containsKey(beanModel.getModel())) {
                createObject(beanModelMap, beanModel, objectPool);
            }
        }
        return mapToList(objectPool);
    }

    private List<IocModel> mapToList(Map<Model, Object> map) {
        List<IocModel> iocModels = Lists.newArrayList();
        for (Map.Entry<Model, Object> entry : map.entrySet()) {
            iocModels.add(new IocModel(entry.getKey(), entry.getValue()));
        }
        return iocModels;
    }

    private void createObject(Map<Model, BeanModel> beanModelMap, BeanModel beanModel, Map<Model, Object> map) {
        Model[] constructorArguments = beanModel.getConstructorArguments();
        Object[] objects = new Object[constructorArguments.length];
        for (int i = 0; i < constructorArguments.length; i++) {
            Model constructorArgument = constructorArguments[i];
            Object object = map.get(constructorArgument);
            if (object == null) {
                createObject(beanModelMap, beanModelMap.get(constructorArgument), map);
            }
            objects[i] = object;
        }
        map.put(beanModel.getModel(), Classes.newInstance(beanModel.getConstructor(), objects));
    }

    private static Map<Model, BeanModel> wrap(List<BeanModel> beanModels) {
        Map<Model, BeanModel> map = Maps.newHashMap();
        for (BeanModel beanModel : beanModels) {
            map.put(beanModel.getModel(), beanModel);
        }
        return map;
    }

    private void injectManage(List<IocModel> iocModels) {
        for (IocModel iocModel : iocModels) {
            List<InjectModel> injectModels = CommonInjectParser.parseInjectModel(iocModel.getType());
            for (InjectModel injectModel : injectModels) {
                inject(iocModel, injectModel);
            }
        }
    }

    private void inject(IocModel model, InjectModel injectModel) {
        Method injectMethod = injectModel.getInjectMethod();
        Object injectObject = ioc.fetch(injectModel.getInjectType(), injectModel.getName());
        if (injectModel.getInjectMethod() != null) {
            Classes.invoke(injectMethod, model.getObject(), injectObject);
        } else {
            Classes.setField(injectModel.getInjectField(), model.getObject(), injectObject);
        }
    }


}
