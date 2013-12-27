
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Service Users</title>

</head>
<body>
    <%@include file="/header.jsp"%>
	
	<h1>Service Users: Search</h1>
	<hr/>
	<form id="srchServiceUserfrm" method="POST" action="ServiceUserServlet">
		<input type="hidden" name="requestAction" value="srchServiceUser"/>
		Search:<input type="search" placeholder="Search Service user" name="serviceUserName" focus="true"/>
		<input type="submit" value="Search by Name">
	</form>
	<hr/>
	<c:if test="${serviceUserResults.size() != null}">
	<form id ="serviceUserSelect" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="editServiceUser"/>
	<input type="hidden" name="serviceUserId" value=""/>
	<table width="100%" border="1">
	<tr><th>Name</th><th>Date of Birth</th><th>Created By</th></tr>
	
	<c:forEach var="result"  items="${serviceUserResults}" >
	
	<tr>
		<td><a href="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${result.id}';f.submit();">${result.name}</a></td>
		<td>${result.doB}</td>
		<td>${result.createdBy}</td>
	</tr>
	
	</c:forEach>
	</table>
	</form>
	</c:if>
</body>
</html>