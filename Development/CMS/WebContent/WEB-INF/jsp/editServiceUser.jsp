
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Service Users</title>

</head>
<body>
    <%@include file="/header.jsp"%>
	
	<h1>Edit ${serviceUser.name}</h1>
	<hr/>
	<form id="editSelectServiceUser" action ="ServiceUserServlet" method="post">
	<input type="hidden" name="requestAction" value="updateServiceUser"/>
	<input type="hidden" name="srvUserid" value="${serviceUser.id}"/>${serviceUser.id} ${serviceUser.updatedBy} ${username} ${serviceUser.updatedOn}
	<input type="hidden" name="username" value="${username}"/>
	<table>
		<tr>
			<td>
				Full Name: 
			</td>
			<td>
				<input name="srvUsername" type="text" value="${serviceUser.name}"/>
			</td>
		</tr>
		<tr>
			<td>
				Date of Birth
			</td>
			<td>
				<input name="srvUserDOB" type="date" value="${serviceUser.doB}"/>
			</td>
		</tr>
		<tr>
			<td>
				Gender: 
			</td>
			<td>
				<input  name="srvUserGender" value="${serviceUser.gender}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				Contact Number: 
			</td>
			<td>
				<input name="srvUserContactactNumber" type="tel" value="${serviceUser.contactNumber}" maxlength ="14"/>
			</td>
		</tr>
		<tr>
			<td>
				Address: 
			</td>
			<td>
				<textarea name="srvUserAddress" maxLength="100" style="resize:none" rows="4">
				${serviceUser.address}
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				Ethnicity: 
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
				<textarea name="srvUserFamily" maxLength="100" style="resize:none" rows="5">
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
	
	<script>
	 
	  function genderRadio() {

	        var radio_check_val = "";
	        var i;
	        for (i = 0; i < document.getElementsByName('srvUserGender').length; i++) {
	            if (document.getElementsByName('srvUserGender')[i].checked) {
	                radio_check_val = document.getElementsByName('srvUserGender')[i].value;
	                document.getElementsByName('srvUserGender').value = radio_check_val;
	                serviceUser.gender = radio_check_val;
	            }

	        }
	       



	    }
	</script>
</body>
</html>