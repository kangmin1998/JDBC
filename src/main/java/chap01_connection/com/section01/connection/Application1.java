package chap01_connection.com.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {
    public static void main(String[] args) {

        Connection con = null;

        try {
            /* 사용할 Driver 등록 */
            Class.forName("com.mysql.cj.jdbc.Driver"); // 내가 사용할 드라이버의 풀클래스네임

            /* DriverManager의 getConnection 메소드를 이용해서 Connection (통로) 생성 */
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee" , "taemin", "taemin");

            System.out.println("con : " + con);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
