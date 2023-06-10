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
public class AdminLoginPage extends Stage {

    private Scene adminLoginScene;

    public AdminLoginPage() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource("AdminFXML/AdminLoginPage.fxml"));
        Parent root = loader.load();
        adminLoginScene = new Scene(root);

        this.setTitle("Admin Login Page");
        this.setScene(adminLoginScene);
        this.show();
    }

}
