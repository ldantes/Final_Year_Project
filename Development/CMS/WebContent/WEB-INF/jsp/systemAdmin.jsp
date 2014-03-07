<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>System Administration</title>

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
</script>

</head>
<body>
	<%@include file="/header.jsp"%>
	
	<h1>System Administration</h1>
	<hr/>
	<h3>Substances</h3>
	<div> <p style="color:red">${userMsg}</p></div>
	<form id="substanceActive" action ="SystemServlet" method="post">
	<input type="hidden" name="requestAction" value="substanceUpdate"/>
	<input type="hidden" name="username" value="${username}"/>
	<table class="hor-zebra">
	<tr>
	<th>Substance
	</th>
	<th>Active
	</th>
	<th>Maximum accumalation
	</th>
	<th>Reset value
	</th>
	<th>Stream Progression/Regression
	</th>
	</tr>
	<c:forEach var="substance" items="${substanceBeans}">
		<tr>
			<td>${substance.substance}<input type="hidden" name="${substance.substance}" value="${substance.substance}"  disabled="disabled" /></td>
			<td>
				<c:choose>
				<c:when test="${substance.active=='Y' || substance.active=='y'}">
						<c:set var="active" value="checked"/>
				</c:when>
				<c:when test="${substance.active=='N' || substance.active=='n'}">
						<c:set var="active" value=""/>
				</c:when>
				<c:otherwise>
						<c:set var="active" value=""/>
				</c:otherwise>
				</c:choose>
				<input type="checkbox" name="subActive${substance.substance}" id="subActive${substance.substance}" Onclick="assignCheckBox(this); "value="Y" ${active}/>
			</td>
			
			<td>
				<input type="number" name="maxVal${substance.substance}" maxLength="2" min="0" id="maxVal${substance.substance}" value="${substance.maxValue}" />
			</td>
			<td>
				<input type="number" name="resetVal${substance.substance}" maxLength="2" min="0" id="resetVal${substance.substance}" value="${substance.resetValue}" />
			</td>
			<td>
			<c:choose>
				<c:when test="${substance.streamRegressionFlag=='Y' || substance.streamRegressionFlag=='y'}">
						<c:set var="ractive" value="checked"/>
				</c:when>
				<c:when test="${substance.streamRegressionFlag=='N' || substance.streamRegressionFlag=='n'}">
						<c:set var="ractive" value=""/>
				</c:when>
				<c:otherwise>
						<c:set var="ractive" value=""/>
				</c:otherwise>
				</c:choose>
							
				<input type="checkbox" name="regFlag${substance.substance}" id="regFlag${substance.substance}" Onclick="assignCheckBox(this);"value="Y" ${ractive}/>
			</td>
				
		</tr>
	</c:forEach>
	<tr>
			<td><input input class="button" type="submit" value="Update Substances"></td>
	</tr>
	</table>
	</form>
	
	<form id="newSubstance" action ="SystemServlet" method="post">
	<input type="hidden" name="requestAction" value="newSubstance"/>
	<input type="hidden" name="username" value="${username}"/>
	<table>
	<tr>
			<td>New Substance:</td><td><input type="text" maxlength ="30" size ="30" name="newSubstance" value=""  /></td></tr>
		<tr>
			<td>
			
				Active:</td><td><input type="checkbox" name="subactive"  Onclick="assignCheckBox(this);" value="N" />
			</td></tr>
		<tr>
			
			<td>
				Maximum Accumalation:</td><td><input type="number" name="submax" maxLength="2" min="0"  value="0" />
			</td></tr>
		<tr>
			<td>
				Reset Value:</td><td><input type="number" name="subreset" maxLength="2" min="0" i value="0" />
			</td></tr>
		<tr>
			<td>
			Stream Progression/Regression:</td><td><input type="checkbox" name="regFlag"  Onclick="assignCheckBox(this);" value="N" />
			</td>
				
		</tr>
		<tr>
			<td><input input class="button" type="submit" value="Add New Substances"></td>
		</tr>
	</table>
	
	</form>
	<hr/>
	<h3>Streams</h3>
	<div> <p style="color:red">${userMsg2}</p></div>
	<form id="streamValues" action ="SystemServlet" method="post">
	<input type="hidden" name="requestAction" value="updateStreams"/>
	<input type="hidden" name="username" value="${username}"/>
	<table class="hor-zebra">
	<tr>
	<th>Stream
	</th>
	<th>Support Level
	</th>
	<th>Point Conversion
	</th>
	<th>Maximum Weekly Credits
	</th>
	</tr>
	<c:forEach var="stream" items="${streams}">
	<tr>
	<td>	${stream.streamName}  <input type="hidden" name="${stream.streamId}" value="${stream.streamId}"/>
	</td>
	<td> ${stream.supportLevel}
	</td>
	<td>	<input type="number" name="point${stream.streamId}" step="0.10"  min="0" id="point${stream.streamId}" value="${stream.pointConversion}" />
	</td>
	<td>	<input type="number" name="max${stream.streamId}" step="1"  min="0" id="max${stream.streamId}" value="${stream.maxPoints}" />
	</td>
	
	</tr>
	</c:forEach>
	<tr>
			<td><input class="button" type="submit" value="Update Streams"></td>
	</tr>
	</table>
	</form>
	<%@include file="/footer.html"%>
</body>
</html>