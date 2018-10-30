package generator;

import generator.bean.TableInfo;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCExample implements Serializable {
    /**
     * Hive2 Driver class name
     */
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static List<TableInfo> getTableInfoList() throws SQLException {
        List<TableInfo> resultTablesInfo = null;
        Properties prop = new Properties();
        try {
            try {
                //读取属性文件a.properties
                //InputStream in = new BufferedInputStream(new FileInputStream("D:\\opensource\\scoringCard\\src\\main\\resources\\database.properties"));
                InputStream in = new BufferedInputStream(new FileInputStream("properties/database.properties"));
                prop.load(in);
                in.close();
                Class.forName(prop.getProperty("driverClassName"));
            } catch (Exception e) {
                
            }
            Connection conn = DriverManager.getConnection(prop.getProperty("driverUrl"), prop.getProperty("username"), prop.getProperty("password"));
            resultTablesInfo = getResultTablesInfo();
            Statement stmt = conn.createStatement();
            for (TableInfo tableInfo : resultTablesInfo) {
                ResultSet rs = stmt.executeQuery(" SELECT * FROM xhh_ssfk_std." + tableInfo.getTableName());
                ResultSetMetaData rsmd = rs.getMetaData();
                List<String> props = tableInfo.getProps();
                for (int size = rsmd.getColumnCount(); size > 0; size--) {
                    props.add(rsmd.getColumnName(size));
                }
               
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTablesInfo;
    }

    private static List<TableInfo> getResultTablesInfo() {
        Properties prop = new Properties();
        try {
            //InputStream in = new BufferedInputStream(new FileInputStream("D:\\opensource\\scoringCard\\src\\main\\resources\\tables.properties"));
            InputStream in = new BufferedInputStream(new FileInputStream("properties/tables.properties"));
            prop.load(in);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        String tableNames = prop.getProperty("tableNames");
        String preStreamName = prop.getProperty("preStreamName");
        String[] tablesStr = tableNames.split(",");

        List<TableInfo> tableInfoList = new ArrayList<>(18);
        for (String s : tablesStr) {
            TableInfo tableInfo = new TableInfo(s, preStreamName+s);
                        tableInfoList.add(tableInfo);
        }
        return tableInfoList;
    }


}
