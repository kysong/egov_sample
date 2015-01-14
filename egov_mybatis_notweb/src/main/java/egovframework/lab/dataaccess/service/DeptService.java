package egovframework.lab.dataaccess.service;

import java.util.List;

public interface DeptService {

	// TODO [Step 3-2-2] DeptService 작성
	
    public void insertDept(DeptVO deptVO) throws Exception;

    public void updateDept(DeptVO deptVO) throws Exception;

    public void deleteDept(DeptVO deptVO) throws Exception;

    public DeptVO selectDept(DeptVO deptVO) throws Exception;
    
    public List<DeptVO> selectDeptList(DeptVO searchVO) throws Exception;

    public DeptIncludesEmpList selectDeptIncludesEmpList(DeptVO deptVO) throws Exception;

}
