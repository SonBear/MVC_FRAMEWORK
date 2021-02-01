/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.xml;

import java.util.List;

/**
 *
 * @author emman
 */
public interface XMLManager<T> {

    public List<T> readData() throws NoFileConfigException, BadConfigException;

}
