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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Martin
 */
public class ResistanceController implements Initializable {
    
    @FXML
    public Button homepageButton;
    
    @FXML
    public Button foodButton;
    
    @FXML
    public Button aerobicsButton;
    
    @FXML
    public Button mentalButton;
    
    @FXML
    public Button attendanceButton;
    
    @FXML
    public Button medicalButton;
    
    @FXML
    private TextField TypeField;

    @FXML
    private TextField RepsField;

    @FXML
    private TextField SetField;
    
    @FXML
    private TextField MassField;
    
    @FXML
    private TextField MonthlyMassField;

    @FXML
    private Button Submit;
    
    @FXML
    private ProgressBar massProgress;
    
    @FXML 
    public void toHomepage(ActionEvent event) throws IOException {
           Stage stage = (Stage) homepageButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "HomePage.fxml");
    }
    
    @FXML 
    public void toFoodPage(ActionEvent event) throws IOException {
           Stage stage = (Stage) foodButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "FoodPage.fxml");
    }
    
    @FXML 
    public void toAerobicsPage(ActionEvent event) throws IOException {
           Stage stage = (Stage) aerobicsButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "Aerobics.fxml");
    }
    
    @FXML
    public void toMentalPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) mentalButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "MentalPage.fxml");
    }
    
    @FXML 
    public void toAttendancePage(ActionEvent event) throws IOException {
           Stage stage = (Stage) attendanceButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "AttendancePage.fxml");
    }
    
    @FXML
    public void toMedicalPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) medicalButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "MedicalPage.fxml");
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    public void submit(ActionEvent event) throws SQLException {

        String Type = TypeField.getText();
        int Reps = Integer.parseInt(RepsField.getText());
        int Set = Integer.parseInt(SetField.getText());
        Double Mass = Double.parseDouble(MassField.getText());
        Double MassGoal = Double.parseDouble(MonthlyMassField.getText());

        String query = "INSERT OR IGNORE INTO Resistance (Type, Reps, Sets, Mass, MassMonthly)"
                + "VALUES (?,?,?,?,?);";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query);
            ps.setString(1, Type);
            ps.setInt(2, Reps);
            ps.setInt(3, Set);
            ps.setDouble(4, Mass);
            ps.setDouble(5, MassGoal);

            Window owner = Submit.getScene().getWindow();

            if (TypeField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter type of Aerobics Exercise");
                return;
            }
            if (RepsField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please Enter The Amount Of Reps");
                return;
            }
            if (SetField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please Enter The Amount of Sets");
                return;
            }
            if (MassField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please Enter the Amount of Mass");
                return;
            } 
            else {
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Data Recorded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            ps.executeUpdate();
            ps.close();
        }
        massProgress.setProgress(Double.parseDouble(MassField.getText()) / Double.parseDouble(MonthlyMassField.getText()));
        if(massProgress.getProgress() < 0.4){
            massProgress.setStyle("-fx-accent: red;");
        }
        else if (massProgress.getProgress() > 0.4 && massProgress.getProgress() < 0.6){
            massProgress.setStyle("-fx-accent: orange;");
        }
         else if (massProgress.getProgress() > 0.6 && massProgress.getProgress() < 0.8){
            massProgress.setStyle("-fx-accent: yellow;");
        }
         
        else if (massProgress.getProgress() > 0.8 && massProgress.getProgress() < 1){
            massProgress.setStyle("-fx-accent: #ADFF2F;");
        }
        
        else if (massProgress.getProgress() == 1){
            massProgress.setStyle("-fx-accent: green");
        }
       

        
    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
