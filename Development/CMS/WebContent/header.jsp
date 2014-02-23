<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />

</head>
<body>

<div class="header">
	<h1 >Contingency Management System</h1>
	<h3> version 1.0</h3>
</div>
	<c:if test="${username != null}">
	<p  align="right">Signed in as: ${username}, <a href="javascript:document.navigationForm.requestAction.value='logout';document.navigationForm.action='UserServlet';document.navigationForm.submit();">Logout</a></p>

	<form name="navigationForm" method="post">
					<input type="hidden" name="action">
					<input type="hidden" name="requestAction">
					<input type="hidden" name="serviceUserId">
					
	</form>
	
		<nav id="nav"> 
		<a href="javascript:document.navigationForm.requestAction.value='srchServiceUser';document.navigationForm.action='ServiceUserServlet';document.navigationForm.submit();">Search Service User</a>
		<a href="javascript:document.navigationForm.requestAction.value='editServiceUser';document.navigationForm.action='ServiceUserServlet';document.navigationForm.serviceUserId='';document.navigationForm.submit();">Add Service User</a>
		<c:if test="${sessionScope.userAdmin == 'true'}"><a href="javascript:document.navigationForm.requestAction.value='init';document.navigationForm.action='SystemServlet';document.navigationForm.submit();">System Administration</a>
		<a href="javascript:document.navigationForm.requestAction.value='init';document.navigationForm.action='UserServlet';document.navigationForm.submit();">User Management</a>
		</c:if>
		</nav>
	
	</c:if>
 <hr/>
</body>
</html>