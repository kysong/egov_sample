<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ajax" uri="http://ajaxtags.sourceforge.net/tags/ajaxtags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="easaycompany.employeelist.title"/></title>
<!-- Ajax Tags  -->
<!--TODO [Step 4-2-03] ajaxtags 사용을 위한 script 코드를 import 한다. -->
<script type="text/javascript" src="<c:url value='/ajaxtags/js/prototype.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/scriptaculous/scriptaculous.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/overlibmws/overlibmws.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/ajaxtags.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/ajaxtags.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/displaytag.css'/>" />

<script type="text/javascript">
	
	function fnSearch(){
		listform.action = "employeeList.do";
		listform.submit();
	}
	
	function getEmployeeInfo(id){
		location.href = "updateEmployee.do?employeeid="+id;
	}

	function linkPage(pageNo){
		location.href = "employeeList.do?pageNo="+pageNo;
	}
	
</script>
<link type="text/css" rel="stylesheet" href="scripts/easycompany.css" />
</head>
<body>
<jsp:include page="menu.html" flush="true"/>
<br/>
<div id="listpage">
<form:form commandName="searchCriteria" action="employeeList.do">
<table width="50%" border="1">
	<tr>
		<td colspan="6" >
			사원번호 :<form:input path="searchEid"/> 
			부서번호 : <form:input path="searchDid"/>
			이름 :   <form:input path="searchName" cssStyle="WIDTH: 80px;"/>&nbsp;
			<input type="submit" value="검색" onclick="this.disabled=true,this.form.submit();" />
		</td>
	</tr>	

	<tr>
		<th></th>
		<th>사원번호</th>
		<th>부서번호</th>
		<th>이름</th>
		<th>나이</th>
		<th>이메일</th>
	</tr>
	
<c:forEach items="${employeelist}" var="empinfo">	
	<tr>
		<td></td>
		<td><a href="javascript:getEmployeeInfo('${empinfo.employeeid}')">${empinfo.employeeid}</a></td>
		<td>${empinfo.departmentid}</td>
		<td>${empinfo.name}</td>
		<td>${empinfo.age}</td>
		<td>${empinfo.email}</td>
	</tr>
</c:forEach>
</table>
</form:form>
</div>

<br/>
<table width="50%">
	<tr>
		<td align="right"><a href="insertEmployee.do">사원추가</a></td>
	</tr>
</table>
<br/>
<table width="50%">
	<tr>
		<td align="center">
		<div id="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}"
			type="image"
			jsFunction="linkPage"
			/>
		</div>	
		</td>
	</tr>
</table>

<!--Ajax Tags TODO [Step 4-2-04] ajaxtags 사용을 위한 ajax:autocomplete 코드를 생성한다.-->
<ajax:autocomplete 
  baseUrl="${pageContext.request.contextPath}/suggestName.do" 
  source="searchName" 
  target="searchName" 
  className="autocomplete" 
  minimumCharacters="1" />


</body>
</html>