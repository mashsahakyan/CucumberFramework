package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Recap {
    public static void main(String[] args) {

        String userName = "syntax_hrm";
        String password = "syntaxhrm123";
        String dbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from ohrm_skill");

            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            List<Map<String, String>> listOfMapsFromDB = new ArrayList();

            Map<String, String> rowMap;


            while (resultSet.next()) { //iterates over rows ins resultset

                //creating a new LHM for every row in resultset
                rowMap = new LinkedHashMap<>();

                //loops over the columns of every row
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {

                    rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));

                }

                System.out.println(rowMap);
                listOfMapsFromDB.add(rowMap);


            }

            System.out.println(listOfMapsFromDB);
            System.out.println(listOfMapsFromDB.size());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}