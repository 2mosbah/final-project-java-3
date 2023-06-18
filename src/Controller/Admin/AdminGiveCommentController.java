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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminGiveCommentController implements Initializable {

    @FXML
    private TextArea txtArea;
    @FXML
    private Button commentBtn;
    @FXML
    private Button cancelBtn;

    Bokkedappointments oldBookedApp;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalProjectPU");
    EntityManager em = emf.createEntityManager();
    BokkedappointmentsJpaController bookAppJPA = new BokkedappointmentsJpaController(emf);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void commentBtnHandle(ActionEvent event) throws Exception {
        Users userId = oldBookedApp.getUserId();
        Appointments appointmentId = oldBookedApp.getAppointmentId();
        String status = "finished";
        String comment = txtArea.getText().trim();

        Bokkedappointments bookApp = new Bokkedappointments(status, comment, appointmentId, userId);
        bookApp.setId(oldBookedApp.getId());
        bookAppJPA.edit(bookApp); 

        FinalProject.successAlert("Adding Comment", "Comment Added Successfully☻♥");
        
        ViewManager.adminDashboardPage.ChangeSceneToAdminBokkedAppScene();
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        ViewManager.adminDashboardPage.ChangeSceneToAdminBokkedAppScene();
    }

    public void setComment(Bokkedappointments selectedAppToComment) {
        this.oldBookedApp = selectedAppToComment;
    }

    public Bokkedappointments getComment() {
        return this.oldBookedApp;
    }

}
