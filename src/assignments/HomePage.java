/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import static javafx.application.Application.launch;
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
public class HomePage {
    
    //@Override
    public void start(Stage stage) throws Exception {
        // TODO: loadDatabase();
        
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.DECORATED);
        
        scene.getStylesheets().
                add(HomePage.class.getResource("LoginScreen.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
        
        Text scenetitle = new Text("Homepage");
        
    }
    
    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        launch(args);
    }
}
