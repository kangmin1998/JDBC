package chap03_crud.com.section01.update;

import model.dto.MenuDTO;

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
        System.out.print("변경할 메뉴 번호 입력 : ");
        int menuCode = sc.nextInt();
        System.out.print("변경할 메뉴 이름 입력 : ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴 가격 입력 : ");
        int menuPrice = sc.nextInt();
        sc.close();

        MenuDTO changedMenu = new MenuDTO();
        changedMenu.setCode(menuCode);
        changedMenu.setName(menuName);
        changedMenu.setPrice(menuPrice);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu_query/menu_query.xml"));
            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changedMenu.getName());
            pstmt.setInt(2, changedMenu.getPrice());
            pstmt.setInt(3, changedMenu.getCode());

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

        if (result > 0) {
            System.out.println("메뉴 변경 완료!");
        } else {
            System.out.println("메뉴 변경 실패!");
        }
    }
}
