package View;

import Controller.Admin.AdminUpdatePatientController;
import Model.Users;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author PC
 */
public class AdminDashboardPage extends Stage {

    private final Scene adminDashboardScene;
    private final Scene patientsScene;
    private final Scene appointmentsScene;
    private final Scene adminAddNewPatientScene;
    private final Scene adminUpdatePatientScene;
    private final Scene adminAddAppScene;
    private final Scene adminUpdateAppScene;
    private final Scene adminBokkedAppScene;
    AdminUpdatePatientController controller;

    public AdminDashboardPage() throws IOException {

        FXMLLoader adminDashboardLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminDashboardPage.fxml"));
        Parent adminDashboardroot = adminDashboardLoader.load();
        adminDashboardScene = new Scene(adminDashboardroot);

        FXMLLoader patientsManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminPatientManagment.fxml"));
        Parent patientsManagmentRoot = patientsManagmentLoader.load();
        patientsScene = new Scene(patientsManagmentRoot);

        FXMLLoader appointmentsManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminAppointmentManagment.fxml"));
        Parent appointmentsManagmentRoot = appointmentsManagmentLoader.load();
        appointmentsScene = new Scene(appointmentsManagmentRoot);

        FXMLLoader adminAddNewPatientLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminAddNewPatient.fxml"));
        Parent adminAddNewPatientRoot = adminAddNewPatientLoader.load();
        adminAddNewPatientScene = new Scene(adminAddNewPatientRoot);

        FXMLLoader adminUpdatePatientLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminUpdatePatient.fxml"));
        controller = new AdminUpdatePatientController();
        adminUpdatePatientLoader.setController(controller);
        Parent adminUpdatePatientRoot = adminUpdatePatientLoader.load();
        adminUpdatePatientScene = new Scene(adminUpdatePatientRoot);

        FXMLLoader adminAddAppLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminAddAppPage.fxml"));
        Parent adminAddAppRoot = adminAddAppLoader.load();
        adminAddAppScene = new Scene(adminAddAppRoot);

        FXMLLoader adminUpdateAppLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminUpdateApp.fxml"));
        Parent adminUpdateAppRoot = adminUpdateAppLoader.load();
        adminUpdateAppScene = new Scene(adminUpdateAppRoot);

        FXMLLoader adminBokkedAppLoader = new FXMLLoader(getClass().getResource("AdminFXML/AdminBokkedApp.fxml"));
        Parent adminBokkedAppRoot = adminBokkedAppLoader.load();
        adminBokkedAppScene = new Scene(adminBokkedAppRoot);

        this.setScene(adminDashboardScene);
        this.setTitle("Admin Dashboard Page");
    }

    public void ChangeSceneToPatientsScene() {
        this.setScene(patientsScene);
        this.setTitle("Patient Managment");
    }

    public void ChangeSceneToAppointmentsScene() {
        this.setScene(appointmentsScene);
        this.setTitle("Appointment Managment");
    }

    public void ChangeSceneToAdminDashboardScene() {
        this.setScene(adminDashboardScene);
        this.setTitle("Admin Dashboard Page");
    }

    public void ChangeSceneToAdminAddNewPatientScene() {
        this.setScene(adminAddNewPatientScene);
        this.setTitle("Add New Patient");
    }

    public void ChangeSceneToAdminUpdatePatientScene(Users selectedPatientToUpdate) {
        System.out.println("CC "+controller);
        System.out.println("Selected"  + selectedPatientToUpdate);
        controller.setPatient(selectedPatientToUpdate);
        this.setScene(adminUpdatePatientScene);
        this.setTitle("Update Patient");
    }

    public void ChangeSceneToAdminAddAppScene() {
        this.setScene(adminAddAppScene);
        this.setTitle("Add New Appointment");
    }

    public void ChangeSceneToAdminUpdateAppScene() {
        this.setScene(adminUpdateAppScene);
        this.setTitle("Update Appointment");
    }

    public void ChangeSceneToAdminBokkedAppScene() {
        this.setScene(adminBokkedAppScene);
        this.setTitle("Bokked Appointments");
    }

}
