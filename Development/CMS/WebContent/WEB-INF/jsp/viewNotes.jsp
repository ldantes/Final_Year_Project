<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Service User's Notes</title>


</head>
<body>
    <%@include file="/header.jsp"%>
	<script type="text/javascript" src="/resources/javascript/jsFunctions.js"></script>
	

	
	
	<form id ="serviceUserSelect" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="editServiceUser"/>
	<input type="hidden" name="serviceUserId" value=""/>
	</form>
	<h1>Notes: </h1><h3 style="color:red">
	
			<a href="javascript:var f = document.getElementById('serviceUserSelect'); f.serviceUserId.value='${serviceUser.id}';f.submit();">${serviceUser.name}</a> (${serviceUser.id})
	
	</h3>
	
	
	<hr/>
	
		<div class="inline" >
	<h1>New Note</h1>
	<form id ="newNote" method="post" action="ServiceUserServlet">
	<input type="hidden" name="requestAction" value="newNote"/>
	<input type="hidden" name="serviceUserId" value="${serviceUser.id}"/>
	<input type="hidden" name="username" value="${username}"/>
		
	<table>
	<tr>
		<td>Enter new note:</td>
		<td>
			<textarea name ="note" maxlength=500 placeholder="new note content..." onkeyup="countChar(this)" style="resize:none;" cols="50" rows="10" required /></textarea>
			 
    		<div>Characters remaining:</div><div id="charNum">0</div>
    		<script src="http://code.jquery.com/jquery-1.5.js"></script>
		    <script>
		      function countChar(val) {
		        var len = val.value.length;
		        if (len >= 500) {
		          val.value = val.value.substring(0, 500);
		        } else {
		          $('#charNum').text(500 - len);
		        }
		      };
		    </script>
		</td>
	</tr>
	<tr>
	<td>
	<input type="submit" value="submit new note"/>
	</td>
	</tr>
	</table>
	</form>
	</div>
	
	<div class="inline" width="50%">
	<h1>Notes History</h1>
	<c:forEach var="result" items="${notes}" >
			
	<form id ="updateNote" method="post" action="ServiceUserServlet">
		<input type="hidden" name="requestAction" value="updateNote"/>
		<input type="hidden" name="serviceUserId" value="${serviceUser.id}"/>
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="noteId" value=""/>
	
		
		<table >
			<tr>
								<td colspan="2" align="right" ><p style="color:red">${result.userName} - ${result.updated_On}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
											
								<td>
								<c:choose>
									<c:when test="${username == result.userName }">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javascript:show_hide('note'+${result.id});">Show/Hide</a>
										<div id="note${result.id}"    ${(result.note!=null)?'':'style="display:none;"'}>
											
											<textarea name="${result.id}" id="${result.id}" maxlength=500  readonly="readonly" style="resize:none;" cols="50" rows="5">${result.note}</textarea>
											<a href="javascript:edit(${result.id});">edit</a>
											<div id="submitdiv+${result.id}" style="display:none;" >
												<input type="button" value="submitdiv+${result.id}" onclick="javascript:var f = document.getElementById('updateNote'); f.noteId.value='${result.id}';f.submit();" />
											</div>
											<br>
											
											
										</div>
									</c:when>
									<c:otherwise>
										${result.note}
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
		</table>
		</form>
	
	</c:forEach>	
		
	
	</div>
	
	
	
</body>
<script>

 
function show_hide(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
 }
 
 
function edit(id) {
	var textarea = document.getElementById (id);
    textarea.readOnly = !textarea.readOnly;
    var e = document.getElementById ("submitdiv+"+id);
    if(e.style.display == 'block')
        e.style.display = 'none';
     else
        e.style.display = 'block';
 }
</script>
</html>