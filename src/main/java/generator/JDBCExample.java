package generator;

import generator.bean.TableInfo;
import mybatis.simple.mapper.XrSysDictionaryMapper;
import mybatis.simple.model.XrSysDictionary;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
                InputStream in = new BufferedInputStream(new FileInputStream("D:\\opensource\\scoringCard\\src\\main\\resources\\database.properties"));
                //InputStream in = new BufferedInputStream(new FileInputStream("properties/database.properties"));
                prop.load(in);
                in.close();
                Class.forName(prop.getProperty("driverClassName"));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
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
                rs.close();
                System.out.println("\"========================\" = " + "========================");
                System.out.println("tableInfo = " + tableInfo);
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

    public static List<XrSysDictionary> getTableInfo() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().
                    build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.  利用SqlSessionFactory打开一个和数据库的SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        //3. 利用这个SqlSession获取要使用的mapper接口
        XrSysDictionaryMapper xrSysDictionaryMapper = session.getMapper(XrSysDictionaryMapper.class);

        //4. 使用mapper接口和数据库交互，运行mapper.xml文件中的SQL语句
        System.out.println("\"开始=============================\" = " + "开始=============================");
        List<XrSysDictionary> listStr = xrSysDictionaryMapper.selectByPrimaryKey();
        for (XrSysDictionary s : listStr) {
            System.out.println("s = " + s.toString());
        }
        System.out.println("\"结束=============================\" = " + "结束=============================");

        //5. SqlSession提交SQL到数据库并关闭SqlSession
        session.commit();
        session.close();

        return listStr;

    }



}
