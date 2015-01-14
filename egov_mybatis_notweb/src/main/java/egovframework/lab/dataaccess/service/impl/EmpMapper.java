package egovframework.lab.dataaccess.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.lab.dataaccess.service.EmpIncludesDept;
import egovframework.lab.dataaccess.service.EmpVO;
import egovframework.lab.dataaccess.service.EmpWithDeptVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * EgovAbstractMapper를 상속/구현한 DAO 클래스
 * */
@Repository("empMapper")
public class EmpMapper extends EgovAbstractMapper {
	
	// TODO [Step 3-1-4] EmpMapper 작성 (EgovAbstractMapper 상속한 DAO) 

    public void insertEmp(EmpVO vo) {
        insert("Emp.insertEmp", vo);
    }

    public int updateEmp(EmpVO vo) {
        return update("Emp.updateEmp", vo);
    }

    public int deleteEmp(EmpVO vo) {
        return delete("Emp.deleteEmp", vo);
    }

    public EmpVO selectEmp(EmpVO vo) {
        return (EmpVO) selectByPk("Emp.selectEmp", vo);
    }

    @SuppressWarnings("unchecked")
    public List<EmpVO> selectEmpList(EmpVO searchVO) {
        return (List<EmpVO>) list("Emp.selectEmpList", searchVO);
    }

    public EmpWithDeptVO selectEmpWithDept(EmpVO vo) {
        return (EmpWithDeptVO) selectByPk("Emp.selectEmpWithDept", vo);
    }

    public EmpIncludesDept selectEmpIncludesDept(EmpVO vo) {
        return (EmpIncludesDept) selectByPk("Emp.selectEmpIncludesDept", vo);
    }

    public EmpVO selectEmpUsingCache(EmpVO vo) {
    	return (EmpVO) selectByPk("EmpCache.selectEmpUsingCache", vo);
    }

}
