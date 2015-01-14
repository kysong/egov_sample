package com.easycompany.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.easycompany.service.Department;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("departmentDao")
public class DepartmentDao  extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentList(Map<?, ?> param)throws DataAccessException{		
		return (List<Department>) list("Department.getDepartmentList",param);
	}
	
	public Department getDepartmentInfoById(String id)throws DataAccessException{		
		return (Department)selectByPk("Department.getDepartmentInfoById",id);
	}
	
	public int updateDepartment(Department department)throws DataAccessException{		
		return update("Department.updateDepartment",department);
	}
	
}
