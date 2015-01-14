package egovframework.lab.dataaccess.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.lab.dataaccess.service.DeptIncludesEmpList;
import egovframework.lab.dataaccess.service.DeptService;
import egovframework.lab.dataaccess.service.DeptVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("deptService")
public class DeptServiceImpl extends EgovAbstractServiceImpl implements DeptService {

	// TODO [Step 3-2-3] DeptServiceImpl 작성
	
    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    // insert Dept
    public void insertDept(DeptVO deptVO) throws Exception {
        deptMapper.insertDept(deptVO);
    }

    // update Dept
    public void updateDept(DeptVO deptVO) throws Exception {
        deptMapper.updateDept(deptVO);
    }

    // delete Dept
    public void deleteDept(DeptVO deptVO) throws Exception {
        deptMapper.deleteDept(deptVO);
    }

    // select Dept
    public DeptVO selectDept(DeptVO deptVO) throws Exception {

        DeptVO resultVO = deptMapper.selectDept(deptVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }

    // list Dept
    public List<DeptVO> selectDeptList(DeptVO searchVO) throws Exception {
        return deptMapper.selectDeptList(searchVO);
    }

    // select Dept + EmpList
    public DeptIncludesEmpList selectDeptIncludesEmpList(DeptVO deptVO)
            throws Exception {
        DeptIncludesEmpList resultVO = deptMapper.selectDeptIncludesEmpList(deptVO);

        if (resultVO == null) {
            throw processException("info.nodata.msg");
        }

        return resultVO;
    }

}
