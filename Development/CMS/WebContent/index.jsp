<html>
    <head>
        		<title>Login</title>
	</head>
	
	<body>
		
			<h1>Contingency Management System </h1><h3>v1.0.0</h3> ${username} ${userMessage}
			
			<form id="loginForm" method="POST" action="UserServlet">
	        	<input type="hidden" name="requestAction" value="login"/>
			  	Username: <input type="text"     name="username" size="25" maxlength="25"><br />
			  	Password: <input type="password" name="password" size="25" maxlength="25"><br /><br /> 
			
			  	<input type="submit" value="Login">
			  	<input type="reset"  value="Reset">
			</form>		
		
	</body>
</html>