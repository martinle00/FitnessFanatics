package assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static void main(String[] args) {
        try {
            createAttendance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createHome() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement st = conn.createStatement();
        
        String createQuery = "CREATE TABLE IF NOT EXISTS Home"
                + "(LeanMassGoal DOUBLE,"
                + "FatMassGoal DOUBLE, "
                + "SleepGoal DOUBLE"
                + ");";
        st.execute(createQuery);
        
        st.close();
        conn.close();
    }
    
    
    
    public static void createRegistration() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Registration"
                + "(Email TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "FName TEXT NOT NULL, "
                + "LName TEXT NOT NULL, "
                + "Age INTEGER NOT NULL, "
                + "Height DOUBLE NOT NULL, "
                + "Weight DOUBLE NOT NULL "
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }

    public static void createFood() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Food"
                
                + "(Carbs INTEGER NOT NULL,"
                + "Protein INTEGER NOT NULL,"
                + "Dairy INTEGER NOT NULL,"
                + "Fruits INTEGER NOT NULL,"
                + "Fats INTEGER NOT NULL"
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }

    public static void createAerobics() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Aerobics "
                + "(Type TEXT NOT NULL, "
                + "Distance DOUBLE NOT NULL, "
                + "Time DOUBLE NOT NULL, "
                + "TimeGoal DOUBLE, "
                + "DistanceGoal DOUBLE"
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }

    public static void createResistance() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Resistance "
                + "(Type TEXT NOT NULL, "
                + "Reps DOUBLE NOT NULL, "
                + "Sets DOUBLE NOT NULL, "
                + "Mass DOUBLE NOT NULL, "
                + "MassMonthly DOUBLE"
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }

    public static void createAttendance() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Attendance "
                + "(Date TEXT NOT NULL, "
                + "Description TEXT NOT NULL"
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }
    
    public static void dropAttendance() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement st = conn.createStatement();
        String createQuery = "DROP TABLE Attendance;";
        st.execute(createQuery);
        st.close();
        conn.close();
    }

    public static void createMental() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS MENTAL "
                + "(DATE TEXT NOT NULL UNIQUE, "
                + "ANSWER INTEGER NOT NULL "
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }

    public static void createMedical() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");

        Statement st = conn.createStatement();

        String createQuery = "CREATE TABLE IF NOT EXISTS Medical "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "DATE TEXT NOT NULL, "
                + "TYPE TEXT NOT NULL, "
                + "FUTURE_DATE TEXT NOT NULL "
                + ");";
        st.execute(createQuery);

        st.close();
        conn.close();
    }
}
