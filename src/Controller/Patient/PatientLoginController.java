/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.UsersJpaController;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

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
    UsersJpaController usersJPA = new UsersJpaController(emf);

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
    private void loginHandle(ActionEvent event) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        
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

}
