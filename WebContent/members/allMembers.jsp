<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>

<%
	MemberService mService = new MemberService();
	List<MembersVO> list = mService.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="memSev" scope="page"
	class="com.members.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style type="text/css">
	img {
	max-height: 200px;
	max-width: 200px;
}
</style>
</head>
<body>
	<div class="container text-center mt-5">
		<h1>會員資料一覽表</h1><a href="<%=request.getContextPath()%>/members/index.jsp">回首頁</a>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">會員編號</th>
					<th scope="col">會員姓名</th>
					<th scope="col">會員電話</th>
					<th scope="col">會員email</th>
					<th scope="col">會員身分</th>
					<th scope="col">會員狀態</th>
					<th scope="col">大頭貼</th>
					<th scope="col">會員地址</th>
					<th scope="col">編輯</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="memVO" items="${list}">
					<tr>
						<td>${memVO.memberId}</td>
						<td>${memVO.name}</td>
						<td>${memVO.phone}</td>
						<td>${memVO.email}</td>
						<td>${memVO.membership}</td>
						<td>${memVO.memberStatus}</td>
						<td><img src="data:image/jpg;base64,${memVO.base64Image}" /></td>
						<td>${memVO.address}</td>
						<td>
							<form action="<%=request.getContextPath()%>/account/member.do" method="post">
								<button type="submit">編輯</button>
								<input type="hidden" name="id" value="${memVO.memberId}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<table>

	</table>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>