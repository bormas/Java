package ru.ncedu.java.tasks;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by ultra on 15.08.2017.
 */
public class Test {
    public static void main (String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections ref = new ReflectionsImpl();
        System.out.println(ref.getFooFunctionResultForDefaultConstructedClass());
    }
}
