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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author galstern
 */
public class MedicalPageController implements Initializable{
    
    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet rs = null;
    
    ObservableList<String> choiceBoxList = FXCollections
            .observableArrayList("General checkup", "Injury checkup", "Surgery", "Throat checkup", "Blood sugar test", "X-ray", "Vaccination", "Viral infection checkup", "Other");
    
    @FXML
    public ChoiceBox choiceBox;
    
    @FXML
    public DatePicker datePicker;
    
    @FXML
    public TableView<ModelTable> tableView;
    @FXML
    public TableColumn<ModelTable, String> colDate;
    @FXML
    public TableColumn<ModelTable, String> colType;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
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
    public Button mentalButton;
    
    @FXML
    public Button medicalButton;
    
    @FXML
    public Label lbl;
    
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
    public void toMentalPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) mentalButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "MentalPage.fxml");
    }
    
    @FXML
    public void selectDate(ActionEvent event) {
        lbl.setText(datePicker.getValue().toString());
    }
    
    @FXML
    public void submitCheckup(ActionEvent event) throws SQLException {
    
        String type = (String) choiceBox.getValue();
        String query = "INSERT OR IGNORE INTO Medical (DATE, TYPE)" 
                    + "VALUES (?, ?);";
        
        try{ 
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, ((TextField)datePicker.getEditor()).getText());
            preparedStatement.setString(2, type);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.executeUpdate();
            preparedStatement.close();
            alertBox("Submitted your next checkup!",null,"Success");
        }
    }
    
    public static void alertBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    /**
     *
     * @param location
     * @param resources
     * @throws java.sql.SQLException
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        choiceBox.setItems(choiceBoxList);
         
        String query = "SELECT * FROM Medical;";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ResultSet rs = conn.createStatement().executeQuery(query);
            
            while(rs.next()) {
                oblist.add(new ModelTable(rs.getString("DATE"), rs.getString("TYPE")));
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        tableView.setItems(oblist);
   
        
         
    }
    
    
    public void loadDatabaseData() {
        String query = "SELECT * FROM Medical;";
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                oblist.add(new ModelTable(
                        rs.getString("date"), 
                        rs.getString("type")));
                tableView.setItems(oblist);
            }
            
            preparedStatement.close();
            conn.close();
            
        } catch(SQLException e) {
             System.err.println(e); 
        }
    }
    
    
  }

