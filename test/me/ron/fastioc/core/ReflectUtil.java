package me.ron.fastioc.core;

import java.io.*;
import java.util.regex.Pattern;

public class ReflectUtil {
    private static final Pattern pattern = Pattern.compile("class\\s+(\\w+)");

    private static void gennerateTestJavaFile(String basePackage, String javaSource) {
        String path = ReflectUtil.class.getClassLoader().getResource("/").getPath();
        String fileName = pattern.matcher(javaSource).group() + ".java";
        File file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileWriter fw = new FileWriter(file)) {
            fw.write(javaSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
