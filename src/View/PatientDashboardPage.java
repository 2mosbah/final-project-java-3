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

    private final Scene PatientDashboardScene;

    public PatientDashboardPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource("PatientFXML/PatientDashboardPage.fxml"));
        Parent root = loader.load();
        PatientDashboardScene = new Scene(root);

        this.setTitle("Patient Dashboard Page");
        this.setScene(PatientDashboardScene);
        this.show();
    }

}
