<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ajax" uri="http://ajaxtags.sourceforge.net/tags/ajaxtags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="easaycompany.departmentlist.title"/></title>
<link type="text/css" rel="stylesheet" href="scripts/easycompany.css" />

<!--Ajax Tags-->
<script type="text/javascript" src="<c:url value='/ajaxtags/js/prototype.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/scriptaculous/scriptaculous.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/overlibmws/overlibmws.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ajaxtags/js/ajaxtags.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/ajaxtags.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/ajaxtags/css/displaytag.css'/>" />

<script type="text/javascript">
	function departmentInfo(id){
		location.href = "${pageContext.request.contextPath}/updateDepartment.do?deptid="+id;
	}
</script>
</head>
<body>
<jsp:include page="menu.html" flush="true"/>
<br/>
<div id="listpage">
<table>
	<tr>
		<td>
		<ajax:tabPanel id="departmentTab">
		<c:forEach items="${departmentlist}" var="departmentinfo" varStatus="status">
			<c:choose>
				<c:when test="${status.first}">
			    <ajax:tab caption="${departmentinfo.deptname}" 
					  baseUrl="${pageContext.request.contextPath}/subDepartmentList.do?superdeptid=${departmentinfo.deptid}&depth=2"
					  defaultTab="true"/>
				</c:when>
				<c:otherwise>
				<ajax:tab caption="${departmentinfo.deptname}" 
					  baseUrl="${pageContext.request.contextPath}/subDepartmentList.do?superdeptid=${departmentinfo.deptid}&depth=2"/>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</ajax:tabPanel>
		</td>
	</tr>
</table>
</div>
</body>
</html>