    <!DOCTYPE html>
    <head>
        		<title>Login</title>
        		<%@include file="header.jsp"%>
	</head>
	
	<body>
		
	 <p style="color:red">${userMessage}</p>
			
			<form id="loginForm" method="POST" action="UserServlet" class="login-form">
				<h1>Login</h1>
	        	<input type="hidden" name="requestAction" value="login"/>
	        	<table>
	        	<tr>
	        		<td>Username:</td><td> <input type="text"    placeholder="Enter your username..." name="username" size="25" maxlength="25" required></td>
	        	</tr>
	        	<tr>
	        		<td>Password:</td><td> <input type="password" placeholder="Enter your password..." name="password" size="25" maxlength="25" required></td>
	        	</tr>
				</table>
			  	<input class="button" type="submit" value="Login">
			  	<input class="button" type="reset"  value="Reset">
			</form>		
		
	</body>
