/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import Controller.TodosController;
import View.TodosView;

/**
 *
 * @author Rizki
 */
public class Main {

    public static void main(String[] args) {
        TodosView view = new TodosView();
        new TodosController(view);
        view.setVisible(true);
    }
    
}
