package com.mvc.framework.reflection;

import com.mvc.framework.reflection.constanst.MessagesError;
import com.mvc.framework.reflection.exceptions.ClassErrorException;
import com.mvc.framework.reflection.exceptions.MethodErrorException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author emman
 */
public class ManagerReflection implements ReflectionTransaction {

    private final HashMap<String, Object> instances = new HashMap<>();//To don't create intance of the same classes

    @Override
    public void runMethodModel(String modelNameClass, String nameMethodModel, Object view, Object controller, Object arg)
            throws MethodErrorException, ClassErrorException {
        try {
            Class c = Class.forName(modelNameClass);
            Object instance = getInstanceClass(modelNameClass);

            if (view == null && arg == null) {
                Method method = c.getMethod(nameMethodModel, controller.getClass());
                method.invoke(instance, controller);
            } else if (view == null) {
                Method method = c.getMethod(nameMethodModel, controller.getClass(), arg.getClass());
                method.invoke(instance, controller, arg);

            } else if (arg == null) {
                Method method = c.getMethod(nameMethodModel, view.getClass(), controller.getClass());
                method.invoke(instance, view, controller);
            } else {
                Method method = c.getMethod(nameMethodModel, view.getClass(), controller.getClass(), arg.getClass());
                method.invoke(instance, view, controller, arg);
            }

        } catch (NoSuchMethodException ex) {
            throw new MethodErrorException(MessagesError.ERROR_NO_METHOD_EXIST.toString());
        } catch (SecurityException | IllegalAccessException ex) {
            throw new MethodErrorException(MessagesError.ERROR_NO_ACCESIBLE_METHOD.toString());
        } catch (IllegalArgumentException ex) {
            throw new MethodErrorException(MessagesError.ERROR_ARGUMENTS_NO_VALIDS.toString());
        } catch (InvocationTargetException ex) {
            throw new MethodErrorException(MessagesError.ERROR_IN_METHOD_CODE.toString());
        } catch (ClassNotFoundException ex) {
            throw new ClassErrorException(MessagesError.ERROR_NO_CLASS_EXISTS.toString());
        }
    }

    @Override
    public Object getInstanceClass(String nameClass) throws ClassErrorException {

        if (instances.containsKey(nameClass)) {
            return instances.get(nameClass);
        } else {

            try {
                Class c = Class.forName(nameClass);
                Constructor[] constructors = c.getDeclaredConstructors();
                Constructor construc = null;
                for (int i = 0; i < constructors.length; i++) {
                    construc = constructors[i];
                    if (construc.getGenericParameterTypes().length == 0) {
                        break;
                    }
                }
                construc.setAccessible(true);
                Object instance = construc.newInstance();
                instances.put(nameClass, instance);
                return instance;
            } catch (ClassNotFoundException ex) {
                throw new ClassErrorException(MessagesError.ERROR_NO_CLASS_EXISTS.toString());
            } catch (InstantiationException ex) {
                throw new ClassErrorException(MessagesError.ERROR_INIT_CLASS.toString());
            } catch (IllegalAccessException ex) {
                throw new ClassErrorException(MessagesError.ERROR_INIT_UNACCESBILE.toString());
            } catch (IllegalArgumentException ex) {
                throw new ClassErrorException(MessagesError.ERROR_INIT_ARGUMENTS.toString());
            } catch (InvocationTargetException ex) {
                throw new ClassErrorException(MessagesError.ERROR_INIT_CODE.toString());
            }

        }
    }

}
