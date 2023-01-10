package edu.upc.dsa.BBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactorySession {
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }



    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn =
                    //DriverManager.getConnection("jdbc:mariadb://localhost:3306/bbdd_juego","CAVI","1234");
                    DriverManager.getConnection("jdbc:mariadb://localhost:3306/dsa_g5_v1","CAVI","1234");


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
