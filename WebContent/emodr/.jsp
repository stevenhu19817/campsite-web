<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emodr.model.*"%>

<%
	EmodrService emodrSvc = new EmodrService();
	List<EmodrVO> list = emodrSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="membersSvc" scope="page"
	class="com.members.model.MemberService" />

<html>
<head>
<title>所有訂單資料</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	/* 	width: 800px;   */
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有訂單資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/eshop/pages/EShop.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>商城訂單編號</th>
			<th>買方會員編號</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="emodrVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${emodrVO.emodr_id}</td>

<%-- 		    <td>${emodrVO.member_id}</td> --%>
				<td>${emodrVO.member_id}【${membersSvc.findByPrimaryKey(emodrVO.member_id).name}】</td>

				<td>${emodrVO.emodr_date}</td>
				<td>${emodrVO.receipient}</td>
				<td>${emodrVO.addr}</td>
				<td>${emodrVO.mobile}</td>
				<td>${emodrVO.totalprice}</td>
				<td>${emodrVO.emodr_status}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emodr/emodr.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="編輯"> <input type="hidden"
							name="emodr_id" value="${emodrVO.emodr_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emodr/emodr.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="emodr_id" value="${emodrVO.emodr_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>

</body>
</html>