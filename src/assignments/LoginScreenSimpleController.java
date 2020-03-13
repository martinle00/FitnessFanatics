/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import static assignments.LoginScreenController.alertBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author galstern
 */

public class LoginScreenSimpleController implements Initializable {

    @FXML
    public Button loginButton;
    
    @FXML 
    public Button registerButton;
    
    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;
    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    public boolean validateFields(){
        if(textEmail.getText().isEmpty() || textPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter an email and password.");
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.showAndWait();
            
            return false;
        }
        else {
            return true;
        }
    }
    
    @FXML
    public void actionLogin(ActionEvent event) throws IOException{
        
        String email = textEmail.getText();
        String password = textPassword.getText();

        if(validateFields()) {
            boolean userIsLegit = LoginDatabaseHelper.validateLogin(email, password);
            if (userIsLegit) {
                alertBox("Login Successful",null,"Success");
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Helper helper = new Helper();
                helper.switcher(stage, "HomePage.fxml");
                        
                        
                        //find a way to use the ActionEvent to get the current window
                        //TODO: getWindow
                        /*
                        Parent root = FXMLLoader.load(getClass().getResource());
                        stage.initStyle(StageStyle.DECORATED);
                        Scene scene = new Scene(root);
                        stage.setTitle("Homepage");
                        stage.setScene(scene);
                        stage.show();
                    */
            } else {
               alertBox("Please enter a valid Email and Password", null, "Failed");
            }
        }
    }
    
    @FXML
    public void actionRegister(ActionEvent event) throws IOException{
           Stage stage = (Stage) registerButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "RegistrationPageDocument.fxml");
    }
    
    public static void alertBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
}
