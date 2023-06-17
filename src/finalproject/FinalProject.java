/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class FinalProject extends Application {

    public static int userId = 0;

    @Override
    public void start(Stage stage) throws Exception {

        ViewManager.openPatientLoginPage();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
