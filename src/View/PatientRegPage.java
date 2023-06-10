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
public class PatientRegPage extends Stage {

    private final Scene patientRegScene;

    public PatientRegPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource("PatientFXML/PatientRegPage.fxml"));
        Parent root = loader.load();
        patientRegScene = new Scene(root);

        this.setTitle("Patient Register Page");
        this.setScene(patientRegScene);
        this.show();
    }

}
