package ru.ncedu.java.tasks;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by ultra on 15.08.2017.
 */
public class ReflectionsImpl implements Reflections {
    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NullPointerException {
        if (object == null || fieldName == null)
            throw new NullPointerException();

        try {
            Class clazz = object.getClass();
            Field fieldValueByName = clazz.getDeclaredField(fieldName);
            fieldValueByName.setAccessible(true);

            return fieldValueByName.get(object);
        } catch (NoSuchFieldException ex) {
            throw new IllegalStateException("Field was not found");
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException("Constructor error");
        }
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) throws NullPointerException {
        if (clazz == null)
            throw new NullPointerException();

        Set<String> protectedMethodNames = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods)
            if (Modifier.isProtected(m.getModifiers())) {
                protectedMethodNames.add(m.getName());
            }

        return protectedMethodNames;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) throws NullPointerException {
        if (clazz == null)
            throw new NullPointerException();

        Set<Method> allImplementedMethodsWithSupers = new HashSet<>();

        do {
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods)
                allImplementedMethodsWithSupers.add(m);

            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return allImplementedMethodsWithSupers;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) throws NullPointerException {
        if (clazz == null)
            throw new NullPointerException();

        List<Class> extendsHierarchy = new ArrayList<>();
        clazz = clazz.getSuperclass();

        while (clazz != null) {
            extendsHierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }

        return extendsHierarchy;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) throws NullPointerException {
        if (clazz == null)
            throw new NullPointerException();

        Set<Class> implementedInterfaces = new HashSet<>();
        Class[] interfaces = clazz.getInterfaces();


       for (Class i : interfaces)
            implementedInterfaces.add(i);

        return implementedInterfaces;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) throws NullPointerException {
        if (method == null)
            throw new NullPointerException();

        List<Class> thrownExceptions = new ArrayList<>();
        Class[] exceptions = method.getExceptionTypes();

        for (Class i : exceptions)
            thrownExceptions.add(i);

        return thrownExceptions;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        try {
            Class clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];

            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            Object newInstance = constructor.newInstance();

            Method foo = clazz.getDeclaredMethod("foo");
            foo.setAccessible(true);

            return (String) foo.invoke(newInstance);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Class was not found");
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("Method or constructor was not found");
        } catch (SecurityException ex) {
            throw new IllegalStateException("Method error");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException ex) {
            throw new IllegalStateException("Constructor error");
        }
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        try {
            Class clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];

            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            Object newInstance = constructor.newInstance();

            Class[] paramTypes = new Class[]{String.class, Integer[].class};
            Method foo = clazz.getDeclaredMethod("foo", paramTypes);
            foo.setAccessible(true);

            return (String) foo.invoke(newInstance, string, integers);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Class was not found");
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("Method or constructor was not found");
        } catch (SecurityException ex) {
            throw new IllegalStateException("Method error");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException ex) {
            throw new IllegalStateException("Constructor error");
        }
    }
}
