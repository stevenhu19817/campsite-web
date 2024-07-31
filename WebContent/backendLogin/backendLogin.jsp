<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>管理員登入頁面</title>
<script src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/homesign.css">
</head>
<body>
	<div class="logo">
		<a href="#"> <img src="../images/200x1280.png"
			style="width: 100%; height: 200px;">
		</a>
	</div>
	<div class="divform">
<%-- 		<c:if test="${not empty errorMsgs}"> --%>
<!-- 			<font style="color: red">請修正以下錯誤:</font> -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li style="color: red">${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<%-- 		</c:if> --%>
		<form
			action="<%=request.getContextPath()%>/backendLogin/backendLogin.do"
			method="post">
			<p>
				請輸入管理員編號: <input type="text" name="adminId">
				<c:if test="${not empty errorMsgs}">
				<c:forEach var="message" items="${errorMsgs}">
					<small style="color: red">${message}</small>
				</c:forEach>
		
		</c:if>

			</p>
			<p>
				請輸入管理員密碼: <input type="PASSWORD" name="adminPwd"><br> <small
					style="color: red;">${noPassword}</small>
			</p>

			<div class="button">
				<button type="submit" class="btn btn-primary" style="margin-right: 40px;">送出</button>
				<input type="hidden" name="action" value="login"> <a
					href="<%=request.getContextPath()%>/homepage/index.jsp">回到首頁</a>

			</div>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>

	<script src="../js/homesign.js"></script>
</body>
</html>