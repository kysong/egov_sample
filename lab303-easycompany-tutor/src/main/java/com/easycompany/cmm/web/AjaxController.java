package com.easycompany.cmm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.ajaxtags.xml.AjaxXmlBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easycompany.cmm.view.AjaxXmlView;
import com.easycompany.service.Department;
import com.easycompany.service.DepartmentService;
import com.easycompany.service.EmployeeService;


@Controller
public class AjaxController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	//사원정보 리스트 페이지에서 검색입력창(사원이름)에 사용되는 자동완성기능
	@RequestMapping("/suggestName.do")
	protected ModelAndView suggestName(@RequestParam("searchName") String searchName){
		
		ModelAndView model = new ModelAndView(new AjaxXmlView());
		List<String> nameList = employeeService.getNameListForSuggest(searchName);
		
		AjaxXmlBuilder ajaxXmlBuilder = new AjaxXmlBuilder();
		
		for(String name:nameList){
			ajaxXmlBuilder.addItem(name, name, false);
		}
		model.addObject("ajaxXml",ajaxXmlBuilder.toString());
		return model;
	}
	
	//linked selectbox 처리 기능
	@RequestMapping("/autoSelectDept.do")
	protected ModelAndView getDeptInfoforSelectTag(@RequestParam("superdeptid") String superdeptid,
			@RequestParam("depth") String depth) throws Exception{
		
		AjaxXmlBuilder ajaxXmlBuilder = new AjaxXmlBuilder();
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("superdeptid",superdeptid);
		param.put("depth",depth);
		List<Department> deptList = departmentService.getDepartmentList(param);
		ModelAndView model = new ModelAndView(new AjaxXmlView());
		
		for(Department dept:deptList){
			ajaxXmlBuilder.addItem(dept.getDeptname(), dept.getDeptid(), true);
		}
		
		model.addObject("ajaxXml",ajaxXmlBuilder.toString());
		
		return model;
	}
}
