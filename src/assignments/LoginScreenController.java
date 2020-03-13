package assignments;
 
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
/**
 *
 * @author Gal Stern
 */
public class LoginScreenController implements Initializable {
   

    @FXML
    public Button loginButton;
    
    @FXML
    public Button registerButton;
 
    @FXML
    Stage stage;
    
    @FXML
    private TextField textEmail;
    
    @FXML
    private PasswordField textPassword;
    
    //Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    
    @FXML
    public void actionLogin(ActionEvent event){
        
        String email = textEmail.getText();
        String password = textPassword.getText();

        if(validateFields() == true){ 
            try{
             
                Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
              
                preparedStatement = conn.prepareStatement("SELECT email, password FROM REGISTRATION WHERE email = ? AND password = ?");
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                
                resultSet = preparedStatement.executeQuery();
                
                if(!resultSet.next()){  
                   alertBox("Please enter a valid Email and Password", null, "Failed");
                }else{
                   alertBox("Login Successful",null,"Success");
                       
                        stage = (Stage) loginButton.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                        //find a way to use the ActionEvent to get the current window
                        //TODO: getWindow
                        //stage.initStyle(StageStyle.DECORATED);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(scene);
                        //stage.show();
                       
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void actionRegister(ActionEvent event) {
        try {
           Stage stage = (Stage) registerButton.getScene().getWindow();
           Helper helper = new Helper();
           helper.switcher(stage, "RegistrationPageDocument.fxml");
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }
   

    public boolean validateFields(){
        if(textEmail.getText().isEmpty() || textPassword.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
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
        
    public static void alertBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

