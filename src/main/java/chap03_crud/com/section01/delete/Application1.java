package chap03_crud.com.section01.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate_menudb.close;
import static common.JDBCTemplate_menudb.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 메뉴 번호 입력 : ");
        int menuCode = sc.nextInt();
        sc.close();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu_query/menu_query.xml"));
            String query = prop.getProperty("deleteMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, menuCode);

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            // throw new RuntimeException(e);
            e.printStackTrace();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("메뉴 삭제 성공!");
        }else {
            System.out.println("메뉴 삭제 실패!");
        }
    }
}
