package chap01_connection.com.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

//    public static Connection getConnection() {
//
//        Connection con = null;
//
//        Properties prop = new Properties();
//
//        try {
//            prop.load(new FileReader("src/main/java/chap01_connection/com/config/connection_info_employee.properties"));
//
//            String driver = prop.getProperty("driver");
//            String url = prop.getProperty("url");
//
//            Class.forName(driver);
//
//            // getConnection 에 properties 객체를 그냥 전달해 주는 것도 있다.
//            con = DriverManager.getConnection(url, prop);
//
//        } catch (IOException e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//        return con;
//    }
//
//    public static void close(Connection con) {
//
//        try {
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//    }
}
