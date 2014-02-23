
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Service Users</title>

</head>
<body>
    <%@include file="/header.jsp"%>
	<script type="text/javascript" src="/resources/javascript/jsFunctions.js"></script>
	<form id ="serviceUserSelect" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="editServiceUser"/>
	<input type="hidden" name="serviceUserId" value=""/>
	</form>
	<h1>Add/View Attendance: </h1><h3 style="color:red">
	
			<a href="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${serviceUser.id}';f.submit();">${serviceUser.firstname} ${serviceUser.surname}</a> (${serviceUser.id})
	
	</h3>
	<hr/>
	
	
	
	<div class="inline" >
	<h2>NEW ATTENDANCE</h2>
	<form id="newAttendance" action ="ServiceUserServlet" method="post" onsubmit="return confirm('Please confirm entry before continuing\n\'OK\' to CONTINUE ,\'Cancel\' to RETURN'); ">
	<input type="hidden" name="requestAction" value="insertNewAttendance"/>
	<input type="hidden" name="srvUserid" value="${serviceUser.id}"/> 
	<input type="hidden" name="username" value="${username}"/>
	<div> <p style="color:red">${userMsg}</p></div>
	<table class="hor-zebra">
		
		<tr>
			<td>Time of meeting:</td>
			<td><input type="time" name="attndTime" value=""/>	</td>
		</tr>
		<tr>
			<td>Date of meeting:</td>
			<td><input type="date" name="attndDate" value=""/>	</td>
		</tr>
		<tr>
			<td>Meeting attended:</td>
			<td>Yes:<input type="radio" name="attended" id="passed" onClick="assignmentRadio(this.name);" value="Y"/>
				No:<input type="radio" name="attended" id="failed" onClick="assignmentRadio(this.name);" value="N" />
				<input type="radio" name="attended" id="default" onClick="assignmentRadio(this.name);" value="" style="display:none" checked="checked"/></td>
			
		</tr>
		<tr>
			<td>Meaningful participation:</td>
			<td>Yes:<input type="radio" name="particaption" id="passed" onClick="assignmentRadio(this.name);" value="Y"/>
				No:<input type="radio" name="particaption" id="failed" onClick="assignmentRadio(this.name);" value="N" />
				<input type="radio" name="particaption" id="default" onClick="assignmentRadio(this.name);" value="" style="display:none" checked="checked"/></td>
			
		</tr>
		<tr>
			<td>Reason for missed attendance:</td>
			<td>
				<textarea name="missedReason" maxLength="50" style="resize:none; overflow:hidden;" rows="2" cols="25" ></textarea>
			</td>
		</tr>
		<tr>
			<td>Valid reason for absence:</td>
			<td>Yes:<input type="radio" name="reasonValid" id="passed" onClick="assignmentRadio(this.name);" value="Y"/>
				No:<input type="radio" name="reasonValid" id="failed" onClick="assignmentRadio(this.name);" value="N" />
				<input type="radio" name="reasonValid" id="default" onClick="assignmentRadio(this.name);" value="" style="display:none" checked="checked"/></td>
				
		</tr>
		<tr>
			<td>Review Meeting:</td>
			<td>Yes:<input type="radio" name="reviewMeeting" id="passed" onClick="assignmentRadio(this.name);" value="Y"/>
				No:<input type="radio" name="reviewMeeting" id="failed" onClick="assignmentRadio(this.name);" value="N" />
				<input type="radio" name="reviewMeeting" id="default" onClick="assignmentRadio(this.name);" value="" style="display:none" checked="checked"/></td>
			
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
	
	<div class="inline" >
	<h2>PREVIOUS ATTENDANCES</h2>
	<table class="hor-zebra">
		
		<c:choose>
		<c:when test="${attendanceDetails != null &&  attendanceDetails.size() != 0}">
		<tr><th>Member of Staff	</th><th class="vzebra-even" >Professional</th><th>Attended</th><th class="vzebra-even" >Time/Date</th><th>Active participation</th><th class="vzebra-even">Reason for absence</th><th>Valid reason for absence</th><th class="vzebra-even">Review meeting</th></tr>
		<c:forEach var="results" items="${attendanceDetails}">
		<tr><td >${results.username}</td>
			<td class="vzebra-even">${results.staffProfession}
			</td>
				<td>
				
				<c:choose>
				<c:when test="${results.attended=='N' || results.attended=='n'}">
						<p style="color:red">No</p>
				</c:when>
				<c:when test="${results.attended=='Y' || results.attended=='y'}">
						<p style="color:green">Yes</p>
				</c:when>
				<c:otherwise>
						Not recorded
				</c:otherwise>
				</c:choose>
				
			</td>
			
		<td class="vzebra-even" >${results.timeDate}</td>
		<td><c:choose>
				<c:when test="${results.participation=='N' || results.participation=='n'}">
						<p style="color:red">No</p>
				</c:when>
				<c:when test="${results.participation=='Y' || results.participation=='y'}">
						<p style="color:green">Yes</p>
				</c:when>
				
				</c:choose></td>
		<td class="vzebra-even"> <div style="word-wrap: break-word;">${results.attndFailedReason}</div></td>
		<td>
			<c:choose>
				<c:when test="${results.validReason=='N' || results.validReason=='n'}">
						<p style="color:red">No</p>
				</c:when>
				<c:when test="${results.validReason=='Y' || results.validReason=='y'}">
						<p style="color:green">Yes</p>
				</c:when>
				
				</c:choose>
		</td>
		<td class="vzebra-even">
			<c:choose>
				<c:when test="${results.treatmentReviewMeeting=='N' || results.treatmentReviewMeeting=='n'}">
						<p style="color:red">No</p>
				</c:when>
				<c:when test="${results.treatmentReviewMeeting=='Y' || results.treatmentReviewMeeting=='y'}">
						<p style="color:green">Yes</p>
				</c:when>
				<c:otherwise>
						Not recorded
				</c:otherwise>
				</c:choose>
			
		</td>
		
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td>No previous engagements recorded!</td>
		</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>

</body>
</html>