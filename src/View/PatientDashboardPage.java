/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class PatientDashboardPage extends Stage {

    private final Scene patientDashboardScene;
    private final Scene patientShowBookedAppScene;
    
    public PatientDashboardPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientFXML/PatientDashboardPage.fxml"));
        Parent root = loader.load();
        patientDashboardScene = new Scene(root);

        FXMLLoader ShowBookedAppLoader = new FXMLLoader(getClass().getResource("PatientFXML/PatientShowBookedAppointments.fxml"));
        Parent ShowBookedAppRoot = ShowBookedAppLoader.load();
        patientShowBookedAppScene = new Scene(ShowBookedAppRoot);
        
       
        this.setTitle("Patient Dashboard Page");
        this.setScene(patientDashboardScene);
        this.show();
    }

    public void ChangeSceneToShowFreeApp() {
        this.setScene(patientDashboardScene);
        this.setTitle("Show Free Appointments");
    }

    public void ChangeSceneToShowBookedApp() {
        this.setScene(patientShowBookedAppScene);
        this.setTitle("Show Booked Appointments");
    }
   

}
