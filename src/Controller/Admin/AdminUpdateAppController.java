/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    private TextField txtAppTime;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton freeRadio;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton bookedRadio;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateAppHandle(ActionEvent event) {
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
    }
    
}
