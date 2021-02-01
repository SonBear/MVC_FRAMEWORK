/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author emman
 */
public class ManagerReflection implements ReflectionProcessor {

    private HashMap<String, Object> instances = new HashMap<>();

    @Override
    public void runMethodController(String nameClass, String nameMethod, Object view, Object modelObj) throws ClassNotFoundException, NoSuchMethodException {
        try {
            Object instance = getInstanceClass(nameClass);
            Class c = Class.forName(nameClass);

            if (view == null) {
                Method method = c.getMethod(nameMethod, modelObj.getClass());
                method.invoke(instance, modelObj);

            } else if (modelObj == null) {

                Method method = c.getMethod(nameMethod, view.getClass());
                method.invoke(instance, view);

            } else {
                Method method = c.getMethod(nameMethod, view.getClass(), modelObj.getClass());
                method.invoke(instance, view, modelObj);
            }

        } catch (NoSuchMethodException ex) {
            throw new NoSuchMethodException("Error metodo no existe en el controlador");
        } catch (SecurityException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalAccessException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalArgumentException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (InvocationTargetException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        }
    }

    @Override
    public Object runMethodModel(String nameClass, String nameMethod, Object modelObj) throws ClassNotFoundException, NoSuchMethodException {
        Object objRes = null;
        try {
            Class c = Class.forName(nameClass);
            Object instance = getInstanceClass(nameClass);

            if (modelObj == null) {
                Method method = c.getMethod(nameMethod);
                objRes = method.invoke(instance);

            } else {
                Method method = c.getMethod(nameMethod, modelObj.getClass());
                objRes = method.invoke(instance, modelObj);
            }

        } catch (NoSuchMethodException ex) {
            throw new NoSuchMethodException("Error metodo no existe en el modelo");
        } catch (SecurityException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalAccessException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalArgumentException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (InvocationTargetException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        }
        return objRes;
    }

    private Object getInstanceClass(String nameClass) throws ClassNotFoundException {

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
                throw new ClassNotFoundException("Error la clase no está definida");
            } catch (InstantiationException ex) {
                throw new ClassNotFoundException("Error al inicializar clase");
            } catch (IllegalAccessException ex) {
                throw new ClassNotFoundException("Error al inicializar clase");
            } catch (IllegalArgumentException ex) {
                throw new ClassNotFoundException("Error al inicializar clase");
            } catch (InvocationTargetException ex) {
                throw new ClassNotFoundException("Error al inicializar clase");
            }

        }
    }

}
