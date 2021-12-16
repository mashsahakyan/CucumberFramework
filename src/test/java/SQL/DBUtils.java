package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    static Connection connection;
    static ResultSet resultSet;

    public static Connection getConnection(){
        return connection;
    }

    public static ResultSet getResultSet(String sqlQuery){
        return resultSet;
    }

    /**
     * This method returns data from ResultSet object
     * @param sqlQuery
     * @return List<Maps<K, V>>
     */
    public static List<Map<String, String>> listOfMapsFromDb(String sqlQuery){
        List<Map<String,String>> listOfRowMaps = new ArrayList<>();
        Map<String, String> rowMap;

        try{
            resultSet = getResultSet(sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            listOfRowMaps = new ArrayList<>();
            while(resultSet.next()){
                rowMap = new LinkedHashMap<>();
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
                }
                listOfRowMaps.add(rowMap);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listOfRowMaps;
    }
}