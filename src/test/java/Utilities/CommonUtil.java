package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CommonUtil {
    public static Set<String> getMethods(Set<String> classNames) {
        Set<String> allMethods = new HashSet<>();
        try {
            for (String name : classNames) {
                Class c = Class.forName(name);
                Method[] methods = c.getMethods();
                for (Method method : methods) {
                    Annotation ann = method.getAnnotation(Test.class);
                    if (ann != null) {
                        allMethods.add(method.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMethods;
    }
}


