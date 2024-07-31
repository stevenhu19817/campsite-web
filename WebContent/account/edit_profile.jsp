<%@page import="com.sun.beans.util.Cache"%>
<%@page import="com.members.model.MemberService"%>
<%@page import="com.members.model.MembersVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	Object account = session.getAttribute("account");
	if (account == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/register_and_login/login.jsp");
		return;
	}
	MemberService memSvc = new MemberService();
	MembersVO memVO = memSvc.findByPrimaryKey((Integer) session.getAttribute("id"));
	pageContext.setAttribute("memVO", memVO);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>

<body>
	<%@ include file="/template/navbar.jsp"%>
	<div class="container" style="margin-top: 100px;">
		<div class="d-flex align-items-start">
			<div style="border: 0px; width: 175px;"
				class="nav flex-column nav-tabs me-3" id="v-pills-tab"
				role="tablist" aria-orientation="vertical">
				<button class="nav-link mb-3 active" id="1"
					data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button"
					role="tab" aria-controls="v-pills-home" aria-selected="true">編輯個人資料</button>
				<button class="nav-link mb-3" id="2"
					data-bs-toggle="pill" data-bs-target="#v-pills-profile"
					type="button" role="tab" aria-controls="v-pills-profile"
					aria-selected="false">更改密碼</button>
				<button class="nav-link mb-3" id="3"
					data-bs-toggle="pill" data-bs-target="#v-pills-messages"
					type="button" role="tab" aria-controls="v-pills-messages"
					aria-selected="false">通知選項</button>
			</div>
			<div class="tab-content container-fluid mx-auto"
				id="v-pills-tabContent">
				<div class="tab-pane fade show active" id="v-pills-home"
					role="tabpanel" aria-labelledby="v-pills-home-tab">
					<div class="container">
						<h2>編輯個人資料</h2>
						<form style="border: 1px solid #DEE2E6; padding: 40px;"
							action="<%=request.getContextPath()%>/account/member.do"
							method="post" enctype="multipart/form-data">
							<div class="mb-3 mx-auto" style="width: 400px;">
								<div class="row">
									<div class="col align-self-center">
									<label for="formFile" class="form-label">個人資料頭貼:</label>
										<div class=""
											style="background-size: cover; width: 75px; height: 75px; border-radius: 50%; display: inline-block; background-image: url('data:image/jpg;base64,${memVO.base64Image}')">
										</div>
									</div>
									<div class="col" style="margin-left: -20px">
									</div>
								</div>
								<input class="form-control" type="file" id="formFile"
									name="photo">
							</div>
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="text" placeholder="姓名" class="form-control"
									id="name" aria-describedby="name" name="name"
									value="${memVO.name}">
							</div>
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="text" placeholder="地址" class="form-control"
									name="address" id="address" value="${memVO.address}">
							</div>
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="text" placeholder="手機號碼" class="form-control"
									name="phone" id="phone" value="${memVO.phone}">
							</div>
							<div class="d-grid gap-2 col-6 ms-auto" style="width: 80px">
								<button id="submit" class="btn btn-success" type="submit">儲存</button>
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="id" value="${memVO.memberId}">
							</div>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
					aria-labelledby="v-pills-profile-tab">
					<div class="container">
						<h2>更改密碼</h2>
						<form method="post" style="border: 1px solid #DEE2E6; padding: 40px;" action="<%=request.getContextPath()%>/account/member.do">
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="password" placeholder="目前密碼" class="form-control"
									id="currentPword" aria-describedby="passwordHelp" name="currentPword">
							</div>
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="password" placeholder="更新密碼" class="form-control"
									id="newPword" name="newPword">
								<div id="emailHelp" class="form-text">密碼長度最少8碼，並且須至少有一字母和一數字</div>
							</div>
							<div class="mb-3 mx-auto" style="width: 400px;">
								<input type="password" placeholder="確認更新密碼" class="form-control"
									id="confirmPword" name="confirmNewPword">
							<input type="hidden" name="action" value="passwordUpdate">
							</div>
							<div class="d-grid gap-2 col-6 ms-auto" style="width: 80px">
								<button id="submitPwordChange" class="btn btn-success"
									type="submit">儲存</button>
							</div>
							<small class="text-center" style="color: green;">${success}</small>
							<small class="text-center" style="color: red;">${wrongPword}</small>
							<small class="text-center" style="color: red;">${samePword}</small>
							<small class="text-center" style="color: red;">${pwordTooWeak}</small>
							<small class="text-center" style="color: red;">${invalid}</small>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
					aria-labelledby="v-pills-messages-tab">...</div>
			</div>
		</div>
	</div>
	<%@ include file="/template/script.html"%>
	<script type="text/javascript">
	$(function () {
	    // Select the tab
	    // $('#profile-tab a:last').tab('show');
	    var tab = new bootstrap.Tab($("#"+ ${index}));
	    tab.show();
	});
	</script>
</body>

</html>