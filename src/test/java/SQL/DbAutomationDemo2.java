package SQL;

import java.sql.*;

public class DbAutomationDemo2 {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try{
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "select * from person where city in ('New York', 'Dubai', 'Istanbul')";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            resultSet.next();
            ResultSetMetaData rsetData = resultSet.getMetaData();

            // to get number of cols
            int cols = rsetData.getColumnCount();
            System.out.println(cols);

            // to get name of the columns
            String colName = rsetData.getColumnName(1);
            System.out.println(colName);

            System.out.println(" ---------------------------------------   ");

            for (int i = 1; i <= cols; i++) {

                colName = rsetData.getColumnName(i);
                System.out.println(colName);

            }

            System.out.println(" ---------------------------------------   ");

            while (resultSet.next()) {

                for (int i = 1; i <= rsetData.getColumnCount(); i++) {

                    colName = rsetData.getColumnName(i);

                    String data = resultSet.getString(colName);
                    System.out.print(data + "    ");
                }
                System.out.println();
            }
        } catch ( SQLException e){
            e.printStackTrace();
        }

    }
}
