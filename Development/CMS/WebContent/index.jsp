    <!DOCTYPE html>
    <head>
        		<title>Login</title>
        		<%@include file="header.jsp"%>
	</head>
	
	<body>
		
	 <p style="color:red">${userMessage}</p>
			
			<form id="loginForm" method="POST" action="UserServlet">
	        	<input type="hidden" name="requestAction" value="login"/>
			  	Username: <input type="text"    placeholder="Enter your username..." name="username" size="25" maxlength="25" required><br />
			  	Password: <input type="password" placeholder="Enter your password..." name="password" size="25" maxlength="25" required><br /><br /> 
			
			  	<input type="submit" value="Login">
			  	<input type="reset"  value="Reset">
			</form>		
		
	</body>
