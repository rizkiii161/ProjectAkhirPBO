/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

<<<<<<< HEAD
import Controller.TodosController;
import View.TodosView;
=======
//import View.NIggga;
import Controller.SubTasksController;
import View.SubTaksView;
>>>>>>> b648e9920de99d6cc994763ec263bcf684ec3c07

/**
 *
 * @author Rizki
 */
public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
        TodosView view = new TodosView();
        new TodosController(view);
=======
        // TODO code application logic here

        SubTaksView view = new SubTaksView();
        SubTasksController controller = new SubTasksController(view, 2);
>>>>>>> b648e9920de99d6cc994763ec263bcf684ec3c07
        view.setVisible(true);
    }
    
}
