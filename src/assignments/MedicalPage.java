/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

//import static assignment.CRUD.createMedical;
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
 * @author galstern
 */
public class MedicalPage {
    
    public void start(Stage stage) throws Exception {
        
            //createMedical();
             
            Parent root = FXMLLoader.load(getClass().getResource("assignments/MedicalPage.fxml"));

            stage.initStyle(StageStyle.DECORATED);

            Scene scene = new Scene(root);

        /*
        scene.getStylesheets().
                add(LoginScreen.class.getResource("LoginScreen.css").toExternalForm());
        */
        
            stage.setScene(scene);
            stage.show();
        
            Text scenetitle = new Text("Medical Checkup");
            scenetitle.setFont(Font.font("Tahoma",FontWeight.NORMAL, 20)); 
        }
}
