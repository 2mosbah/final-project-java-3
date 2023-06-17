/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.Users;
import View.ViewManager;
import finalproject.FinalProject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PatientLoginController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblLogin;
    @FXML
    private Button loginBtn;
    @FXML
    private Button adminLogin;
    @FXML
    private Button registerBtn;
    @FXML
    private Label headtxt;
    @FXML
    private AnchorPane blueBack;
    @FXML
    private AnchorPane whiteBack;
    @FXML
    private ImageView nursepng;
    @FXML
    private AnchorPane nusrdebackGround;
    @FXML
    private ImageView nurseimg;
    @FXML
    private ImageView userpng;
    @FXML
    private Button CloseBtn;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    EntityManager em = emf.createEntityManager();

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
    private void txtUserNameHandle(ActionEvent event) {
    }

    @FXML
    private void txtPasswordHandle(ActionEvent event) {
    }
    
    @FXML
    private void loginHandle(ActionEvent event) throws SQLException, IOException {
        
        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        Query query = em.createNamedQuery("Users.findByRole");
        query.setParameter("role", "patient");
        List<Users> rs = query.getResultList();

        if (password.isEmpty() || username.isEmpty()) {
            warningAlert("Error", "Please enter username and password");
            return;
        }

        boolean isLoggedIn = false;
        for (Users user : rs) {
            if (password.equals(user.getPassword()) && username.equals(user.getUsername())) {
                FinalProject.userId = user.getId();
                isLoggedIn = true;
                break;
            }
        }

        if (isLoggedIn) {
            successAlert("Login", "Logged In Successfully");
            ViewManager.openPatientDashboardPage();
            ViewManager.closePatientLoginPage();
            System.out.println("User Id: " + FinalProject.userId);
        } else {
            warningAlert("Error", "Invalid username or password");
        }
        reset();
    }

    @FXML
    private void adminLoginHandle(ActionEvent event) throws IOException {
        ViewManager.closePatientLoginPage();
        ViewManager.openAdminLoginPage();
    }

    @FXML
    private void registerBtnHandle(ActionEvent event) throws IOException {
        ViewManager.closePatientLoginPage();
        ViewManager.openPatientRegPage();
    }

    @FXML
    private void CloseBtnHandle(ActionEvent event) {
        ViewManager.closePatientLoginPage();
    }

    public void successAlert(String title, String content) {
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

    public void reset() {
        txtUserName.setText("");
        txtPassword.setText("");
    }

}
