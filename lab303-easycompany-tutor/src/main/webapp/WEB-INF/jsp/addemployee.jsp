<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ajax" uri="http://ajaxtags.sourceforge.net/tags/ajaxtags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="easaycompany.insertemployee.title"/></title>

<!--for including generated Javascript Code(in validation-rules.xml)-->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!--for including generated Javascript Code(validateEmployee(), formName:커맨드 클래스 이름)-->
<validator:javascript formName="employee" staticJavascript="false" xhtml="true" cdata="false"/>

<!--Ajax Tags-->
<script type="text/javascript" src="<c:url value='/ajaxtags/js/prototype.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/scriptaculous/scriptaculous.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/overlibmws/overlibmws.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/ajaxtags.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/ajaxtags.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/displaytag.css'/>" />

<script type="text/javascript">

	function save(form){
		
		if(!validateEmployee(form)){
			return;
		}else{
			form.submit();
		}
	}
	function list(){
		location.href="employeeList.do";
	}
	
</script>

<link type="text/css" rel="stylesheet" href="scripts/easycompany.css" />
</head>
<body>
<jsp:include page="menu.html" flush="true"/>
<br/>
<div id="editform">
<form:form commandName="employee">
<table>
	<tr>
		<th>사원번호</th>
		<td><form:input path="employeeid" size="20" /></td>
	</tr>
	<tr>
		<th>부서번호</th>
		<td>
			<form:select path="superdeptid">
				<option value="">상위부서를 선택하세요.</option>
				<form:options items="${deptInfoOneDepthCategory}" />
			</form:select>
			<form:select path="departmentid">
				<option value="">근무부서를 선택하세요.</option>
				<form:options items="${deptInfoTwoDepthCategory}" />
			</form:select>
		</td>
	</tr>
	<tr>
		<th>이름</th>
		<td><form:input path="name" size="20"/><form:errors path="name" /></td>		
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><form:password path="password" size="10" showPassword="true"/></td>
	</tr>
	<tr>
		<th>나이</th>
		<td><form:input path="age" size="10"/><form:errors path="age" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><form:input path="email" size="50"/><form:errors path="email" /></td>
	</tr>
</table>
<table width="80%" border="1">
	<tr>
		<td>
		<!-- <input type="submit"/>  -->
		<input type="button" value="SAVE" onclick="save(this.form)"/><!-- client-validation을 위해 바로 submit하지 않고 먼저 validateEmployee 함수를 호출-->
		<input type="button" value="LIST" onclick="location.href='${pageContext.request.contextPath}/employeeList.do'"/>
		</td>
	</tr>
</table>

</form:form>
</div>

<!--Ajax Tags TODO [Step 4-4-04] addemployee.jsp 에서 ajaxtags 사용을 위한 ajax:select 코드를 생성한다.-->
<ajax:select 
    baseUrl="${pageContext.request.contextPath}/autoSelectDept.do"  
    parameters="depth=2,superdeptid={superdeptid}" 
    source="superdeptid" 
    target="departmentid" 
    emptyOptionName="Select model"/>

</body>
</html>