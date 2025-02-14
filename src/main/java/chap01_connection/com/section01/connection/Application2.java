package chap01_connection.com.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Application2 {
    public static void main(String[] args) {

        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/chap01_connection/com/section01/connection/jdbc-config.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);

            System.out.println("con : " + con);


        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
        }
    }
}
