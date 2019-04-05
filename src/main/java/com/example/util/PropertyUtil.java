package com.example.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by yaswanth on 05/04/19.
 */
@Configuration
public class PropertyUtil {
    @Resource
    private Environment environment;

    public String getProperty(String propertyName) throws Exception {
        if(environment.getProperty(propertyName).isEmpty() || null== environment.getProperty(propertyName))
            throw new Exception("Property not found.Please contact System Administrator");
        return environment.getProperty(propertyName);
    }
}
