/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Martin
 */
public class RegistrationController implements Initializable {
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField passwordField;
    
    @FXML
    private TextField fNameField;
    
    @FXML
    private TextField lNameField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private TextField heightField;
    
    @FXML
    private TextField weightField;
    
    @FXML
    private Button Return;
    
    @FXML
    private Button submit;
    
    @FXML 
    public void toLogIn(ActionEvent event) throws IOException {
           Stage stage = (Stage) Return.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "LoginScreen.fxml");
    }
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @FXML
    public void submit(ActionEvent event) throws SQLException {
        String Email = emailField.getText();
        String Password = passwordField.getText();
        String FName = fNameField.getText();
        String LName = lNameField.getText();
        int Age = Integer.parseInt(ageField.getText());
        double Height = Double.parseDouble(heightField.getText());
        double Weight = Double.parseDouble(weightField.getText());
        
        String query = "INSERT OR IGNORE INTO Registration (Email, Password, FName, LName, Age, Height, Weight)"
                + "VALUES (?,?,?,?,?,?,?);";
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query);
            ps.setString(1, Email);
            ps.setString(2, Password);
            ps.setString(3, FName);
            ps.setString(4,LName);
            ps.setInt(5,Age);
            ps.setDouble(6,Height);
            ps.setDouble(7,Weight);
            
            Window owner = submit.getScene().getWindow();
            
            if(emailField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter Email!");
            }
            if(passwordField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter Password!");
        }
            if(fNameField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter your first name!");
    }
            if(lNameField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter your last name!");
            }
            if(ageField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter your age!");
            }
            if(heightField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter your height!");
            }
            if(weightField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter your weight!");
            }
            else{
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Profile Created!");
                Stage stage = (Stage) submit.getScene().getWindow();
                Helper helper = new Helper();
                helper.switcher(stage, "LoginScreenSimple.fxml");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ps.executeUpdate();
            ps.close();
        }
    }
    
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
