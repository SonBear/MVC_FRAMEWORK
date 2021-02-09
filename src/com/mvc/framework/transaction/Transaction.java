/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.transaction;

import java.util.Objects;

/**
 *
 * @author emman
 */
public class Transaction {

    private String name;
    private String controller;
    private String model;
    private String modelFunction;

    public Transaction(String name, String controller, String view, String model) {
        this.name = name;
        this.controller = controller;
        this.model = model;
    }

    public Transaction() {
    }

    public String getModelFunction() {
        return modelFunction;
    }

    public void setModelFunction(String modelFunction) {
        this.modelFunction = modelFunction;
    }

    public String getName() {
        return name;
    }

    public String getController() {
        return controller;
    }

    public String getModel() {
        return model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Transaction{" + "name=" + name + ", controller=" + controller + ", model=" + model + ", model_func=" + modelFunction + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
