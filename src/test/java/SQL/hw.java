package SQL;

import java.sql.*;

public class hw {
    /*
    US 12345: Verify that all job titles are displayed in Ascending order in HRMS Application (Must be verified against DB)

    US 12345: Verify all office locations have name and country displayed HRSM application (Must be verified against DB)
     */
    public static void main(String[] args) {

        String dbURL = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from person");

            resultSet.next();
            String firstName = resultSet.getString("FirstName");
            System.out.println(firstName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
