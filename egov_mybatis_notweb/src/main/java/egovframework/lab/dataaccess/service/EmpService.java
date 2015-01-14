package egovframework.lab.dataaccess.service;

import java.math.BigDecimal;
import java.util.List;

public interface EmpService {
	
	// TODO [Step 3-1-2] EmpService 작성

    public BigDecimal insertEmp(EmpVO empVO) throws Exception;

    public void updateEmp(EmpVO empVO) throws Exception;

    public void deleteEmp(EmpVO empVO) throws Exception;

    public EmpVO selectEmp(EmpVO empVO) throws Exception;
    
    public EmpWithDeptVO selectEmpWithDept(EmpVO empVO) throws Exception;
    
    public EmpIncludesDept selectEmpIncludesDept(EmpVO empVO) throws Exception;

    public List<EmpVO> selectEmpList(EmpVO searchVO) throws Exception;
    
    public EmpVO selectEmpUsingCache(EmpVO empVO) throws Exception;
    
}
