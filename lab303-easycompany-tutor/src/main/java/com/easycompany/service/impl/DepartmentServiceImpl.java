package com.easycompany.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easycompany.service.Department;
import com.easycompany.service.DepartmentService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("departmentService")
public class DepartmentServiceImpl extends EgovAbstractServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;	
	
	public List<Department> getDepartmentList(Map<?, ?> param){
		return departmentDao.getDepartmentList(param);
	}
	
	public Map<String, String> getDepartmentIdNameList(String depth){
		return getDepartmentIdNameList(depth, null);
	}
	
	public Map<String, String> getDepartmentIdNameList(String depth, String superdeptid) {
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("superdeptid", superdeptid);
		param.put("depth", depth);
		List<Department> deptList = (List<Department>)departmentDao.getDepartmentList(param);
		Map<String, String> deptMap = new HashMap<String, String>();
		for(Department dept:deptList){
			deptMap.put(dept.getDeptid(), dept.getDeptname());
		}
		return deptMap;
	}

	public Department getDepartmentInfoById(String id) {
		return departmentDao.getDepartmentInfoById(id);
	}

	public int updateDepartment(Department department) {
		return departmentDao.updateDepartment(department);
	}

}
