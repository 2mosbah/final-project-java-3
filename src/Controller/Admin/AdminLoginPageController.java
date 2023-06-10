/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

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

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminLoginPageController implements Initializable {

    @FXML
    private AnchorPane whiteBack;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblLogin;
    @FXML
    private Button loginBtn;
    @FXML
    private ImageView userpng;
    @FXML
    private ImageView nursepng;
    @FXML
    private Button registerBtn;
    @FXML
    private Button CloseBtn;

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
    private void loginHandle(ActionEvent event) {
    }

    @FXML
    private void registerBtnHandle(ActionEvent event) throws IOException {
        ViewManager.closeAdminLoginPage();
        ViewManager.openPatientRegPage();
    }

    @FXML
    private void CloseBtnHandle(ActionEvent event) throws IOException {
        ViewManager.closeAdminLoginPage();
        ViewManager.openPatientLoginPage();
    }

}
