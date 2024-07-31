<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("token", request.getParameter("token"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/navbar.jsp" %>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/resetPassword.jpg")
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.container {
	box-shadow: 0 1px 2px rgb(0 0 0/ 10%), 0 2px 4px rgb(0 0 0/ 10%);
}

.container:after {
	position: absolute;
	content: "";
	top: 120px;
	height: 120px;
	width: 1px;
}
</style>
</head>
<body>
	<input type="hidden" id="invalid" value="${invalid}">
	<input type="hidden" id="success" value="${success}">
	<div
		style="max-width: 550px; margin-top: 50px; margin-bottom: -100px;"
		class="alert alert-danger mx-auto d-none" role="alert" id="failNotice">
		<h4 class="alert-heading">
			<i class="fas fa-exclamation-triangle"></i>&nbsp;${invalid}!
		</h4>
		<hr>
		請重新<a
			href="<%=request.getContextPath()%>/register_and_login/search_by_email.jsp"
			class="alert-link">搜尋郵件。</a>
	</div>
	<div
		style="max-width: 550px; margin-top: 50px; margin-bottom: -100px;"
		class="d-none alert alert-success mx-auto" role="alert"
		id="successNotice">
		<h4 class="alert-heading">
			<i class="fas fa-check-circle"></i>&nbsp;${success}
		</h4>
		<hr>
	</div>
	<div
		style="margin-top: 120px; height: 360px; max-width: 570px; background-color: #FBEFE7; border-radius: 8px"
		class="container">
		<h3 class="px-2" style="padding-top: 30px">重設密碼</h3>
		<hr>
		<form class="px-2"
			action="<%=request.getContextPath()%>/passwordReset.do" method="post">
			<div style="margin-bottom: 20px;">
				<label style="margin-top: 10px;" for="password"><h5>輸入新密碼</h5></label>
				<input class="form-control" type="password" name="password"
					id="password"><div id="emailHelp" class="form-text">密碼長度最少8碼，並且須至少有一字母和一數字</div> <small
					style="color: red; margin-top: -20px;">${pwordTooWeak}</small>
			</div>
			<label for="passwordConfirm"><h5>確認新密碼</h5></label> <input
				class="form-control" type="password" name="passwordConfirm"
				id="passwordConfirm"><small style="color: red;">${passwordDiff}</small>
			<input type="hidden" name="token" value="${token}">
			<button type="submit" style="margin-bottom: 10px;" id="reset" class="my-3 float-end btn btn-primary">重設密碼</button>
		</form>
	</div>
	<%@ include file="/template/script.html"%>
	<script type="text/javascript">
	$(document).ready(function() {
		if ($("#invalid").val()) {
			$("#failNotice").removeClass("d-none");
			$("#reset").prop('disabled', true);
		} else if($("#success").val()) {
			$("#successNotice").removeClass("d-none")
		}		
	})
		if ($("#success").val()) {
			window.setTimeout(function() {window.location.href = "<%=request.getContextPath()%>/register_and_login/login.jsp";}, 4000)}
	</script>
</body>
</html>