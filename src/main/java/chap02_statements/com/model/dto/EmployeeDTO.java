package chap02_statements.com.model.dto;
    /*
        DTO(Data Transfer Object)
        여러 계층간 데이터 전송을 위해 다양한 타입의 데이터를 하나로 묶어 전송하는 용도의 클래스

        DTO 클래스의 조건
        1. 모든 필드는 private
        2. 기본생성자와 모든 필드를 초기화 하는 생성자
        3. 모든 필드에 대한 setter/getter
        4. toString Overriding을 이용한 필드값 반환용 메소드
        5. 직렬화 처리
     */

import java.sql.Date;

public class EmployeeDTO implements java.io.Serializable{

    // 디티오에 담고 한 행에
    // add 어레이리스트에 담는다.
    // db에 관련된 employee를 가져온다?
    // 프리페어 스테이트 먼트 이용

    private String empId;
    private String empName;
    private String empNo;
    private String email;
    private String phone;
    private String deptCode;
    private String jobCode;
    private String salLevel;
    private double salary;
    private float bonus;
    private String managerId;
    private java.sql.Date hireDate;
    private java.sql.Date entDate;
    private String entYn;


    public EmployeeDTO() {
    }

    public EmployeeDTO(String empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode, String salLevel, double salary, float bonus, String managerId, Date hireDate, Date entDate, String entYn) {
        this.empId = empId;
        this.empName = empName;
        this.empNo = empNo;
        this.email = email;
        this.phone = phone;
        this.deptCode = deptCode;
        this.jobCode = jobCode;
        this.salLevel = salLevel;
        this.salary = salary;
        this.bonus = bonus;
        this.managerId = managerId;
        this.hireDate = hireDate;
        this.entDate = entDate;
        this.entYn = entYn;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getSalLevel() {
        return salLevel;
    }

    public void setSalLevel(String salLevel) {
        this.salLevel = salLevel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getEntDate() {
        return entDate;
    }

    public void setEntDate(Date entDate) {
        this.entDate = entDate;
    }

    public String getEntYn() {
        return entYn;
    }

    public void setEntYn(String entYn) {
        this.entYn = entYn;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", empNo='" + empNo + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", jobCode='" + jobCode + '\'' +
                ", salLevel='" + salLevel + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", managerId='" + managerId + '\'' +
                ", hireDate=" + hireDate +
                ", entDate=" + entDate +
                ", entYn='" + entYn + '\'' +
                '}';
    }
}
