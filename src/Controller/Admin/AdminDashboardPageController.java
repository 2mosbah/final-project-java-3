/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminDashboardPageController implements Initializable {

    @FXML
    private Label headerTextLabel;
    @FXML
    private Label patientsLabel;
    @FXML
    private Label appointmentsLabel;
    @FXML
    private Label logoutLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logoutHandle(MouseEvent event) throws IOException {
        ViewManager.closeAdminDashboardPage();
        ViewManager.openAdminLoginPage();
    }

    @FXML
    private void patientHandle(MouseEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToPatientsScene();
    }

    @FXML
    private void appointmentsHandle(MouseEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
    }
    
}
