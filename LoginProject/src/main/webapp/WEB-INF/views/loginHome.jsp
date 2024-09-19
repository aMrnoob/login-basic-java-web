<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="static/styles/login.css">
</head>
<body>
	<div class="loginBox">
		<a class="sign-in-here" href="http://localhost:8080/LoginProject/loginHome">Sign in here</a>
		<form action="role" method="post">
			<div class="inputBox">
				<input id="uname" type="text" name="Username" placeholder="Username">
				<input id="pass" type="password" name="Password" placeholder="Password">
			</div>
			<input type="submit" name="" value="Login">
		</form>
		<a class="forget-pw" href="http://localhost:8080/LoginProject/forget-password">Forget Password<br></a>
		<div class="text-center">
			<a class="sign-up" href="http://localhost:8080/LoginProject/sign-up">Sign-Up</a>
		</div>
	</div>
</body>
</html>