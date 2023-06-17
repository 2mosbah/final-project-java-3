/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.Bokkedappointments;
import Model.BokkedappointmentsJpaController;
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
import javafx.scene.control.Alert;
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
public class PatientDashboardPageController implements Initializable {

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
    private TableView<Appointments> tableView;
    @FXML
    private TableColumn<Appointments, Integer> idTC;
    @FXML
    private TableColumn<Appointments, String> appDateTC;
    @FXML
    private TableColumn<Appointments, String> appDayTC;
    @FXML
    private TableColumn<Appointments, String> appTimeTC;
    @FXML
    private TableColumn<Appointments, String> statusTC;
    @FXML
    private Button bookAnAppointment;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    EntityManager em = emf.createEntityManager();
    AppointmentsJpaController appsJPA = new AppointmentsJpaController(emf);
    BokkedappointmentsJpaController bookAppJPA = new BokkedappointmentsJpaController(emf);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idTC.setCellValueFactory(new PropertyValueFactory("id"));
        appDateTC.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        appDayTC.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        appTimeTC.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusTC.setCellValueFactory(new PropertyValueFactory("status"));

    }

    @FXML
    private void showFreeAppointmentsHandle(ActionEvent event) {
        tableView.getItems().clear();
        Query query = em.createNamedQuery("Appointments.findByStatus");
        query.setParameter("status", "free");
        List<Appointments> result = query.getResultList();
        tableView.getItems().addAll(result);
    }

    @FXML
    private void showBookedAppointmentsHandle(ActionEvent event) {
        ViewManager.patientDashboardPage.ChangeSceneToShowBookedApp();
    }

    @FXML
    private void showFinishedAppointmentHandle(ActionEvent event) {
        ViewManager.patientDashboardPage.ChangeSceneToShowBookedApp();
    }

    @FXML
    private void logoutHandle(ActionEvent event) throws IOException {
        ViewManager.closePatientDashboardPage();
        ViewManager.openPatientLoginPage();
    }

    @FXML
    private void bookAnAppointmentHandle(ActionEvent event) throws Exception {

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Appointments app = tableView.getSelectionModel().getSelectedItem();
            int id = app.getId();
            String appDate = app.getAppointmentDate();
            String appTime = app.getAppointmentTime();
            String appDay = app.getAppointmentDay();
            String status = "bokked";

            Users user = new Users(FinalProject.userId);
            Appointments appointments = new Appointments(appDate, appDay, appTime, status);
            Bokkedappointments bookedApp = new Bokkedappointments("waiting", appointments, user);
            appointments.setId(id);

            appsJPA.edit(appointments);
            bookAppJPA.create(bookedApp);

            successAlert("Booked Success", "An Appointment Booked Successfully☻♥");

        } else {

            warningAlert("Select An Appointment", "Please Select An Appointment From The Table View");

        }
    }

    public void warningAlert(String title, String content) {
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle(title);
        warnAlert.setContentText(content);
        warnAlert.show();
    }

    public void successAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
