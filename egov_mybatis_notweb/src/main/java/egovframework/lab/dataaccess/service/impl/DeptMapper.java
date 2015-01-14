package egovframework.lab.dataaccess.service.impl;

import java.util.List;

import egovframework.lab.dataaccess.service.DeptIncludesEmpList;
import egovframework.lab.dataaccess.service.DeptVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * Mapper Interface
 * - 메서드명과 쿼리ID를 매핑하여 쿼리호출
 * */
@Mapper("deptMapper")
public interface DeptMapper {
 
	// TODO [Step 3-2-4] DeptMapper 작성 (Mapper Interface) 
	
    public void insertDept(DeptVO vo);
 
    public int updateDept(DeptVO vo);
 
    public int deleteDept(DeptVO vo);
 
    public DeptVO selectDept(DeptVO vo);
 
    public List<DeptVO> selectDeptList(DeptVO searchVO);

    public DeptIncludesEmpList selectDeptIncludesEmpList(DeptVO vo);
}
