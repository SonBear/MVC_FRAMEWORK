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
public interface ReflectionProcessor {

    public void runMethodController(String nameClass, String nameMethod, Object view, Object modelObj) throws ClassNotFoundException, NoSuchMethodException;

    public Object runMethodModel(String nameClass, String nameMethod, Object modelObj) throws ClassNotFoundException, NoSuchMethodException;
}
