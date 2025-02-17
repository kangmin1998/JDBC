package chap03_crud.com.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate_menudb.close;
import static common.JDBCTemplate_menudb.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0; // insert 된 row의 개수를 반환받을 변수 (행의 갯수)


        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu_query/menu_query.xml"));
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "봉골레파스타");
            pstmt.setInt(2, 15000);
            pstmt.setInt(3, 4);
            pstmt.setString(4, "Y");

            // 조회 시에는 excuteQuery() 메소드를 사용하여 ResultSet 객체를 반환 받지만,
            // 삽입, 수정, 삭제 시에는 excuteUpdate() 메소드를 사용하여 수행 된 행의 갯수를 반환 받는다.
             result = pstmt.executeUpdate();


        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        System.out.println("result : " + result);

    }


}
