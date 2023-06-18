/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.Bokkedappointments;
import Model.BokkedappointmentsJpaController;
import Model.Users;
import View.ViewManager;
import finalproject.FinalProject;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminUpdateAppController implements Initializable {

    @FXML
    private Button updateAppBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label appDateLabel;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Label appTimeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton freeRadio;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton bookedRadio;
    @FXML
    private ComboBox<Integer> timeCombo;
    @FXML
    private ComboBox<Integer> userIdCombo;

    Appointments oldApp;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    EntityManager em = emf.createEntityManager();
    AppointmentsJpaController appsJPA = new AppointmentsJpaController(emf);
    BokkedappointmentsJpaController bookAppJPA = new BokkedappointmentsJpaController(emf);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void selectedApp() {
        txtDate.getEditor().setText(oldApp.getAppointmentDate());
        timeCombo.setValue(Integer.valueOf(this.oldApp.getAppointmentTime().charAt(0) + ""));
        if (oldApp.getStatus().equals("free")) {
            statusGroup.selectToggle(freeRadio);
        } else {
            statusGroup.selectToggle(bookedRadio);
        }
    }

    @FXML
    private void updateAppHandle(ActionEvent event) throws Exception {
        LocalDate currentDate = LocalDate.now();
        String appDate = txtDate.getValue().toString();
        String appTime = timeCombo.getValue().toString() + " pm";
        String appDay = txtDate.getValue().getDayOfWeek().toString();
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText().toLowerCase();
        if (txtDate.getValue().isBefore(currentDate)) {

            warningAlert("Invalid Date", "Please select today's date or a future date.");

        } else {
            Appointments appointments = new Appointments(appDate, appDay, appTime, status);
            appointments.setId(oldApp.getId());
            appsJPA.edit(appointments);
            FinalProject.successAlert("Update Appointment", "Appointment Updated Successfully☻♥");

            if (bookedRadio.isSelected()) {
                Users user = new Users(Integer.valueOf(userIdCombo.getValue().toString()));
                Bokkedappointments bookedApp = new Bokkedappointments("waiting", appointments, user);
                bookAppJPA.create(bookedApp);
            }

            ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
        }
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAppointmentsScene();
    }

    public void setApp(Appointments selectedApptoUpdate) {
        this.oldApp = selectedApptoUpdate;

        Platform.runLater(() -> {
            selectedApp();
            timeComboData();
            userIdComboData();
        });
    }

    public Appointments getApp() {
        return this.oldApp;
    }

    @FXML
    private void timeComboHandle(ActionEvent event) {
    }

    @FXML
    private void timeComboData() {
        timeCombo.getItems().addAll(12, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @FXML
    private void userIdComboData() {
        Query query = em.createNamedQuery("Users.findByRole");
        query.setParameter("role", "patient");
        List<Users> result = query.getResultList();
        for (Users s : result) {
            userIdCombo.getItems().add(s.getId());
        }
    }

    @FXML
    private void userIdComboHandle(ActionEvent event) {

    }

    public void warningAlert(String title, String content) {
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle(title);
        warnAlert.setContentText(content);
        warnAlert.show();
    }
}
