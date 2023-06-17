/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Appointments;
import Model.AppointmentsJpaController;
import View.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminAddAppPageController implements Initializable {

    @FXML
    private Button signUpBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label appDateLabel;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Label appTimeLabel;
    private TextField txtAppTime;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton freeRadio;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton bokkedRadio;
    @FXML
    private ComboBox<Integer> timeCombo;
    
    
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    AppointmentsJpaController appsJPA = new AppointmentsJpaController(emf);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void signUpHandle(ActionEvent event) {

        String appDate = txtDate.getValue().toString();
        String appTime = txtAppTime.getText();
        String appDay = txtDate.getValue().getDayOfWeek().toString();
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText().toLowerCase();

        Appointments appointments = new Appointments(appDate, appDay, appTime, status);
        appsJPA.create(appointments);

        successAlert("Add Appointment", "Appointment Added Successfully☻♥");
        ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
    }

    public void successAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void timeComboHandle(ActionEvent event) {
    }

}
