package egovframework.lab.dataaccess.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.lab.dataaccess.service.EmpIncludesDept;
import egovframework.lab.dataaccess.service.EmpService;
import egovframework.lab.dataaccess.service.EmpVO;
import egovframework.lab.dataaccess.service.EmpWithDeptVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("empService")
public class EmpServiceImpl extends EgovAbstractServiceImpl implements EmpService {
	
	// TODO [Step 3-1-3] EmpServiceImpl 작성

    @Resource(name = "primaryTypeSequenceIds")
    EgovIdGnrService egovIdGnrService; // primaryTypeSequenceIds 는 Sequence 기반으로 key 생성

    @Resource(name = "empMapper")
    private EmpMapper empMapper;

    // insert Emp
    public BigDecimal insertEmp(EmpVO empVO) throws Exception {
    	
        // IDGeneration Service 를 사용하여 key 생성    	
        BigDecimal generatedEmpNo = egovIdGnrService.getNextBigDecimalId();
        egovLogger.debug("EmpServiceImpl.insertEmp - generated empNo : {}", generatedEmpNo);

        empVO.setEmpNo(generatedEmpNo);

        empMapper.insertEmp(empVO);

        return generatedEmpNo;
    }

    // update Emp
    public void updateEmp(EmpVO empVO) throws Exception {
        empMapper.updateEmp(empVO);
    }

    // delete Emp
    public void deleteEmp(EmpVO empVO) throws Exception {
        empMapper.deleteEmp(empVO);
    }

    // select Emp
    public EmpVO selectEmp(EmpVO empVO) throws Exception {
        EmpVO resultVO;
        resultVO = empMapper.selectEmp(empVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }

    // list Emp
    public List<EmpVO> selectEmpList(EmpVO searchVO) throws Exception {
        return empMapper.selectEmpList(searchVO);
    }

    // select Emp + deptNo, deptName, loc
    public EmpWithDeptVO selectEmpWithDept(EmpVO empVO) throws Exception {
        EmpWithDeptVO resultVO;
        resultVO = empMapper.selectEmpWithDept(empVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }
    
    // select Emp + Dept
    public EmpIncludesDept selectEmpIncludesDept(EmpVO empVO) throws Exception {
        EmpIncludesDept resultVO;
        resultVO = empMapper.selectEmpIncludesDept(empVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }

    // select using cache
    public EmpVO selectEmpUsingCache(EmpVO empVO) throws Exception {

        EmpVO resultVO;
        resultVO = empMapper.selectEmpUsingCache(empVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }
}
