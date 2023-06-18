/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import View.ViewManager;
import finalproject.FinalProject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class AdminAddNewPatientController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtFirstName;
    @FXML
    private Button signUpBtn;
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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    UsersJpaController usersJPA = new UsersJpaController(emf);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signUpHandle(ActionEvent event) {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String email = txtEmail.getText();
        int phone = Integer.parseInt(txtPhone.getText());
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText().toLowerCase();
        String role = "patient";

        Users users = new Users(username, password, fName, lName, age, email, phone, gender, role);
        usersJPA.create(users);

        FinalProject.successAlert("Patient Inserted", "Patient Inserted Successfully☻♥");
        ViewManager.adminDashboardPage.ChangeSceneToPatientsScene();
         
        ResetHandle(event); 
        
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToPatientsScene();
    }

    private void ResetHandle(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtPhone.setText("");
    }
}
