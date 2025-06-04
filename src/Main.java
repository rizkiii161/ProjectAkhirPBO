/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//import View.NIggga;
import Controller.SubTasksController;
import View.SubTaksView;

/**
 *
 * @author Rizki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SubTaksView view = new SubTaksView();
        SubTasksController controller = new SubTasksController(view, 2);
        view.setVisible(true);
    }
    
}
