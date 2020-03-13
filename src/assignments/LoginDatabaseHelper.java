/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import static assignments.LoginScreenController.alertBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author galstern
 */
public class LoginDatabaseHelper {
    /*
    if success return true
    */
    public static boolean validateLogin(String username, String password) {
        boolean preparedResponse = false;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT email, password FROM REGISTRATION WHERE email = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){  
               preparedResponse = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return preparedResponse;
    }
}
