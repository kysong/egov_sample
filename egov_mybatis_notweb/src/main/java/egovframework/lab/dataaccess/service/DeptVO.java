package egovframework.lab.dataaccess.service;

import java.math.BigDecimal;

import egovframework.lab.dataaccess.common.SearchVO;

public class DeptVO extends SearchVO {

	// TODO [Step 3-2-1] DeptVO 작성
	
    private static final long serialVersionUID = -5658611204548724246L;

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
