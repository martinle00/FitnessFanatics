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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Martin
 */
public class AttendanceController implements Initializable {

    @FXML
    private Button Yes;
    
    @FXML
    private TextField descField;
    
    @FXML
    public Button homepageButton;
    
    @FXML
    public Button foodButton;
    
    @FXML
    public Button aerobicsButton;
    
    @FXML
    public Button resistanceButton;
    
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
    private TableView<Attendance> attend;

    @FXML
    private TableColumn<Attendance, String> dateColumn;
    
    @FXML
    private TableColumn<Attendance, String> descColumn;
    

    ObservableList<Attendance> attendance = FXCollections.observableArrayList();
    
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static String timeStamp() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String timenow = date.format(formatter);
        return timenow;
    }

    @FXML
    private void submit(ActionEvent event) throws SQLException {

        String date = timeStamp();
        String description = descField.getText();
        String query = "INSERT OR IGNORE INTO Attendance(Date, Description) VALUES (?, ?);";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            ps = conn.prepareStatement(query);
            ps.setString(1, date);
            ps.setString(2,description);

            Window owner = Yes.getScene().getWindow();

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Data Recorded!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.executeUpdate();
        ps.close();
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

        Connection connection;
        try {
            connection = DBConnector.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT Date, Description FROM Attendance;");

            while (rs.next()){
                attendance.add(new Attendance(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        attend.setItems(attendance);
    }

}
