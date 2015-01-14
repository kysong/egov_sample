package egovframework.lab.dataaccess.service;

import java.math.BigDecimal;


/**
 *  하나의 사원정보와 해당 사원이 속한 부서정보를 담기 위한 클래스
 *  아래는 DeptVO 클래스의 프로퍼티를 포함하는 경우
 * */
public class EmpWithDeptVO extends EmpVO {

    private static final long serialVersionUID = 2916598732662272200L;

    private BigDecimal deptNo;

    private String deptName;

    private String loc;

    public BigDecimal getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(BigDecimal deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

}
