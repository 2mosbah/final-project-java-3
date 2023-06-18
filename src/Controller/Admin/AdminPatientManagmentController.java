/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminPatientManagmentController implements Initializable {

    @FXML
    private Label headerTextLabel;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button addPatient;
    @FXML
    private TextField fNameTF;
    @FXML
    private Button searchByFNameBtn;
    @FXML
    private TableView<Users> tableView;
    @FXML
    private TableColumn<Users, Integer> idTC;
    @FXML
    private TableColumn<Users, String> usernameTC;
    @FXML
    private TableColumn<Users, String> passwordTC;
    @FXML
    private TableColumn<Users, String> fNameTC;
    @FXML
    private TableColumn<Users, String> lNameTC;
    @FXML
    private TableColumn<Users, Integer> ageTC;
    @FXML
    private TableColumn<Users, String> emailTC;
    @FXML
    private TableColumn<Users, Integer> phoneTC;
    @FXML
    private TableColumn<Users, String> genderTC;
    @FXML
    private Button viewPatients;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;

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

        idTC.setCellValueFactory(new PropertyValueFactory("id"));
        usernameTC.setCellValueFactory(new PropertyValueFactory("username"));
        passwordTC.setCellValueFactory(new PropertyValueFactory("password"));
        fNameTC.setCellValueFactory(new PropertyValueFactory("firstName"));
        lNameTC.setCellValueFactory(new PropertyValueFactory("lastName"));
        ageTC.setCellValueFactory(new PropertyValueFactory("age"));
        emailTC.setCellValueFactory(new PropertyValueFactory("email"));
        phoneTC.setCellValueFactory(new PropertyValueFactory("phone"));
        genderTC.setCellValueFactory(new PropertyValueFactory("gender"));

    }

    private void showSelectedPatient() {

        Users users = tableView.getSelectionModel().getSelectedItem();
        if (users != null) {
            idTC.setText(String.valueOf(users.getId()));
            usernameTC.setText(users.getUsername());
            passwordTC.setText(users.getPassword());
            fNameTC.setText(users.getFirstName());
            lNameTC.setText(users.getLastName());
            ageTC.setText(String.valueOf(users.getAge()));
            emailTC.setText(users.getEmail());
            phoneTC.setText(String.valueOf(users.getPhone()));
            genderTC.setText(users.getGender());

        }
    }

    @FXML
    private void dashboardBtnHandle(ActionEvent event) throws IOException {
        ViewManager.adminDashboardPage.ChangeSceneToAdminDashboardScene();
    }

    @FXML
    private void addPatientHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAdminAddNewPatientScene();
    }

    @FXML
    private void searchByFNameHandle(ActionEvent event) {
        tableView.getItems().clear();

        Query query = em.createNamedQuery("Users.findByFirstName");
        String findByFName = fNameTF.getText();
        query.setParameter("firstName", findByFName);
        List<Users> result = query.getResultList();
        if (!result.isEmpty()) {
            tableView.getItems().addAll(result);
        } else {
            warningAlert("Not Found !!", "First Name Not Exsist ");
        }
    }

    @FXML
    private void viewPatientsHandle(ActionEvent event) {
        tableView.getItems().clear();
        Query query = em.createNamedQuery("Users.findByRole");
        query.setParameter("role", "patient");
        List<Users> result = query.getResultList();
        tableView.getItems().addAll(result);
    }

    @FXML
    private void updateBtnHandle(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Users selectedPatientToUpdate = tableView.getSelectionModel().getSelectedItem();
            ViewManager.adminDashboardPage.ChangeSceneToAdminUpdatePatientScene(selectedPatientToUpdate);

        } else {
            warningAlert("Select A Patient", "Please Select A Patient From The Table View");
        }
        tableView.getItems().clear();
    }

    @FXML
    private void deleteBtnHandle(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Users delUser = tableView.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Delete Patient");
            deleteConfirmAlert.setContentText("Are You Sure You Want To Delete This Patient?");

            Optional<ButtonType> option = deleteConfirmAlert.showAndWait();

            try {
                if (option.get() == ButtonType.OK) {

                    usersJPA.destroy(delUser.getId());
                    successAlert("Delete Patient", "Patient Deleted Successfully☻♥");

                } else {
                    deleteConfirmAlert.close();
                }

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AdminPatientManagmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            warningAlert("Select A Patient", "Please Select A Patient From The Table View");
        }
        viewPatientsHandle(event);
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

}
