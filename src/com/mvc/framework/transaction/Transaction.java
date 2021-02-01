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
    private String controller_func;
    private String view;
    private String view_func;
    private String model;
    private String model_func;

    public Transaction(String name, String controller, String view, String model) {
        this.name = name;
        this.controller = controller;
        this.view = view;
        this.model = model;
    }

    public Transaction() {
    }

    public String getController_func() {
        return controller_func;
    }

    public String getView_func() {
        return view_func;
    }

    public String getModel_func() {
        return model_func;
    }

    public void setController_func(String controller_func) {
        this.controller_func = controller_func;
    }

    public void setView_func(String view_func) {
        this.view_func = view_func;
    }

    public void setModel_func(String model_func) {
        this.model_func = model_func;
    }

    public String getName() {
        return name;
    }

    public String getController() {
        return controller;
    }

    public String getView() {
        return view;
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

    public void setView(String view) {
        this.view = view;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Transaction{" + "name=" + name + ", controller=" + controller + ", controller_func=" + controller_func + ", view=" + view + ", view_func=" + view_func + ", model=" + model + ", model_func=" + model_func + '}';
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
