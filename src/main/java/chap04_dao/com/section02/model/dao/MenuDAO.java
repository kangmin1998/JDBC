package chap04_dao.com.section02.model.dao;

/*
    DAO(Database Access Object) : 데이터베이스 접근용 객체
    crud 연산을 담당하는 메소드들의 집합으로 이루어진 클래스
 */

import chap04_dao.com.section02.model.dto.CategoryDTO;
import chap04_dao.com.section02.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate_menudb.close;

public class MenuDAO {

    private Properties prop = new Properties();

    public MenuDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu_query/menu_query.xml"));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }


    /* 1. 하위 카테고리 조회하는 메소드 */
    public List<CategoryDTO> selectAllCategory(Connection con){

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<CategoryDTO> categoryList = null;

        String query = prop.getProperty("selectAllCategoryList");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            categoryList = new ArrayList<>();

            while (rset.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryCode(rset.getInt("category_code"));
                categoryDTO.setCategoryName(rset.getString("category_name"));

                categoryList.add(categoryDTO);
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return categoryList;
    }


    /* 2. 신규 메뉴 등록하는 메소드*/
   public int insertMenu(Connection con, MenuDTO newMenu) {

       PreparedStatement pstmt = null;
       int result = 0;
       String query = prop.getProperty("insertMenu");

       try {
           pstmt = con.prepareStatement(query);
           pstmt.setString(1, newMenu.getMenuName());
           pstmt.setInt(2, newMenu.getMenuPrice());
           pstmt.setInt(3, newMenu.getCategoryCode());
           pstmt.setString(4, newMenu.getOrderableStatus());

           result = pstmt.executeUpdate();

       } catch (SQLException e) {
           //throw new RuntimeException(e);
           e.printStackTrace();
       }finally {
           close(pstmt);
       }
       return result;
   }

}
