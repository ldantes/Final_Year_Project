<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
<script>
function assignCheckBox(checkbox)
{
	if (checkbox.checked) 
	{
		checkbox.value = 'Y';
	}
	else
	{
		checkbox.value = 'N';
    }
}

function show_hide(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
 }
</script>
</head>
<body>
 <%@include file="/header.jsp"%>
 <script type="text/javascript" src="/resources/javascript/jsFunctions.js"></script>
<div class="inline">
<h1>Users</h1>
	<form id ="editUser" method="post" action="UserServlet">
	<input type="hidden" name="requestAction" value="editUser"/>
	<input type="hidden" name="userId" value=""/>
	<input type="hidden" name="username" value="${username}"/>
	</form>	
	<c:forEach var="users" items="${users}">
	<a href="javascript:var f = document.getElementById('editUser'); f.userId.value='${users.userName}';f.submit();">${users.firstName} ${users.surname}</a><br/>
	</c:forEach>
	<a href="javascript:var f = document.getElementById('editUser'); f.userId.value='';f.submit();">Add New User</a>
</div>
<div class="inline">
	<h1>Edit User</h1>
	<form id ="saveUser" method="post" action="UserServlet">
	<input type="hidden" name="requestAction" value="saveUser"/>
	<input type="hidden" name="username" value="${username}"/>
	<table class="class="hor-zebra">
		<c:choose>
		<c:when test="${selecteduser.userName != null && selecteduser.userName != ''}">
		<tr>
			<td>Username
			</td>
			<td><input name="setusername" type="hidden" value="${selecteduser.userName}">${selecteduser.userName}
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td>Username*
			</td>
			<td><input name="enteredusername" type="text" maxlength="25" size="50" value="${selecteduser.userName}" required>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td>First Name*
			</td>
			<td><input name="fname" type="text" maxlength="20" size="50" value="${selecteduser.firstName}" required>
			</td>
		</tr>
		<tr>
			<td>Surname*
			</td>
			<td><input name="sname" type="text" maxlength="20" size="50" value="${selecteduser.surname}" required>
			</td>
		</tr>
		<tr>
			<td>Profession*
			</td>
			<td><input name="profession" type="text" maxlength="50" size="50" value="${selecteduser.profession}" required>
			</td>
		</tr>
		<tr>
			<td>Email Address*
			</td>
			<td><input name="email" type="email" maxlength="50" size="50" value="${selecteduser.email}" required>
			</td>
		</tr>
		<tr>
			<td> <a href="javascript:show_hide('newPassword');">New Password</a>
			<div id="newPassword"    style="display:none;"}>
			Password:<input name="password" type="password" maxlength="50" size="50" value="" >
			</div>
			</td>
			
		</tr>
		<tr>
			<td>Active
			</td>
			<td>
				<c:choose>
				<c:when test="${selecteduser.active=='Y' || selecteduser.active=='y'}">
						<c:set var="active" value="checked"/>
				</c:when>
				<c:when test="${selecteduser.active=='N' || selecteduser.active=='n'}">
						<c:set var="active" value=""/>
				</c:when>
				<c:otherwise>
						<c:set var="active" value=""/>
				</c:otherwise>
				</c:choose>
				<input type="checkbox" name="active" id="active" Onclick="assignCheckBox(this);"value="Y" ${active}/>
			</td>
		</tr>
		<tr><td><h1>User Roles</h1></td></tr>
		<c:forEach var="availableRoles" items="${roles}">
		<tr>
		<td> ${availableRoles.roleName}
			<c:forEach var="userRoles" items="${selecteduser['userRoles']}">
			<c:set var="checkedx" value=""/>
			<c:choose>
				<c:when test="${availableRoles.roleName == userRoles.roleName}">
						<c:set var="checkedx" value="checked"/>
				</c:when>
			</c:choose>
			</c:forEach>
			<input type="checkbox" name="${availableRoles.roleName}X" Onclick="assignCheckBox(this);"value="Y" ${checkedx} />
		</td></tr>
		</c:forEach>
		<tr>
			<td><input type="submit" class="button" value="Submit">
			</td>
			<td><input class="button"  type="button" value="Cancel" onClick="this.form.reset();"/>
			</td>
		</tr>
		
	
	</table>
	</form>
	
</div>
<%@include file="/footer.html"%>
</body>
</html>