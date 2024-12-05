<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in Form</title>
     <link rel="stylesheet" type="text/css" href="css/log-on.css">
</head>
<body>
	<div class="background" style= " background-image: url('<%= request.getContextPath() %>/image/login-background-image.jpg'); " >
		<div class="background-flur" style = "background-color: rgba(0, 0, 0, 0.5); ">
		    <form class="login-form" action="${pageContext.request.contextPath}/MainMenuServlet">
		        <h2>Sign in</h2>
		        <div class="form-group">
		            <label for="username">User Name</label>
		            <input type="text" id="username" name="username" placeholder="Enter your username" >
		        </div>
		        <div class="form-group">
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password" placeholder="Enter your password" >
		        </div>
		        <div class="form-actions">
		            <button type="submit" name="action" value="signin">Sign in</button>
		            <button type="submit" name="action" value="signupredirect">Sign up</button>
		        </div>
		    </form>
    	</div>
    </div>
</body>
</html>
