/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.Appointments;
import Model.Bokkedappointments;
import Model.Users;
import View.ViewManager;
import finalproject.FinalProject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class PatientShowBookedAppointmentsController implements Initializable {

    @FXML
    private AnchorPane dashboardNav;
    @FXML
    private Label headerTextLabel;
    @FXML
    private VBox vBox;
    @FXML
    private Button showFreeAppointments;
    @FXML
    private Button showBookedAppointments;
    @FXML
    private Button showFinishedAppointment;
    @FXML
    private Button logout;
    @FXML
    private TableView<Bokkedappointments> tableView;
    @FXML
    private TableColumn<Bokkedappointments, Integer> tcId;
    @FXML
    private TableColumn<Integer, Integer> tcAppId;
    @FXML
    private TableColumn<Integer, Integer> tcUserId;
    @FXML
    private TableColumn<Bokkedappointments, String> tcStatus;
    @FXML
    private TableColumn<Bokkedappointments, String> tcComment;

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
    private void showFreeAppointmentsHandle(ActionEvent event) throws IOException {
        ViewManager.patientDashboardPage.ChangeSceneToShowFreeApp();
    }

    @FXML
    private void showBookedAppointmentsHandle(ActionEvent event) {

        tableView.getItems().clear();
        Users user = new Users(FinalProject.userId);

        Query q = em.createNamedQuery("Bokkedappointments.findByUserIdAndStat");
        q.setParameter("userId", user);
        q.setParameter("status", "waiting");
        List<Bokkedappointments> result = q.getResultList();
        tableView.getItems().addAll(result);

    }

    @FXML
    private void showFinishedAppointmentHandle(ActionEvent event) {
        tableView.getItems().clear();
        Users user = new Users(FinalProject.userId);

        Query q = em.createNamedQuery("Bokkedappointments.findByUserIdAndStat");
        q.setParameter("userId", user);
        q.setParameter("status", "finished");
        List<Bokkedappointments> result = q.getResultList();
        tableView.getItems().addAll(result);

    }

    @FXML
    private void logoutHandle(ActionEvent event) throws IOException {
        ViewManager.closePatientDashboardPage();
        ViewManager.openPatientLoginPage();
    }

}
