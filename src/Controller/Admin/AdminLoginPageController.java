/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import View.ViewManager;
import finalproject.FinalProject;
import java.io.IOException;
import java.net.URL;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    private void loginHandle(ActionEvent event) throws IOException {

        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        Query query = em.createNamedQuery("Users.findByRole");
        query.setParameter("role", "admin");
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
            ViewManager.closeAdminLoginPage(); 
            ViewManager.openAdminDashboardPage();
            System.out.println("Admin Id: " + FinalProject.userId);
        } else {
            warningAlert("Error", "Invalid username or password");
        }
        reset();
    }


    @FXML
    private void CloseBtnHandle(ActionEvent event) throws IOException {
        ViewManager.closeAdminLoginPage();
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
