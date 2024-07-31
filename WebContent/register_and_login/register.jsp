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
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/register.jpg")
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

#main {
	margin-top: 80px;
	background-color: #fbefe7;
	box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);
	border-radius: 30px;
	width: 600px;
	height: 70%;
}

#form>* {
	margin-left: auto;
	margin-right: auto;
	width: 60%;
}

h1 {
	margin-top: 15px;
	text-align: center;
	margin-bottom: 10px;
}

h3 {
	text-align: center;
	color: #9c9494;
	margin-bottom: 40px;
}

#submit {
	margin-top: 20px;
	border-radius: 20px;
	margin-bottom: 20px;
}

.invalid-feedback {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>

<body>
	<div id="main" class="container">
		<h1 style="padding-top: 30px">雲露營</h1>
		<h3>一起去露營吧!</h3>
		<form id="form"
			action="<%=request.getContextPath()%>/account/register.do"
			method="post" class="needs-validation" novalidate>
			<div class="mb-3">
				<input type="text" placeholder="姓名" class="form-control" id="name"
					name="name" value="${name}" required>
				<div class="invalid-feedback">請輸入使用者名稱</div>
			</div>
			<div class="mb-3">
				<input type="email" placeholder="電子郵件" class="form-control"
					id="email" aria-describedby="emailHelp" name="email"
					value="${email}" required> <small style="color: red;">${emailRepeat}</small>
				<div class="invalid-feedback">請輸入電子信箱地址</div>
			</div>
			<div class="mb-3">
				<input type="password" placeholder="密碼" class="form-control"
					id="password" name="password" required>
					<div id="emailHelp" class="form-text">密碼長度最少8碼，並且須至少有一字母和一數字</div> 
					<small style="color: red;">${pwordTooWeak}</small>
				<div class="invalid-feedback">請輸入密碼</div>
			</div>
			<div class="mb-3" id="password">
				<input type="password" placeholder="密碼確認" class="form-control"
					name="passwordConfirm" id="passwordConfirm" required> <small
					style="color: red;">${passwordDiff}</small>
				<div class="invalid-feedback">請確認密碼</div>
			<small style="color: green;">${success}</small>
			</div>
			<div class="d-grid gap-2 col-6 mx-auto">
				<button id="submit" class="btn btn-success" type="submit">註冊</button>
			</div>
			<input type="hidden" value="register" name="action">
		</form>
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
	</div>
	<%@ include file="/template/script.html" %>
	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>

</html>