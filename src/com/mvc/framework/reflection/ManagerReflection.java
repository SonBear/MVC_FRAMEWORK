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
public class ManagerReflection implements ReflectionProcessorTransaction {

    private HashMap<String, Object> instances = new HashMap<>();

    @Override
    public void runMethodModel(String modelNameClass, String nameMethodModel, Object view, Object controller, Object arg)
            throws NoSuchMethodException, ClassNotFoundException {
        try {
            Class c = Class.forName(modelNameClass);
            Object instance = getInstanceClass(modelNameClass);

            if (view == null) {
                Method method = c.getMethod(nameMethodModel, controller.getClass(), arg.getClass());
                method.invoke(instance, controller.getClass(), arg.getClass());

            } else if (controller == null) {
                Method method = c.getMethod(nameMethodModel, view.getClass(), arg.getClass());
                method.invoke(instance, view.getClass(), arg.getClass());
            } else if (arg == null) {
                Method method = c.getMethod(nameMethodModel, view.getClass(), controller.getClass());
                method.invoke(instance, view.getClass(), controller.getClass());
            } else {
                Method method = c.getMethod(nameMethodModel, view.getClass(), controller.getClass(), arg.getClass());
                method.invoke(instance, view, controller, arg);
            }

        } catch (NoSuchMethodException ex) {
            throw new NoSuchMethodException("Error metodo no existe en el modelo");
        } catch (SecurityException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalAccessException ex) {
            throw new NoSuchMethodException("Error metodo inaccesible");
        } catch (IllegalArgumentException ex) {
            throw new NoSuchMethodException("Error argumentos del metodo no validos");
        } catch (InvocationTargetException ex) {
            throw new NoSuchMethodException("Error en el codigo dentro del metodo");
        }
    }

    @Override
    public Object getInstanceClass(String nameClass) throws ClassNotFoundException {

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
                throw new ClassNotFoundException("Error al inicializar no es accesible el constructor clase");
            } catch (IllegalArgumentException ex) {
                throw new ClassNotFoundException("Error al inicializar argumentos no validos para el contructor clase");
            } catch (InvocationTargetException ex) {
                throw new ClassNotFoundException("Error en el codigo dentro del contructor de la clase");
            }

        }
    }

}
