/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import static assignments.Helper.timeStamp;
import static assignments.LoginScreenController.alertBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JTextField;

/**
 *
 * @author galstern
 */
public class FoodPageControllerNew implements Initializable {
    
    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
     
    @FXML
    private TextField carbsField;
    
    @FXML
    private TextField proteinField;
    
    @FXML
    private TextField dairyField;
    
    @FXML
    private TextField fruitField;
    
    @FXML
    private TextField fatField;
    
    @FXML
    private Button submit;
    
    @FXML
    private Button view;
    
    @FXML
    public PieChart pieChart;
    
    //Menubar section
    
    @FXML
    public Button homepageButton;
    
    @FXML
    public Button aerobicsButton;
    
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
    
    //End of Menubar section
    
    public static String timeStamp() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String timenow = date.format(formatter);
        return timenow;
    }
    
    PreparedStatement ps= null;
    ResultSet rs = null;
    
    @FXML
    public void submit(ActionEvent event) throws SQLException {
        
        int carbs = Integer.parseInt(carbsField.getText());
        int protein = Integer.parseInt(proteinField.getText());
        int dairy = Integer.parseInt(dairyField.getText());
        int fruit = Integer.parseInt(fruitField.getText());
        int fat = Integer.parseInt(fatField.getText());
        
        String query = "INSERT OR IGNORE INTO FOOD (CARBS, PROTEIN, DAIRY, FRUITS, FATS)"
                + "VALUES (?,?,?,?,?);";
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query);
            ps.setInt(1,carbs);
            ps.setInt(2,protein);
            ps.setInt(3,dairy);
            ps.setInt(4,fruit);
            ps.setInt(5,fat);
            
            Window owner = submit.getScene().getWindow();
            
            if(carbsField.getText().isEmpty()){
               showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                       "Please enter the amount of carbohydrates consumed");
               return;
            }
            if(proteinField.getText().isEmpty()){
               showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                       "Please enter the amount of protein consumed");
               return;
            }
            if(dairyField.getText().isEmpty()){
               showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                       "Please enter the amount of dairy products consumed");
               return;
            }
            if(fruitField.getText().isEmpty()){
               showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                       "Please enter the amount of fruits/vegetables consumed");
               return;
            }
            if(proteinField.getText().isEmpty()){
               showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                       "Please enter the amount of protein consumed");
               return;
            }
            else{
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Data Recorded!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ps.executeUpdate();
            ps.close();
        }
    }
    
    public void loadPieChart(ActionEvent event) throws SQLException {
        String query2 = "SELECT CARBS, PROTEIN, DAIRY, FRUITS, FATS FROM FOOD;";
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query2);
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("Carbs", rs.getInt(1)), //PersistantDataHelper.carbsInput1
                new PieChart.Data("Proteins", rs.getInt(2)), //PersistantDataHelper.protienInput1
                new PieChart.Data("Dairy", rs.getInt(3)), //PersistantDataHelper.dairyInput1
                new PieChart.Data("Fruits & Veg", rs.getInt(4)), //PersistantDataHelper.dairyInput1
                new PieChart.Data("Fats", rs.getInt(5))); //PersistantDataHelper.fatsInput1
        pieChart.setData(pieChartData);
        pieChart.setTitle(timeStamp());  

        st.close();
        conn.close();
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
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
                new PieChart.Data("Carbs", PersistantDataHelper.carbsInput1), //PersistantDataHelper.carbsInput1
                new PieChart.Data("Protiens", PersistantDataHelper.proteinInput1), //PersistantDataHelper.protienInput1
                new PieChart.Data("Dairy", PersistantDataHelper.dairyInput1), //PersistantDataHelper.dairyInput1
                new PieChart.Data("Fruits & Veg", PersistantDataHelper.dairyInput1), //PersistantDataHelper.dairyInput1
                new PieChart.Data("Fats", PersistantDataHelper.fatsInput1)); //PersistantDataHelper.fatsInput1
        pieChart.setData(pieChartData); 
    }   
}
