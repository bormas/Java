package ru.ncedu.java.tasks;

/**
 * Created by ultra on 15.08.2017.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public interface Reflections {

    /**
     * Method returns current field value of this object,
     * which has identifier private, public, protected or default.
     * @param object class object
     * @param fieldName field name
     * @throws NoSuchFieldException if field with this name does not exist
     * @throws NullPointerException if fieldName or object is null
     * @return current field value
     * */
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException;

    /**
     * Returns all names of protected methods
     * @param clazz class
     * @throws NullPointerException if clazz is null
     * @return set of names
     * */
    public Set<String> getProtectedMethodNames(Class clazz);

    /**
     * Method returns all methods as well as methods which are in its superclass
     * @param clazz class
     * @throws NullPointerException if clazz is null
     * @return set of methods
     * */
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz);

    /**
     * Method returns set of all names of superclasses
     * @param clazz class
     * @throws NullPointerException if clazz is null
     * @return set of classes
     * */
    public List<Class> getExtendsHierarchy(Class clazz);

    /**
     * Returns the list of interfaces, which implemented in this class
     * @param clazz - class/interface
     * @throws NullPointerException - if clazz is null
     * @return set of interfaces
     * */
    public Set<Class> getImplementedInterfaces(Class clazz);

    /**
     * Method returns the set of exceptions
     * @param method method
     * @return set of exceptions
     * @throws NullPointerException if method is null
     * */
    public List<Class> getThrownExceptions(Method method);

    /**
     * Method creates instance of SecretClass  with no arguments
     * and calls method foo()
     * @return result of method foo()
     * */
    public String getFooFunctionResultForDefaultConstructedClass() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    /**
     * Method creates instance of SecretClass  with argument constructorParameter
     * and calls method foo(...), with the same list of parameters which function obtained
     * @param constructorParameter parameter, which transported to constructor
     * @param string first argument of foo method
     * @param integers last argument of foo method
     * @return result of method foo(...)
     * */
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer ... integers) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    @SuppressWarnings("unused")
    public class SecretClass {

        private String text = null;

        private String secret = "secret";

        private SecretClass() {
        }

        public SecretClass(String text) {
            this.text = text;
        }

        public void setSecret (String secret) {
            this.secret = secret;
        }

        private String foo(String string, Integer ... integers){
            String s = "";
			/* Some text hidden : start */
            int in = 0;
            if(integers != null)
                for(Integer i : integers)
                    in += i;
            s += string + " - > " + in;
			/* Some text hidden : end */
            return s;
        }

        private String foo(){
            String s = "";
			/* Some text hidden : start */
            s += "abraKadabra";
			/* Some text hidden : end */
            return s;
        }

    }

}