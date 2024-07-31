<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
		rel="stylesheet">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<style type="text/css">
body {
	background: url("<%=request.getContextPath()%>/img/searchByEmail.jpg")
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
	<nav class="navbar navbar-expand-md navbar-light sticky-top"
		style="background-color: #7E8B66;">
		<div class="container-fluid">
			<a class="navbar-brand ms-lg-5"
				href="<%=request.getContextPath()%>/homepage/index.jsp"
				style="font-size: 1.25em; color: #FBEFE7;">GoCamping</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<input type="hidden" id="account" value="${name}">
	</nav>
	<input type="hidden" id="success" value="${success}">
	<div
		style="max-width: 550px; margin-top: 100px; margin-bottom: -100px;"
		class="alert alert-success mx-auto" role="alert" id="successNotice">
		<h4 class="alert-heading">
			<i class="fas fa-check-circle"></i>&nbsp;寄送成功!
		</h4>
		<hr>
		${success}
	</div>
	<div
		style="margin-top: 120px; height: 260px; max-width: 550px; background-color: #FBEFE7; border-radius: 8px"
		class="container">
		<h3 class="px-2" style="padding-top: 30px">尋找您的帳號</h3>
		<hr>
		<h5 class="px-2" style="margin-bottom: 20px">請輸入您註冊的電子信箱帳號。</h5>
		<form class="px-2"
			action="<%=request.getContextPath()%>/resetPassword.do" method="post">
			<input class="form-control" type="email" name="email"> <small
				style="color: red;">${noEmail}</small>
			<button type="submit" class="mt-3 float-end btn btn-primary">搜尋</button>
		</form>
	</div>

	<%@ include file="/template/script.html"%>
	<script type="text/javascript">
		if ($("#success").val()) {
			$("#successNotice").removeClass("d-none");
		} else {
			$("#successNotice").addClass("d-none");

		}
	</script>
</body>
</html>