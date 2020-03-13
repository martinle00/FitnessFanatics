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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Martin
 */
public class AerobicsController implements Initializable {

    @FXML
    private TextField TypeField;

    @FXML
    private TextField DistanceField;

    @FXML
    private TextField TimeField;

    @FXML
    private TextField TimeGoalField;

    @FXML
    private TextField DistanceGoalField;

    @FXML
    private Button Submit;

    @FXML
    private ProgressBar DistanceProgress;

    @FXML
    private ProgressBar TimeProgress;

    @FXML
    public Button homepageButton;
    
    @FXML
    public Button foodButton;
    
    @FXML
    public Button resistanceButton;
    
    @FXML
    public Button attendanceButton;
    
    @FXML
    public Button mentalButton;
    
    @FXML
    public Button medicalButton;
 
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
    public void toResistancePage(ActionEvent event) throws IOException {
           Stage stage = (Stage) resistanceButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "Resistance.fxml");
    }
    
    @FXML 
    public void toAttendancePage(ActionEvent event) throws IOException {
           Stage stage = (Stage) attendanceButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "AttendancePage.fxml");
    }
    
    @FXML
    public void toMentalPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) mentalButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "MentalPage.fxml");
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
    private void submit(ActionEvent event) throws SQLException {

        String Type = TypeField.getText();
        Double Distance = Double.parseDouble(DistanceField.getText());
        Double Time = Double.parseDouble(TimeField.getText());
        Double TimeGoal = Double.parseDouble(TimeGoalField.getText());
        Double DistanceGoal = Double.parseDouble(DistanceGoalField.getText());

        String query = "INSERT OR IGNORE INTO Aerobics (TYPE, DISTANCE, TIME, TIMEGOAL, DISTANCEGOAL)"
                + "VALUES (?,?,?,?, ?);";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query);
            ps.setString(1, Type);
            ps.setDouble(2, Distance);
            ps.setDouble(3, Time);
            ps.setDouble(4, TimeGoal);
            ps.setDouble(5, DistanceGoal);

            Window owner = Submit.getScene().getWindow();

            if (TypeField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter type of Aerobics Exercise");
                return;
            }
            if (DistanceField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter distance");
                return;
            }
            if (TimeField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "Please enter time");
                return;
            } else {
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Data Recorded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.executeUpdate();
            ps.close();
        }

        DistanceProgress.setProgress(Double.parseDouble(DistanceField.getText()) / Double.parseDouble(DistanceGoalField.getText()));
        TimeProgress.setProgress(Double.parseDouble(TimeField.getText()) / Double.parseDouble(TimeGoalField.getText()));
        if(DistanceProgress.getProgress() < 0.4){
            DistanceProgress.setStyle("-fx-accent: red;");
        }
        else if (TimeProgress.getProgress() < 0.4){
            TimeProgress.setStyle("-fx-accent: red;");
        }
        else if (DistanceProgress.getProgress() > 0.4 && DistanceProgress.getProgress() < 0.6){
            DistanceProgress.setStyle("-fx-accent: orange;");
        }
        else if (TimeProgress.getProgress() > 0.4 && TimeProgress.getProgress() < 0.6){
            TimeProgress.setStyle("-fx-accent: orange;");
        }
         else if (DistanceProgress.getProgress() > 0.6 && DistanceProgress.getProgress() < 0.8){
            DistanceProgress.setStyle("-fx-accent: yellow;");
        }
         else if (TimeProgress.getProgress() > 0.6 && TimeProgress.getProgress() < 0.8){
            TimeProgress.setStyle("-fx-accent: yellow;");
        }
        else if (DistanceProgress.getProgress() > 0.8 && DistanceProgress.getProgress() < 1){
            DistanceProgress.setStyle("-fx-accent: #ADFF2F;");
        }
        else if (TimeProgress.getProgress() > 0.8 && TimeProgress.getProgress() < 1){
            DistanceProgress.setStyle("-fx-accent: #ADFF2F;");
        }
        else if (DistanceProgress.getProgress() == 1){
            DistanceProgress.setStyle("-fx-accent: green");
        }
        else if (TimeProgress.getProgress() == 1){
            TimeProgress.setStyle("-fx-accent: green");
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
