package com.easycompany.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easycompany.service.Employee;
import com.easycompany.service.EmployeeService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("employeeService")
public class EmployeeServiceImpl extends EgovAbstractServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;	
	
	public void deleteEmployee(String id) {
		employeeDao.deleteEmployee(id);
	}
	
	public List<Employee> getAllEmployees(Map<?, ?> param) throws Exception{
		return employeeDao.getAllEmployees(param);
	}
	
	//TODO [Step 4-5-01] inertEmployee 메소드는 Employee 를 파라메터로 받아 employeeDao 를 실행한다. 
	//(결과는 저장된 갯수)
	public int insertEmployee(Employee emp) {
//		return 0;
		return employeeDao.insertEmployee(emp);
	}

	public int updateEmployee(Employee emp) {
		return employeeDao.updateEmployee(emp);
	}

	public Employee getEmployeeInfoById(String id) {
		return employeeDao.getEmployeeInfoById(id);
	}

	public List<String> getNameListForSuggest(String namePrefix) {
		return employeeDao.getNameListForSuggest(namePrefix);
	}

	public int getEmployeeCount(Map<?, ?> param) {
		return employeeDao.getEmployeeCount(param);
	}

}
