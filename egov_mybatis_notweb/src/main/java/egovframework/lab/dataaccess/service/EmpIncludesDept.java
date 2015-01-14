package egovframework.lab.dataaccess.service;

/**
 *  하나의 사원정보와 해당 사원이 속한 부서정보를 담기 위한 클래스
 *  아래는 DeptVO 객체를 참조하는 경우
 * */
public class EmpIncludesDept extends EmpVO {

    private static final long serialVersionUID = 2178085836444199596L;
    
    private DeptVO deptVO;

    public DeptVO getDeptVO() {
        return deptVO;
    }

    public void setDeptVO(DeptVO deptVO) {
        this.deptVO = deptVO;
    }

}
