package SQL;

import java.sql.*;

public class DbAutomationDemo {
    public static void main(String[] args) {
        // URL = jdbc:mysql://Hostname to my database: port/dbName
        String dbURL = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            //creating a connection to the DB
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("=== CONNECTION IS CREATED ===");
            //creating a statement object
            statement = connection.createStatement();
            //sending sql query to the DB and getting response in the form of ResultSet
            resultSet = statement.executeQuery("select * from person");

            resultSet.next();
            String fullName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
            System.out.println(fullName);
            resultSet.next();
            fullName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
            System.out.println(fullName);

            while(resultSet.next()){
                fullName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
                System.out.println(fullName);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}