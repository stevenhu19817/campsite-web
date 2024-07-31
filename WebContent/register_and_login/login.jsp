<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<%@ include file="/template/navbar.jsp" %>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css">
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/login.jpg") no-repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	-webkit-background-size: cover;
}

#main {
	background-color: #fbefe7;
	margin-top: 150px;
	border-radius: 30px;
	width: 600px;
	height: 70%;
	box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);
}

h1 {
	margin-top: 15px;
	text-align: center;
	margin-bottom: 10px;
}

h5 {
	text-align: center;
	color: #9c9494;
	margin-bottom: 40px;
}

#email {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#passwordDiv {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

#register {
	text-decoration: none;
}

#forget {
	text-decoration: none;
}

#forget:hover {
	text-decoration: underline;
}

#submit {
	margin-top: 25px;
	border-radius: 20px;
	margin-bottom: 20px;
}

form i {
	cursor: pointer;
}
</style>
</head>

<body>
	<div id="main" class="container">
		<h1 style="padding-top: 30px">歡迎回來</h1>
		<h5>
			第一次使用雲露營嗎?<a id="register"
				href="<%=request.getContextPath()%>/register_and_login/register.jsp">&nbsp;帳號註冊</a>
		</h5>
		<input type="hidden" id="noPword" value="${noPassword}">
		<form action="<%=request.getContextPath()%>/account/login.do"
			method="post">
			<div class="mb-3" id="email">
				<input type="email" id="emailAddress" placeholder="電子郵件"
					class="form-control" aria-describedby="emailHelp" name="email"
					value="${email}"> <small style="color: red;">${noEmail}</small>
			</div>
			<div id="passwordDiv" class="mb-3 input-group">
				<input type="password" placeholder="密碼" class="form-control"
					name="password" required id="password"> <span
					style="background-color: white; border-left-color: white;"
					class="input-group-text"><i class="bi bi-eye-slash"
					id="togglePassword"></i></span>
			</div>
			<div style="margin-left: 115px; margin-top: -13px">
				<small style="color: red;">${noPassword}<a id="forget"
					href="/TFA103G4/register_and_login/search_by_email.jsp"
					style="color: red; font-weight: bold;">忘記密碼?</a></small>
				<small style="color: red;">${invalid}</small>
			</div>
			<div class="d-grid gap-2 col-6 mx-auto">
				<button id="submit" class="btn btn-success" type="submit">登入</button>
				<input type="hidden" name="action" value="login">
			</div>
		</form>

	</div>

	<%@ include file="/template/script.html" %>
	<script type="text/javascript">
		if (!$("#noPword").val()) {
			$("#forget").addClass("d-none")
		} else {
			$("#forget").removeClass("d-none")
		}
		const togglePassword = document.querySelector('#togglePassword');
		const password = document.querySelector('#password');
		togglePassword.addEventListener('click', function(e) {

			const type = password.getAttribute('type') === 'password' ? 'text'
					: 'password';
			password.setAttribute('type', type);

			this.classList.toggle('bi-eye');
		});
	</script>
</body>
</html>