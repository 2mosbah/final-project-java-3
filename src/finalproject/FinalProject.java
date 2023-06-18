/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import View.ViewManager;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class FinalProject extends Application {

    public static int userId = 0;

    public static void successAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void warningAlert(String title, String content) {
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle(title);
        warnAlert.setContentText(content);
        warnAlert.show();
    }

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
