/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Bokkedappointments;
import Model.Users;
import View.ViewManager;
import finalproject.FinalProject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminBokkedAppController implements Initializable {

    @FXML
    private Button dashboardBtn;
    @FXML
    private TextField fNameTF;
    @FXML
    private Button searchByFNameBtn;
    @FXML
    private Button giveCommentBtn;
    @FXML
    private TableView<Bokkedappointments> tableView;
    @FXML
    private TableColumn<Bokkedappointments, Integer> tcId;
    @FXML
    private TableColumn<Bokkedappointments, Integer> tcAppId;
    @FXML
    private TableColumn<Bokkedappointments, Integer> tcUserId;
    @FXML
    private TableColumn<Bokkedappointments, String> tcStatus;
    @FXML
    private TableColumn<Bokkedappointments, String> tcComment;
    @FXML
    private AnchorPane dashboardNav;
    @FXML
    private Label headerTextLabel;
    @FXML
    private VBox vBox;
    @FXML
    private Button freeAppointments;
    @FXML
    private Button showBokkedAppointments;
    @FXML
    private Button bookedAppointments;

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

        tcId.setCellValueFactory(new PropertyValueFactory("id"));
        tcAppId.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        tcUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tcStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tcComment.setCellValueFactory(new PropertyValueFactory("doctorComment"));

    }

    @FXML
    private void dashboardBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAdminDashboardScene();
    }

    @FXML
    private void searchByFNameHandle(ActionEvent event) {

        Query query = em.createNamedQuery("Users.findByFirstName");
        String findByFName = fNameTF.getText();
        query.setParameter("firstName", findByFName);
        List<Users> res = query.getResultList();

        System.out.println("Size is: " + res.size());
        int userId = 0;
        for (Users s : res) {
            userId = s.getId();
        }
        System.out.println("Id Is: " + userId);
        Users user = new Users(userId);
        Query query1 = em.createNamedQuery("Bokkedappointments.findByUserId");
        query1.setParameter("userId", user);
        List<Bokkedappointments> result = query1.getResultList();
        tableView.getItems().clear();
        if (!result.isEmpty()) {
            tableView.getItems().addAll(result);
        } else {
            warningAlert("Not Found!!", "First Name Not Found or Patient with this fname doesn't have Booked Appointments");
        }
    }

    @FXML
    private void giveCommentHandle(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
               Bokkedappointments  selectedAppToComment = tableView.getSelectionModel().getSelectedItem();
               ViewManager.adminDashboardPage.ChangeSceneToAdminGiveCommentScene(selectedAppToComment);
        } else {
            warningAlert("Select An Appointment", "Please Select An Appointment From The Table View");
        }
    }

    @FXML
    private void freeAppointmentsHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
    }

    @FXML
    private void showBokkedAppointmentsHandle(ActionEvent event) {

        tableView.getItems().clear();
        Query query = em.createNamedQuery("Bokkedappointments.findAll");
        List<Bokkedappointments> result = query.getResultList();
        tableView.getItems().addAll(result);

    }

    @FXML
    private void bookedAppointmentsHandle(ActionEvent event) {
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
