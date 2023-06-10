/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.Users;
import Model.UsersJpaController;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class PatientRegPageController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button Reset;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private RadioButton maleGender;
    @FXML
    private RadioButton femaleGender;
    @FXML
    private Button goToLoginBtn;
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
    private ToggleGroup genderGroup;
    @FXML
    private Label roleLabel;
    @FXML
    private RadioButton adminRole;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private RadioButton patientRole;

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
    private void signUpHandle(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String email = txtEmail.getText();
        int phone = Integer.parseInt(txtPhone.getText());
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        Users users = new Users(username, password, fName, lName, age, email, phone, gender, role);
        usersJPA.create(users);

        if (patientRole.isSelected()) {
            successAlert("Patient Inserted", "Patient Inserted Successfully☻♥");
            ViewManager.closePatientRegPage();
            ViewManager.openPatientLoginPage();

        } else {
            successAlert("Admin Inserted", "Admin Inserted Successfully☻♥");
            ViewManager.closePatientRegPage();
            ViewManager.openAdminLoginPage();

        }

    }

    @FXML
    private void ResetHandle(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtPhone.setText("");
    }

    @FXML
    private void goToLoginBtnHandle(ActionEvent event) throws IOException {
        ViewManager.closePatientRegPage();
        ViewManager.openPatientLoginPage();
    }

    public void successAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
