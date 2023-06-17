/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import View.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminUpdatePatientController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtFirstName;
    @FXML
    private Button updatePatientBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private RadioButton maleGender;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femaleGender;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;

    Users oldPatient;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Platform.runLater(() -> );
    }

    public void selectedPatient() {

        txtUsername.setText(oldPatient.getUsername());
        txtPassword.setText(oldPatient.getPassword());
        txtFirstName.setText(oldPatient.getFirstName());
        txtLastName.setText(oldPatient.getLastName());
        txtAge.setText(String.valueOf(oldPatient.getAge()));
        txtEmail.setText(oldPatient.getEmail());
        txtPhone.setText(String.valueOf(oldPatient.getPhone()));

        if (oldPatient.getGender().equals("male")) {
            genderGroup.selectToggle(maleGender);
        } else {
            genderGroup.selectToggle(femaleGender);
        }

    }

    @FXML
    private void updatePatientBtnHandle(ActionEvent event) {
                
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToPatientsScene();
    }

    public void setPatient(Users selectedPatientToUpdate) {
        this.oldPatient = selectedPatientToUpdate;

        Platform.runLater(() -> {
            selectedPatient();
        });

    }

    public Users getPatient() {
        return this.oldPatient;
    }

}
