
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Service Users</title>
<%@include file="/header.jsp"%>
</head>
<body>
	<p align="right">Signed in as: ${username}</p>
	<h1>Service Users: Search</h1>
	<hr/>
	<form id="srchServiceUserfrm" method="POST" action="ServiceUserServlet">
		<input type="hidden" name="requestAction" value="srchServiceUser"/>
		Search:<input type="search" placeholder="Search Service user" name="serviceUserName" focus="true"/>
		<input type="submit" value="Search by Name">
	</form>
	<hr/>
	<c:if test="${serviceUserResults.size() != null}">
	<table width="100%" border="1">
	<tr><th>Name</th><th>Date of Birth</th><th>Created By</th></tr>
	<c:forEach var="result"  items="${serviceUserResults}" >
	<tr>
		<td>${result.name}</td>
		<td>${result.doB}</td>
		<td>${result.createdBy}</td>
	</tr>
	</c:forEach>
	</table>
	</c:if>
</body>
</html>