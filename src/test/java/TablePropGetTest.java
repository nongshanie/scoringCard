import mybatis.simple.mapper.TableQueryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class TablePropGetTest {

    @Test
    public void testSql() {
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
        TableQueryMapper accountMapper = session.getMapper(TableQueryMapper.class);

        //4. 使用mapper接口和数据库交互，运行mapper.xml文件中的SQL语句
        System.out.println("\"开始=============================\" = " + "开始=============================");
       /* List<String> listStr = accountMapper.selectListStr();
        for (String s : listStr) {
            System.out.println("s = " + s);
        }*/
        System.out.println("\"结束=============================\" = " + "结束=============================");

        //5. SqlSession提交SQL到数据库并关闭SqlSession
        session.commit();
        session.close();

    }

    @Test
    public void testStr() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("create_time");
        strings.add("modify_time");
        strings.add("contact_name");
        strings.add("mobile");
        strings.add("email");
        strings.add("address");
        strings.add("uuid");
        strings.add("relation");
        strings.add("is_collector");
        StringBuilder sb = new StringBuilder(50);
        for (String s : strings) {
            sb.append(s);
            sb.append("\": \"',");
            sb.append(s);
            sb.append(",'\",','\"");
        }
        String substring = sb.substring(0, sb.length() - 7);
        //substring = substring +"'";
        System.out.println("substring = " + substring);
    }

    @Test
    public void testIntspter() {
        try {
            try {
                Class.forName("org.apache.hive.jdbc.HiveDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }

            //Hive2 JDBC URL with LDAP
            String jdbcURL = "jdbc:hive2://xxxx/default";

            String user = "xhh_sdemo";
            String password = "xhh_sdemoxhh_sdemo";
            Connection conn = DriverManager.getConnection(jdbcURL, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from xhh_ssfk_std.geae_user_contactlist");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("rsmd.getCatalogName(1) = " + rsmd.getCatalogName(1));
           /* int size = rsmd.getColumnCount();
            while (rs.next()) {
                StringBuffer value = new StringBuffer();
                for (int i = 0; i < size; i++) {
                    value.append(rs.getString(i + 1)).append("\t");
                }
                System.out.println(value.toString());
            }*/
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testJDBC() {
        Properties prop = new Properties();
         try{
                 //读取属性文件a.properties
                 //InputStream in = new BufferedInputStream (new FileInputStream("D:\\opensource\\scoringCard\\src\\main\\resources\\database.properties"));
                 InputStream in = new BufferedInputStream (new FileInputStream("D:\\opensource\\scoringCard\\src\\main\\resources\\database.properties"));
                 prop.load(in);     ///加载属性列表
                 Iterator<String> it=prop.stringPropertyNames().iterator();
                 while(it.hasNext()){
                         String key=it.next();
                         System.out.println(key+":"+prop.getProperty(key));
                     }
                 in.close();

                 ///保存属性到b.properties文件
                 FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
                 prop.setProperty("phone", "10086");
                 prop.store(oFile, "The New properties file");
                 oFile.close();
             }catch (Exception e){
             e.printStackTrace();
         }

    }
}
