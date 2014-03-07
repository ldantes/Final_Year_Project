
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
	<h1>Add/View Substance Results: </h1><h3 style="color:red">
	
			<a href="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${serviceUser.id}';f.submit();">${serviceUser.firstname} ${serviceUser.surname}</a> (${serviceUser.id})
	
	</h3>
	<hr/>
	
	
	
	<div class="inline" >
	<h2>NEW RESULT DETAILS</h2>
	<form id="newSubstanceResults" action ="ServiceUserServlet" method="post">
	<input type="hidden" name="requestAction" value="insertNewSubstanceResult"/>
	<input type="hidden" name="srvUserid" value="${serviceUser.id}"/> 
	<input type="hidden" name="username" value="${username}"/>
	<div> <p style="color:red">${userMsg}</p></div>
	<table class="hor-zebra">
		
		<c:forEach var="substance" items="${substanceBeans}">
		<tr>
			<td><input type="text" name="${substance.substance}" value="${substance.substance}"  disabled="disabled" /></td>
			
				<td>
				Pass :<input type="radio" required name="result${substance.substance}" id="passed" onClick="assignmentRadio(this.name);" value="P" ${P}/>
				Fail :<input type="radio" name="result${substance.substance}" id="failed" onClick="assignmentRadio(this.name);" value="F" ${F}/>
				</td>
		</tr>
		</c:forEach>
		<tr>
		<td>Administered by: <select name="administeredBy">
							<c:forEach var="user" items="${userBeans}">
								<option >${user.userName }
								</option>
							</c:forEach>
						</select>
		</td>
		<td>Administered on: <input type="date"  required id="datePicker" name="administeredOn" value="${user.administedOn}"/>
		</td>
		</tr>
		<tr>
		<td><input class="button"  type="submit" value="submit"/>
		</tr>
	</table>
	
	</form>
	</div>
	
	<div class="inline" >
	<h2>PREVIOUS RESULT DETAILS</h2>
	<table class="hor-zebra">
		
		<c:choose>
		<c:when test="${previousResultsBeans.size() != 0}">
		<c:set var="count" value="0"/>
		<tr>
			<th >Substance
			</th>
			<th>Result
			</th >
			<th >Administered By
			</th>
			<th>Administered On
			</th>
		</tr>
		<c:forEach var="results" items="${previousResultsBeans}">
		<c:choose>
		<c:when test="${count % 2 ==0}">
			<tr class="vzebra-even">
		</c:when>
		<c:otherwise>
		<tr>
		</c:otherwise>
		</c:choose>
		
			<td >${results.substance}</td>
			
				<td>
				
				<c:choose>
				<c:when test="${results.result=='F' || results.result=='f'}">
						<p style="color:red">Failed (Positive Detection)</p>
				</c:when>
				<c:when test="${results.result=='P' || results.result=='p'}">
						<p style="color:green">Passed (Negative Detection)</p>
				</c:when>
				<c:otherwise>
						Not recorded
				</c:otherwise>
				</c:choose>
				
			</td>
		
		<td >${results.administeredBy}
								
		</td>
		<td>${results.administeredOn}
		</td>
		</tr>
		<c:set var="count" value="${count +1}"/>
		</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td>No previous results recorded!</td>
		</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
<div><%@include file="/footer.html"%></div>
</body>
</html>