/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emman
 */
public class Model {

    private String state = "dasdasda";

    public void methodModel(View view, Controller controller, String arg) {
        state = arg;
        controller.doSomethingController(view, arg);
    }
}
