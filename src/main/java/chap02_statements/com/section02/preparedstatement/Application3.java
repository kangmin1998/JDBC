package chap02_statements.com.section02.preparedstatement;

import chap02_statements.com.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;



public class Application3 {
    public static void main(String[] args) {
        /* [ 직원의 성씨를 입력받아 해당하는 직원들의 모든 정보를 DTO 객체에 담고, 이를 ArrayList에 담아 출력하기 ] */


        // Connection 생성
        Connection con = getConnection();

        // PreparedStatement 선언
        PreparedStatement pstmt = null;

        // ResultSet 선언
        ResultSet rset = null;

        // 한 행의 정보를 담을 DTO 선언

        Scanner sc = new Scanner(System.in);
        System.out.println("검색하고 싶은 직원의 성씨를 입력하세요.");
        String st = sc.nextLine();
        sc.close();
        String query = "SELECT * FROM employee WHERE  emp_name LIKE CONCAT (?,'%')";
        List<EmployeeDTO> employeeList = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, st);

            rset = pstmt.executeQuery();

            while (rset.next()) {

                EmployeeDTO employeeDTO = new EmployeeDTO();

                // 🔹 resultSet에서 조회된 데이터를 EmployeeDTO 객체에 저장
                employeeDTO.setEmpId(rset.getString("EMP_ID"));
                employeeDTO.setEmpName(rset.getString("EMP_NAME"));
                employeeDTO.setEmpNo(rset.getString("EMP_NO"));
                employeeDTO.setEmail(rset.getString("EMAIL"));
                employeeDTO.setPhone(rset.getString("PHONE"));
                employeeDTO.setDeptCode(rset.getString("DEPT_CODE"));
                employeeDTO.setJobCode(rset.getString("JOB_CODE"));
                employeeDTO.setSalLevel(rset.getString("SAL_LEVEL"));
                employeeDTO.setSalary(rset.getDouble("SALARY"));
                employeeDTO.setBonus(rset.getFloat("BONUS"));
                employeeDTO.setManagerId(rset.getString("MANAGER_ID"));
                employeeDTO.setHireDate(rset.getDate("HIRE_DATE"));
                employeeDTO.setEntDate(rset.getDate("ENT_DATE"));
                employeeDTO.setEntYn(rset.getString("ENT_YN"));


//                System.out.println(rset.getString("emp_Id") +
//                        rset.getString("emp_Name") +
//                        rset.getString("emp_No") +
//                        rset.getString("email") +
//                        rset.getString("phone") +
//                        rset.getString("dept_Code") +
//                        rset.getString("job_Code") +
//                        rset.getString("sal_Level") +
//                        rset.getDouble("salary") +
//                        rset.getFloat("bonus") +
//                        rset.getString("manager_Id") +
//                        rset.getDate("hire_Date") +
//                        rset.getDate("ent_Date") +
//                        rset.getString("ent_Yn"));
                employeeList.add(employeeDTO);
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println(employeeList.get(i));
        }


    }
}
