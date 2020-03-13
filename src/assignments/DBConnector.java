/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Martin
 */
public class DBConnector {
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        return connection;
    }
}