
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Service Users</title>

</head>
<body>
    <%@include file="/header.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/javascript/jsFunctions.js" />"></script>
	
	<h1>Edit Service user: </h1><h3 style="color:red">
	<c:choose>
		<c:when test="${serviceUser.name != null && serviceUser.name != ''}">
		${serviceUser.name} (${serviceUser.id})
		</c:when>
		<c:otherwise>
		New User
		</c:otherwise>
	
	</c:choose>
	</h3>
	<hr/>
	<table width="100%">
	<tr>
	<td width="50%">
	<form id="editSelectServiceUser" action ="ServiceUserServlet" method="post">
	<input type="hidden" name="requestAction" value="updateServiceUser"/>
	<input type="hidden" name="srvUserid" value="${serviceUser.id}"/> 
	<input type="hidden" name="username" value="${username}"/>
	<div> <p style="color:red">${userMsg}</p></div>
	<table>
		<tr>
			<td>
				Full Name: 
			</td>
			<td>
				<input name="srvUsername" required type="text" value="${serviceUser.name}"/>
			</td>
		</tr>
		<tr>
			<td>
				Date of Birth
			</td>
			<td>
				<input name="srvUserDOB" required type="date" value="${serviceUser.doB}"/>
			</td>
		</tr>
		<tr>
			<td>
				Gender: 
			</td>
			<td>
				
				<c:choose>
				<c:when test="${serviceUser.gender=='M' || serviceUser.gender=='m'}">
						<c:set var="M" value="checked"/>
						<c:set var="F" value=""/>
				</c:when>
				<c:otherwise>
						<c:set var="F" value="checked"/>
						<c:set var="M" value=""/>
				</c:otherwise>
				</c:choose>
				
				Male:<input type="radio" name="srvUserGender" id="genderM" onClick="assignmentRadio(this.name);" value="M" ${M}/>
				Female:<input type="radio" name="srvUserGender" id="genderF" onClick="assignmentRadio(this.name);" value="F" ${F}/>
				
			</td>
		</tr>
		<tr>
			<td>
				PPS Number: 
			</td>
			<td>
				<input name="srvPPS" required type="text" value="${serviceUser.pps}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				Contact Number: 
			</td>
			<td>
				<input name="srvUserContactactNumber" required type="tel" value="${serviceUser.contactNumber}" maxlength ="14"/>
			</td>
		</tr>
		<tr>
			<td>
				Address: 
			</td>
			<td>
				<textarea name="srvUserAddress" required maxLength="100" style="resize:none; overflow:hidden;" rows="4">
				${serviceUser.address}
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				Ethnic Background: 
			</td>
			<td>
				<input name="srvUserEthnicity" type="text" value="${serviceUser.ethnicity}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				Nationality: 
			</td>
			<td>
				<input name="srvUserNationality" type="text" value="${serviceUser.nationality}"/>
				
			</td>
		</tr>
			<tr>
			<td>
				Occupation: 
			</td>
			<td>
				<input name="srvUserOccupation" type="text" value="${serviceUser.occupation}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				Family Information: 
			</td>
			<td>
				<textarea name="srvUserFamily" maxLength="100" style="resize:none; overflow:hidden;" rows="5" >
				${serviceUser.familyInformation}
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Submit"/>
			</td>
			<td>
				<input type="button" value="Cancel" onClick="this.form.reset();"/>
			</td>
		</tr>
	</table>
	
	</form>
	</td>
	<td>
	<div>
	<c:if test="${serviceUser.name != null && serviceUser.name != ''}">
	<table>
	<tr>
	<th> Stream Details</th>
	</tr>
	<tr>
	<td>Stream:</td>
	<td>"${serviceUser.streamDetails['streamName']}" support level (${serviceUser.streamDetails['supportLevel']})</td>
	</tr>
	<tr>
	<td>Substance Incrementor:</td>
	<td>${serviceUser.streamDetails['substanceIncrementor']}</td>
	</tr>
	<tr>
	<td>Engagement Incrementor:</td>
	<td>${serviceUser.streamDetails['engagementIncrementor']}</td>
	</tr>
	<tr>
	<td>Last Updated:</td>
	<td>${serviceUser.streamDetails['updatedOn']}</td>
	</tr>
	<tr>
	<td>
	
		<form name="newRequest" method="post">
					<input type="hidden" name="action">
					<input type="hidden" name="requestAction">
					<input type="hidden" name="serviceUserId" value="${serviceUser.id}">
					
	</form>
	<div>
		
		<a href="javascript:document.newRequest.requestAction.value='newSubstanceEntry';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">Add New Behavioural/Substance results</a>
		<br/>
		<a href="javascript:document.newRequest.requestAction.value='newEngagmentEntry';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">Add New Engagement/Meeting</a>
		<br/>
		<a href="javascript:document.newRequest.requestAction.value='viewAccount';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">View Account</a>
		<br/>
		<a href="javascript:document.newRequest.requestAction.value='viewNotes';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">View notes</a>
	</div>	
		
	</td>	
	</tr>
	</table>
	</c:if>
	</div>
	</td> 
	</tr>
	</table>
	

</body>
</html>