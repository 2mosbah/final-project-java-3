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
public class PatientLoginPage extends Stage {

    private final Scene patientLoginScene;

    public PatientLoginPage() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource("PatientFXML/PatientLogin.fxml"));
        Parent root = loader.load();
        patientLoginScene = new Scene(root);

        this.setTitle("Patient Login Page");
        this.setScene(patientLoginScene);
        this.show();

    }
}
