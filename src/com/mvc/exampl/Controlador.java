/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.exampl;

import java.util.List;

/**
 *
 * @author emman
 */
public class Controlador {
    //Igual constructor sin parametros

    public void printPersonList(Vista vista, List<Person> persons) {
        String currentText = "";

        for (Person p : persons) {
            currentText += p.getNombre() + " " + p.getEdad() + "\n";
        }

        vista.getjTextArea1().setText(currentText);
    }

    public void printPersonList(List<Person> list) {
        System.out.println(list);
    }

}
