/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.exampl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emman
 */
public class Modelo {

    //Estado
    private List<Person> persons;

    //No parametros en el constructor
    public Modelo() {
        //Pero podemos usarlo para inicializar objetos
        persons = new ArrayList<>();
        persons.add(new Person(30, "Juan"));
        persons.add(new Person(21, "Emmanuel"));
        persons.add(new Person(32, "Miguel"));
        persons.add(new Person(19, "Carlos"));
    }

    public void addPerson(Vista view, Controlador controlador, Person person) {
        persons.add(person);
        controlador.printPersonList(view, (ArrayList<Person>) persons);
    }

    //Si el parametro vista es null
    public void addPerson(Controlador controlador, Person person) {
        persons.add(person);
        controlador.printPersonList(persons);
    }

    //Si el parametro arg es null
    public void addPerson(Vista vista, Controlador controlador) {
        persons.add(new Person(10, "Miguel"));
        controlador.printPersonList(vista, persons);
    }

    //Si el parametro arg y view son null
    public void addPerson(Controlador controlador) {
        persons.add(new Person(12, "Charlie"));
        controlador.printPersonList(persons);
    }

    public void initList(Vista vista, Controlador controlador) {
        controlador.printPersonList(vista, persons);
    }

}
