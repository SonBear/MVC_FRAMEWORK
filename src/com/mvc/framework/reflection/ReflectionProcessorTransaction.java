/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.reflection;

/**
 *
 * @author emman
 */
public interface ReflectionProcessorTransaction {

    public Object getInstanceClass(String nameClass) throws ClassNotFoundException;

    public void runMethodModel(String modelNameClass, String nameMethodModel, Object view, Object controller, Object arg) throws NoSuchMethodException, ClassNotFoundException;

}
