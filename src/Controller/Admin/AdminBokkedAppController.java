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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminBokkedAppController implements Initializable {

    @FXML
    private Button dashboardBtn;
    @FXML
    private TextField fNameTF;
    @FXML
    private Button searchByFNameBtn;
    @FXML
    private Button giveCommentBtn;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> tcId;
    @FXML
    private TableColumn<?, ?> tcAppId;
    @FXML
    private TableColumn<?, ?> tcUserId;
    @FXML
    private TableColumn<?, ?> tcStatus;
    @FXML
    private TableColumn<?, ?> tcComment;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dashboardBtnHandle(ActionEvent event) {
    }

    @FXML
    private void searchByFNameHandle(ActionEvent event) {
    }

    @FXML
    private void giveCommentHandle(ActionEvent event) {
    }

    @FXML
    private void showFreeAppointmentsHandle(ActionEvent event) {
    }

    @FXML
    private void showBookedAppointmentsHandle(ActionEvent event) {
    }
    
}
