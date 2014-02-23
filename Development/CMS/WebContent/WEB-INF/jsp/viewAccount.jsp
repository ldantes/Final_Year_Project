<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Service User's Account</title>
</head>
<body>
    <%@include file="/header.jsp"%>
	<script type="text/javascript" src="/resources/javascript/jsFunctions.js"></script>
	

	
	
	<form id ="serviceUserSelect" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="editServiceUser"/>
	<input type="hidden" name="serviceUserId" value=""/>
	</form>
	<h1>Account/Transactions: </h1><h3 style="color:red">
	
			<a href="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${serviceUser.id}';f.submit();">${serviceUser.firstname} ${serviceUser.surname}</a> (${serviceUser.id})
	
	</h3>
	
	
	<hr/>
	
		<div class="inline" >
	<h1>Apply transaction</h1>
	<form id ="newTransaction" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="newTransaction"/>
	<input type="hidden" name="serviceUserId" value="${serviceUser.id}"/>
	<input type="hidden" name="username" value="${username}"/>
		
	<table class="hor-zebra">
	<tr>
		<td>Credited amount: </td>
		<td><input type="number" maxLength="5" name="credit" value="0.00" step="0.50" min="0" max="${serviceUser.streamDetails['maxPoints']}"/></td>
	</tr>
	<tr>
		<c:choose>
		<c:when test="${serviceUser.eligibilityBeans.get(0).active=='Y'}">
		<td>Withdrawn amount:</td>
		<td><input type="number" maxLength="5" name="withdraw" value="0.00" step="0.50" min="0" max="${accountDetails.account_Balance}"/></td>
		</c:when>
		<c:otherwise>
		<td><font color="Red"> Service user is NOT eligible to withdraw their credits</font></td>
		</c:otherwise>
		</c:choose>
	</tr>
	<tr>
	<td>
	<input class="button"  type="submit" value="Adjust balance"/>
	</td>
	</tr>
	<tr><td>Account Balance:</td><td><h3>${accountDetails.account_Balance}</h3></td>
	</tr>
	</table>
	</form>
	</div>
	
	<div class="inline">
	
	<h1>Account History</h1>
	<c:choose>
		<c:when test="${accountDetails.transactions.size() != 0}">
		<table class="hor-zebra">
		<tr>
		<th class="vzebra-even" width="25%">Date</th><th>Withdrawn amount</th><th class="vzebra-even" >Credited amount</th><th>Approved By</th>
		</tr>
		
		<c:forEach var="results" items="${accountDetails.transactions}">
		<tr>
		<td class="vzebra-even">${results.date_of_Transaction}</td><td><p style="color:red">${results.amount_Withdrawn}</p></td><td class="vzebra-even"><p style="color:green">${results.amount_Credited}</p></td><td>${results.approved_By}</td>
		</tr>
		</c:forEach>
		
		</table>
		</c:when>
		<c:otherwise>	
			No previous transactions
		</c:otherwise>
	</c:choose>
	
	
	
	
	</div>
	
	
	
</body>
</html>