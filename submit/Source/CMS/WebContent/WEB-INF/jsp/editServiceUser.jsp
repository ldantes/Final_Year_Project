
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Service Users</title>
<%@include file="/header.jsp"%>
   
	<script type="text/javascript" src="<c:url value="/resources/javascript/jsFunctions.js" />"></script>


</head>
<body>
    
	

	<h1>Edit Service User: </h1><h3>
	<c:choose>
		<c:when test="${serviceUser.id != null && serviceUser.id != ''}">
		${serviceUser.firstname} ${serviceUser.surname} (${serviceUser.id})
		
		</c:when>
		<c:otherwise>
		New User
		</c:otherwise>
	
	</c:choose>
	</h3>
	<hr/>
	<div class="msg">${userMsg}</div>
	<div class="firedrules">${firedrules}</div>
	<div>
	<c:if test="${serviceUser.id != null && serviceUser.id != ''}">
	<form name="newRequest" method="post">
					<input type="hidden" name="action">
					<input type="hidden" name="requestAction">
					<input type="hidden" name="serviceUserId" value="${serviceUser.id}">
					
	</form>
	<div>
		<nav>
		<a href="javascript:document.newRequest.requestAction.value='newSubstanceEntry';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">Add/View Toxicology History</a>
		||
		<a href="javascript:document.newRequest.requestAction.value='newEngagmentEntry';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">Add/View Meeting Attendance</a>
		||
		<a href="javascript:document.newRequest.requestAction.value='viewAccount';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">View Account</a>
		||
		<a href="javascript:document.newRequest.requestAction.value='viewNotes';document.newRequest.action='ServiceUserServlet';document.newRequest.submit();">View Notes</a>
		</nav>
	</div>	
	</c:if>
	</div>
	
	<div class="inline">
	<form id="editSelectServiceUser" action ="ServiceUserServlet" method="post">
	<input type="hidden" name="requestAction" value="updateServiceUser"/>
	<input type="hidden" name=serviceUserId value="${serviceUser.id}"/> 
	<input type="hidden" name="username" value="${username}"/>
	
	<table class="hor-zebra">
		<tr>
			<td>
				First Name*: 
			</td>
			<td>
				<input name="srvUserfname" required type="text" pattern="^[A-Za-z_ -][A-Za-z_ -]*$" title="No numbers or special characters permited" maxlength="20" minlength="2" value="${serviceUser.firstname}"/>
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Surname*: 
			</td>
			<td>
				<input name="srvUsersname" required type="text" pattern="^[A-Za-z_ -][A-Za-z_ -]*$" title="No numbers or special characters permited" maxlength="30" minlength="2" value="${serviceUser.surname}"/>
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Date of Birth*:
			</td>
			<td>
				<input name="srvUserDOB" required type="date" pattern="^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$" title="Date format YYYY-MM-DD required"  value="${serviceUser.doB}"/>
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Gender*: 
			</td>
			<td>
					<c:choose>
				<c:when test="${serviceUser.gender=='M' || serviceUser.gender=='m'}">
						<c:set var="M" value="checked"/>
						<c:set var="F" value=""/>
				</c:when>
				<c:when test="${serviceUser.gender=='F' || serviceUser.gender=='f'}">
							<c:set var="F" value="checked"/>
						<c:set var="M" value=""/>
				</c:when>
				<c:otherwise>
						<c:set var="F" value=""/>
						<c:set var="M" value=""/>
				</c:otherwise>
				</c:choose>
							
				Male:<input  type="radio" name="srvUserGender"  id="genderM" onClick="assignmentRadio(this.name);" value="M" ${M}/>
				Female:<input  type="radio" name="srvUserGender" id="genderF" onClick="assignmentRadio(this.name);" value="F" ${F} required/>
				
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				PPS/Clinic Number*: 
			</td>
			<td>
				<input name="srvPPS" required type="text" value="${serviceUser.pps}"/>
				
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Contact Number*: 
			</td>
			<td>
				<input name="srvUserContactactNumber" pattern="^[0-9-+]*$" title="Invalid characters present" required type="tel" value="${serviceUser.contactNumber}" maxlength ="14"/>
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Address*: 
			</td>
			<td>
				<textarea name="srvUserAddress" required maxLength="100" style="resize:none; overflow:hidden;" rows="4">${serviceUser.address}</textarea>
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Ethnic Background: 
			</td>
			<td>
				<input name="srvUserEthnicity" type="text" pattern="^[A-Za-z_ -][A-Za-z_ -]*$" title="No numbers or special characters permited" value="${serviceUser.ethnicity}"/>
				
			</td>
			<td class="status"></td>
		</tr>
		<tr>
			<td>
				Nationality: 
			</td>
			<td>
				<input name="srvUserNationality" type="text" pattern="^[A-Za-z_ -][A-Za-z_ -]*$" title="No numbers or special characters permited" value="${serviceUser.nationality}"/>
				
			</td>
			<td class="status"></td>
		</tr>
			<tr>
			<td>
				Occupation: 
			</td>
			<td>
				<input name="srvUserOccupation" type="text" pattern="^[A-Za-z_ -][A-Za-z_ -]*$" title="No numbers or special characters permited" value="${serviceUser.occupation}"/>
				
			</td>
			
		</tr>
		<tr>
			<td>
				Family Information: 
			</td>
			<td>
				<textarea name="srvUserFamily" maxLength="100" style="resize:none; overflow:hidden;" rows="5" >${serviceUser.familyInformation}</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input class="button"  type="submit" value="Submit"/>
			</td>
			<td>
				<input class="button"  type="button" value="Cancel" onClick="this.form.reset();"/>
			</td>
		</tr>
	</table>
	</form>
	</div>
	<div class="inline">
	<c:if test="${serviceUser.id != null && serviceUser.id != ''}">	
	<br/>
	<table class="hor-zebra">
	<tr>
	<th> Stream Details</th>
	</tr>
	<tr>
	<td>Stream:</td>
	<td>"${serviceUser.streamDetails['streamName']}" support level (${serviceUser.streamDetails['supportLevel']})</td>
	</tr>
	<tr>
	<td>Last Updated:</td>
	<td>${serviceUser.streamDetails['updatedOn']}</td>
	</tr>
	<tr>
	<th> Eligibilities</th>
	</tr>
	<c:forEach items="${serviceUser.eligibilityBeans}" var="eligibility">
	<tr>
	<td><c:if test="${eligibility.active == 'N' && eligibility.amount == 0}"><FONT COLOR="red">&#10008;</FONT></c:if><c:if test="${eligibility.active == 'Y' || eligibility.amount > 0 }"><FONT COLOR="green"> &#10004;</FONT></c:if>${eligibility.name} <c:if test="${eligibility.amount > 0}"> (${eligibility.amount})</c:if>
	</td>
	</tr>
	
	</c:forEach>
	<tr>
	<td>
	<table>
	<tr>
	<th> Substance Accumulation</th>
	</tr>
	<c:forEach items="${serviceUser.subAccums}" var="subaccum">
	<tr>
	<td>
	${subaccum.substance} </td><td> <c:if test="${subaccum.accum > 0}"><FONT COLOR="green"></c:if>
							<c:if test="${subaccum.accum == 0}"><FONT COLOR="red"></c:if>${subaccum.accum}</FONT>
	</td>
	</tr>
	
	</c:forEach>
	</table>
	</td>
	<td>
	<table class="hor-zebra">
	<tr>
	<th>Target Date for Negative Toxicology Screen</th>
	</tr>
	<c:if test="${serviceUser.dateToClean.card != null and serviceUser.dateToClean.card != ''}">
	<tr>
	<td>
	Caution Issued: <FONT class="card" COLOR="${serviceUser.dateToClean.card}">${serviceUser.dateToClean.card}</FONT>
	</td>
	</tr>
	<tr>
	<td>
	Target Date: ${serviceUser.dateToClean.dateToClean}
	</td>
	</tr>
	<tr>
	<td>
	<c:choose>
	<c:when test="${serviceUser.dateToClean.extensionApplied != 'Y'}">
	<form id="DTCExtension" action ="ServiceUserServlet" method="post">
	<input type="hidden" name="requestAction" value="addDTCEx"/>
	<input type="hidden" name=serviceUserId value="${serviceUser.id}"/> 
	<input type="hidden" name="username" value="${username}"/>
		Apply ${serviceUser.dateToClean.daysExtension} Day Extension <input type="checkbox" name="addExt"  Onclick="assignCheckBox(this);" value="N" />
		<input type="submit" class="button" value="Apply extension">
	</form>
	</c:when>
	<c:otherwise>
	  ${serviceUser.dateToClean.daysExtension} Day Extension Applied
	</c:otherwise>
	</c:choose>
	
	</td>
	</tr>
	<tr>
	<td>
	Issued on: ${serviceUser.dateToClean.setOn}
	</td>
	</tr>
	</c:if>
	</table>
	</td>
	<tr>
	<td>
	</td>	
	</tr>
	</table>
	</c:if>
	</div>
	
	<%@include file="/footer.html"%>
	

</body>
</html>