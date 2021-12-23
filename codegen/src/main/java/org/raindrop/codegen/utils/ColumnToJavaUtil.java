package org.raindrop.codegen.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class ColumnToJavaUtil {
    public static Properties PROPERTIES;

    static {
        try {
            System.out.println(new ClassPathResource("generator.properties").getPath());
            PROPERTIES.load(new ClassPathResource("generator.properties").getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key){
        return PROPERTIES.getProperty(key);
    }
}
