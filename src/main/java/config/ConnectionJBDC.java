package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJBDC {
    private static Connection connection;
    private ConnectionJBDC() {
    }
    private static final String URL = "jdbc:mysql://localhost:3306/module3Test";
    private static final String USER = "root";
    private static final String PASSWORD = "D4tRSzbc";
    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.println("connection established");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("connection could not be established");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
