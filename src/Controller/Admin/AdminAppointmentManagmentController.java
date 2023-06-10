/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import static Controller.Admin.AdminPatientManagmentController.selectedPatientToUpdate;
import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.Users;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
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
public class AdminAppointmentManagmentController implements Initializable {

    @FXML
    private Button dashboardBtn;
    @FXML
    private Button addAppointments;
    @FXML
    private TextField fNameTF;
    @FXML
    private Button searchByFNameBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
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
    private AnchorPane dashboardNav;
    @FXML
    private Label headerTextLabel;
    @FXML
    private VBox vBox;
    @FXML
    private Button showFreeAppointments;
    @FXML
    private Button showBookedAppointments;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    EntityManager em = emf.createEntityManager();
    AppointmentsJpaController appsJPA = new AppointmentsJpaController(emf);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
    private void dashboardBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAdminDashboardScene();
    }

    @FXML
    private void addAppointmentsHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAdminAddAppScene();
    }

    @FXML
    private void searchByFNameHandle(ActionEvent event) {
//        Query query = em.createNamedQuery("Appointments.findByAppointmentTime");
//        String findByFName = fNameTF.getText();
//        query.setParameter("firstName", findByFName);
//        List<Appointments> result = query.getResultList();
//        tableView.getItems().addAll(result);
    }

    @FXML
    private void updateBtnHandle(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {


            ViewManager.adminDashboardPage.ChangeSceneToAdminUpdateAppScene();

        } else {
            warningAlert("Select An Appointment", "Please Select An Appointment From The Table View");

        }
    }

    @FXML
    private void deleteBtnHandle(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Appointments delAppointment = tableView.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Delete Appintment");
            deleteConfirmAlert.setContentText("Are You Sure You Want To Delete This Appointment?");

            Optional<ButtonType> option = deleteConfirmAlert.showAndWait();

            try {
                if (option.get() == ButtonType.OK) {

                    appsJPA.destroy(delAppointment.getId());
                    successAlert("Delete Appointment", "Appointment Deleted Successfully☻♥");

                } else {
                    deleteConfirmAlert.close();
                }

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AdminPatientManagmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            warningAlert("Select An Appointment", "Please Select An Appointment From The Table View");
        }
        showFreeAppointmentsHandle(event);
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
        ViewManager.adminDashboardPage.ChangeSceneToAdminBokkedAppScene();
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
