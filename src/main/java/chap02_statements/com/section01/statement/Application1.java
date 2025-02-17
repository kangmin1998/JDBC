package chap02_statements.com.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static common.JDBCTemplate_employee.close;
import static common.JDBCTemplate_employee.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();

        // 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스
        // 쿼리를 운반하고 결과를 반환하는 객체
        Statement stmt = null;

        // select 결과 집합을 받아 올 용도의 인터페이스
        // ResultSet 객체를 이용해 select의 결과 집합을 저장할 수 있다. 조회의 결과 반환되는 객체
        ResultSet rset = null;

        try {
            // connection을 이용하여 statement (쿼리 싣고다닐 인스턴스) 생성
            stmt = con.createStatement();

            rset = stmt.executeQuery("SELECT emp_id, emp_name FROM employee");

            while (rset.next()) {
                // next() : ResultSet의 커서 위치를 하나 내리면 행이 존자하면 true, 존재하지 않으면 false 반환
                // getXXX(컬럼명) : 커서가 가리키는 행의 컬럼을 XXX 데이터 타입으로 반환한다.
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name"));
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        } finally {
            // 맨 마지막에 열렸던것부터 순서대로 닫아줌
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
