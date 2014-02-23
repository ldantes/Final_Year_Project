
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
		<p>Search:<input type="search" placeholder="Search Service user" name="serviceUserName" /></p>
		<input class="button" type="submit" value="Search by Name">
	</form>
	<hr/>
	<c:if test="${serviceUserResults.size() != null}">
	<form id ="serviceUserSelect" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="editServiceUser"/>
	<input type="hidden" name="serviceUserId" value=""/>
	<c:choose>
	<c:when test="${serviceUserResults != null && fn:length(serviceUserResults) != 0}">
	<table class="hor-zebra" >
	<tr><th width="25%">Name</th><th>Gender</th><th>Date of Birth</th><th>Created By</th></tr>
	
	<c:forEach var="result"  items="${serviceUserResults}" >
	
	<tr onclick="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${result.id}';f.submit();" class="row">
		<td>${result.firstname} ${result.surname}</td>
		<td>${result.gender}</td>
		<td>${result.doB}</td>
		<td>${result.createdBy}</td>
	</tr>
	
	</c:forEach>

	</table>
	<hr/>
	<p>Results: ${serviceUserResults.size()}</p>
	</c:when>
	<c:otherwise>
		No results found!
	</c:otherwise>
	</c:choose>
	</form>
	</c:if>
</body>
</html>