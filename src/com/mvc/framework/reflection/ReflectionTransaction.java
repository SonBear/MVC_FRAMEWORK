/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.reflection;

import com.mvc.framework.reflection.exceptions.ClassErrorException;
import com.mvc.framework.reflection.exceptions.MethodErrorException;

/**
 *
 * @author emman
 */
public interface ReflectionTransaction {

    public Object getInstanceClass(String nameClass) throws ClassErrorException;

    public void runMethodModel(String modelNameClass, String nameMethodModel, Object view, Object controller, Object arg) throws MethodErrorException, ClassErrorException;

}
