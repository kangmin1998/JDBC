package chap06_view_lecture_source.com.section01.model.dao;

import chap06_view_lecture_source.com.section01.model.dto.CategoryDTO;
import chap06_view_lecture_source.com.section01.model.dto.MenuDTO;
import chap06_view_lecture_source.com.section01.model.dto.OrderDTO;
import chap06_view_lecture_source.com.section01.model.dto.OrderMenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate_menudb.getConnection;
import static common.JDBCTemplate_menudb.close;
import static common.JDBCTemplate_menudb.commit;
import static common.JDBCTemplate_menudb.rollback;


public class OrderDAO {

    private Properties prop = new Properties();

    public OrderDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu_query/menu_query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 1. 하위 카테고리 조회하는 메소드 */
    public List<CategoryDTO> selectAllCategory(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<CategoryDTO> categoryList = null;
        String query = prop.getProperty("selectAllCategoryList");

        try {

            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            categoryList = new ArrayList<>();

            while(rset.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryCode(rset.getInt("category_code"));
                categoryDTO.setCategoryName(rset.getString("category_name"));

                categoryList.add(categoryDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return categoryList;
    }


    public List<MenuDTO> selectMenuByCategoryCode(Connection con, int categoryCode) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<MenuDTO> menuList = null;
        String query = prop.getProperty("selectMenuByCategoryCode");

        try {

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, categoryCode);
            rset = pstmt.executeQuery();
            menuList = new ArrayList<>();

            while(rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuCode(rset.getInt("menu_code"));
                menu.setMenuName(rset.getString("menu_name"));
                menu.setMenuPrice(rset.getInt("menu_price"));
                menu.setCategoryCode(rset.getInt("category_code"));
                menu.setOrderableStatus(rset.getString("orderable_status"));

                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return menuList;
    }


    /* 주문 정보 입력용 메소드 */
    public int insertOrder(Connection con, OrderDTO order) {

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertOrder");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, order.getOrderDate());
            pstmt.setString(2, order.getOrderTime());
            pstmt.setInt(3, order.getTotalOrderPrice());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

    /* 주문 코드 조회용 메소드 */
    public int selectLastOrderCode(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int lastOrderCode = 0;

        String query = prop.getProperty("selectLastOrderCode");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                lastOrderCode = rset.getInt("order_code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return lastOrderCode;
    }

    /* 주문별 메뉴 입력용 메소드 */
    public int insertOrderMenu(Connection con, OrderMenuDTO orderMenu) {

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertOrderMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, orderMenu.getOrderCode());
            pstmt.setInt(2, orderMenu.getMenuCode());
            pstmt.setInt(3, orderMenu.getOrderAmount());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }
}
