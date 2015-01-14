package egovframework.lab.dataaccess.service;

import java.util.List;

/**
 *  하나의 부서에 속한 사원리스트와 해당 부서정보를 담기 위한 클래스
 * */
public class DeptIncludesEmpList extends DeptVO {

    private static final long serialVersionUID = -6098129034400325088L;

    private List<EmpVO> empList;

    public List<EmpVO> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmpVO> empList) {
        this.empList = empList;
    }

}
