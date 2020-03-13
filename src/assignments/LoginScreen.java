 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import java.net.URL;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin
 */
public class LoginScreen extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //loadDatabase();
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreenSimple.fxml"));
        
        /*
        URL myURL = getClass().getResource("assignment/LoginScreen.fxml");
        FXMLLoader myLoader = new FXMLLoader();
        myLoader.setLocation(myURL);

        Parent root = myLoader.load();
        */


        stage.initStyle(StageStyle.DECORATED);

        Scene scene = new Scene(root);

        scene.getStylesheets().
                add(LoginScreen.class.getResource("LoginScreen.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
        
        Text scenetitle = new Text("Please Login: ");
        scenetitle.setFont(Font.font("Tahoma",FontWeight.NORMAL, 20));
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /*
    public void loadDatabase() throws SQLException{
        createRegistration();
        createFood();
        createAerobics();
        createResistance();
        createAttendance();
        createMental();
        createMedical();
    }
*/
    
    
}
