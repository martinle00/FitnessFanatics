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
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author galstern
 */
public class MentalPageController implements Initializable{
    
    Connection conn;
    Connection conn1;
    Statement st = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    @FXML
    public Button homepageButton;
    
    @FXML
    public Button foodButton;
    
    @FXML
    public Button aerobicsButton;
    
    @FXML
    public Button resistanceButton;
    
    @FXML
    public Button attendanceButton;
    
    @FXML
    public Button medicalButton;
    
    @FXML 
    public Slider slider1;
    
    @FXML 
    public Slider slider2;
    
    @FXML 
    public Slider slider3;
    
    @FXML 
    public Slider slider4;
    
   
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
    public void toMedicalPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) medicalButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "MedicalPage.fxml");
    }
    
    PreparedStatement ps= null;
    ResultSet rs = null;
    
    
    @FXML
    public void getResults(ActionEvent event) throws SQLException {
        
        String selectQuery = "SELECT DATE FROM MENTAL;";  
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        if(!rs.next()) {
        
        int value1 = (int) slider1.getValue();
        int value2 = (int) slider2.getValue();
        int value3 = (int) slider3.getValue();
        int value4 = (int) slider4.getValue();
        int sum = value1 + value2 + value3 + value4;
        
        String query = "INSERT OR IGNORE INTO MENTAL (DATE, ANSWER)"
                    + "VALUES (?,?);";
        try{
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query); 
            ps.setString(1,Helper.timeStamp());
            ps.setInt(2,sum);
            
            if(sum > 0 && sum <= 50) {
                alertBox("It would be best to visit your local psychologist. You can get through this.",null,"You're having the worst day of your life?");   
            } if(sum > 50 && sum <= 100) {
                alertBox("Try to medidate for 20 minutes, it will definitely make your day better!",null,"You're having a terrible day?");    
            } if(sum > 100 && sum <= 150) {
                alertBox("Go for a quick jog, it will give you the energy you need!",null,"You're having a bad day?");
            } if(sum > 150 && sum <= 200) {
                alertBox("Feel free to stay put, but try something different!",null,"You're having an average day?"); 
            } if(sum > 200 && sum <= 250) {
                alertBox("Never hurts to try a new sport!",null,"You're having an ok day!");   
            } if(sum > 250 && sum <= 300) {
                alertBox("Keep up the good habits, but make sure to find more ones!",null,"You're having a good day!");  
            } if(sum > 300 && sum <= 350) {
            alertBox("Keep up the good habits and stay motivated!",null,"You're almost having the best day!"); 
            } if(sum > 350 && sum <= 400) {
                alertBox("Keep up the good habits and stay motivated!",null,"You must be having the best day!");  
            }
         
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            ps.executeUpdate();
            ps.close();
        }
        
        } else {
        alertBox("You already submitted today, try tomorrow!",null,"Oops!");
        }
        //TODO: if statements
        // alertBox("You can now load the chart to see your results!",null,"Submitted for today");
        st.close();
        conn.close();
    }
          
    public static void alertBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
