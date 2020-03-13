/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author galstern
 */
public class HomePageController implements Initializable {
    
    @FXML
    public Button foodButton;
    
    @FXML
    public Button aerobicButton;
    
    @FXML
    public Button resistanceButton;
    
    @FXML
    public Button attendanceButton;
    
    @FXML
    public Button mentalButton;
    
    @FXML
    public Button medicalButton;
    
    @FXML
    public TextField stepsCountGoal;
    
    @FXML
    public TextField stairsCountGoal;
    
    @FXML
    public Label lbl;
   
    @FXML
    public ProgressBar stepsCount;
    //stepsCounter(500);
    
    @FXML
    public ProgressBar stairsCount;
    
    @FXML
    public ProgressBar BMI;
    
    @FXML
    public ProgressBar restingHeartrate;
    
    @FXML
    public Label lblSteps;
    
    @FXML
    public Label lblStairs;
    
    @FXML
    public Label lblBMI;
    
    @FXML
    public Label lblSleep;
    
    @FXML
    public Label lblRatio;
    
    @FXML
    public Label lblHeartrate;
    
    Connection conn;
    PreparedStatement ps = null;
    
    @FXML
    public TextField Ratio;
    
    @FXML
    public TextField SleepField;
    
    @FXML
    public ProgressBar RatioProgress;
    
    @FXML
    public ProgressBar SleepProgress;
    
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML 
    public void toFoodPage(ActionEvent event) throws IOException {
           Stage stage = (Stage) foodButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "FoodPage.fxml");
    }
    
    @FXML 
    public void toAerobicPage(ActionEvent event) throws IOException {
           Stage stage = (Stage) aerobicButton.getScene().getWindow();
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
    
    @FXML
    public void set(ActionEvent event) throws SQLException {
        //update steps
        double stepsDouble = PersistantDataHelper.steps;
        double stepsGoal = Double.parseDouble(stepsCountGoal.getText());
                
        PersistantDataHelper.decimal = stepsDouble/stepsGoal; 
        stepsCount.setProgress(PersistantDataHelper.decimal);
        if (PersistantDataHelper.decimal < 0.4) {
              stepsCount.setStyle("-fx-accent: red;"); 
           } if (PersistantDataHelper.decimal > 0.4 && PersistantDataHelper.decimal <0.6) {
              stepsCount.setStyle("-fx-accent: orange;"); 
           } if (PersistantDataHelper.decimal > 0.4 && PersistantDataHelper.decimal<0.6) {
              stepsCount.setStyle("-fx-accent: yellow;"); 
           } if (PersistantDataHelper.decimal > 0.8) {
              stepsCount.setStyle("-fx-accent: green;"); 
           }
           
        //update stairs
        double stairsDouble = PersistantDataHelper.stairs;
        double stairsGoal = Double.parseDouble(stairsCountGoal.getText());
                
        PersistantDataHelper.decimal2 = stairsDouble/stairsGoal; 
        stairsCount.setProgress(PersistantDataHelper.decimal2);
        if (PersistantDataHelper.decimal2 < 0.4) {
              stairsCount.setStyle("-fx-accent: red;");
           } if (PersistantDataHelper.decimal2 > 0.4 && PersistantDataHelper.decimal2 <0.6) {
              stairsCount.setStyle("-fx-accent: orange;"); 
           } if (PersistantDataHelper.decimal2 > 0.4 && PersistantDataHelper.decimal2<0.6) {
              stairsCount.setStyle("-fx-accent: yellow;"); 
           } if (PersistantDataHelper.decimal2 > 0.8) {
              stairsCount.setStyle("-fx-accent: green;"); 
           }
           
       //Ratio
        double ratioGoal = Double.parseDouble(Ratio.getText());
        double ratioGoal1 = ratioGoal/100;
        PersistantDataHelper.ratioFinal = PersistantDataHelper.ratio/ratioGoal1;
        RatioProgress.setProgress(PersistantDataHelper.ratioFinal);
         BMI.setProgress(PersistantDataHelper.ratioFinal);
           if (PersistantDataHelper.ratioFinal < 0.4) {
              RatioProgress.setStyle("-fx-accent: red;");
           } if (PersistantDataHelper.ratioFinal > 0.4 && PersistantDataHelper.ratioFinal <0.6) {
              RatioProgress.setStyle("-fx-accent: orange;"); 
           } if (PersistantDataHelper.ratioFinal > 0.4 && PersistantDataHelper.ratioFinal <0.6) {
              RatioProgress.setStyle("-fx-accent: yellow;"); 
           } if (PersistantDataHelper.ratioFinal > 0.8) {
              RatioProgress.setStyle("-fx-accent: green;"); 
           }
        
        //Sleep
        double sleepGoal = Double.parseDouble(SleepField.getText());
        PersistantDataHelper.sleepFinal = PersistantDataHelper.sleep/sleepGoal;
        SleepProgress.setProgress(PersistantDataHelper.sleepFinal);
         if (PersistantDataHelper.sleepFinal > 0.4 && PersistantDataHelper.sleepFinal >= 0.6) {
              SleepProgress.setStyle("-fx-accent: green;");
           } if (PersistantDataHelper.sleepFinal > 0.2 && PersistantDataHelper.sleepFinal <= 0.4) {
              SleepProgress.setStyle("-fx-accent: orange;"); 
           } if (PersistantDataHelper.sleepFinal > 0.6 && PersistantDataHelper.sleepFinal< 0.8) {
              SleepProgress.setStyle("-fx-accent: orange;"); 
           } if (PersistantDataHelper.sleepFinal >= 0.8 || PersistantDataHelper.sleepFinal <= 0.2) {
              SleepProgress.setStyle("-fx-accent: red;");  
           }
        
    }

    

    
    public void stairsCounter(int steps) {
        stairsCount.setProgress(steps);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Steps initialize
        stepsCount.setProgress(PersistantDataHelper.decimal);
        stairsCount.setProgress(PersistantDataHelper.decimal2);
        
        //Name initialize
        String query = "SELECT FNAME FROM REGISTRATION;";
        try{ 
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String rs1 = rs.getString(1);
            System.out.println(rs1);
            lbl.setText(rs1);
            st.close();
            conn.close();
        } catch (SQLException e) {    
            e.printStackTrace();
        } 
        
        //Heartrate initialize
        String query1 = "SELECT AGE FROM REGISTRATION;";
        try{
          Connection conn1 = DriverManager.getConnection("jdbc:sqlite:database.db");  
          Statement st = conn1.createStatement();
          ResultSet rs = st.executeQuery(query1);
          double rs1 = Double.parseDouble(rs.getString(1));
          double maxHeartrate = 220.00 - rs1;
          double currentHeartrate = PersistantDataHelper.heartRate;
          double decimal3 = currentHeartrate/maxHeartrate;
          restingHeartrate.setProgress(decimal3);
          if (decimal3 < 0.4) {
              restingHeartrate.setStyle("-fx-accent: green;");
           } if (decimal3 > 0.4 && decimal3 <0.6) {
              restingHeartrate.setStyle("-fx-accent: yellow;"); 
           } if (decimal3 > 0.4 && decimal3<0.6) {
              restingHeartrate.setStyle("-fx-accent: orange;"); 
           } if (decimal3 > 0.8) {
              restingHeartrate.setStyle("-fx-accent: red;"); 
           }
          st.close();
          conn1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        //BMI calculation
        String query2 = "SELECT HEIGHT, WEIGHT FROM REGISTRATION;";
        try {
           Connection conn2 = DriverManager.getConnection("jdbc:sqlite:database.db");  
           Statement st = conn2.createStatement();
           ResultSet rs = st.executeQuery(query2);
           double i = rs.getInt(1);
           double j = rs.getInt(2);

           double height = i/100;
     
           PersistantDataHelper.bmi = j / (height*height);
           
           double BMIdecimal = (PersistantDataHelper.bmi)/60; //max possible BMI
           BMI.setProgress(BMIdecimal);
           if (BMIdecimal < 0.4) {
              BMI.setStyle("-fx-accent: green;");
           } if (BMIdecimal > 0.4 && BMIdecimal <0.6) {
              BMI.setStyle("-fx-accent: yellow;"); 
           } if (BMIdecimal > 0.4 && BMIdecimal <0.6) {
              BMI.setStyle("-fx-accent: orange;"); 
           } if (BMIdecimal > 0.8) {
              BMI.setStyle("-fx-accent: red;"); 
           }
           st.close();
           conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Initialize labels
        String steps = Integer.toString(PersistantDataHelper.steps);
        String stairs = Integer.toString(PersistantDataHelper.stairs);
        double rounded1 = Math.round(PersistantDataHelper.bmi *100)/100.0;
        String bmi = Double.toString(rounded1);
        double love = PersistantDataHelper.ratio*100;
        double rounded2 = Math.round(love *100)/100.0;
        String ratio = Double.toString(rounded2);
        double love1 = PersistantDataHelper.sleep;
        double rounded3 = Math.round(love1 *100)/100.0;
        String sleep = Double.toString(rounded3);
        String heartRate = Integer.toString(PersistantDataHelper.heartRate);
        lblSteps.setText(steps);
        lblStairs.setText(stairs);
        lblHeartrate.setText(heartRate);
        lblBMI.setText(bmi);
        lblSleep.setText(sleep);
        lblRatio.setText(ratio);
        
    }
    
   
    
    /**
     *
     * @param event
     * @throws IOException
     */
}
