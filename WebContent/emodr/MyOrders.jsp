<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emodr.model.*,com.members.model.*"%>

<%
	// 	這塊 用fk 會員編號 從資料庫抓資料
	Integer member_id = (Integer) request.getAttribute("member_id");
	EmodrService emodrSvc = new EmodrService();
	List<EmodrVO> emodrVO2List = emodrSvc.getAllMyOrder(member_id);
	pageContext.setAttribute("emodrVO2List", emodrVO2List);

	String memberName = (String) request.getAttribute("memberName");//接從EmodrServelt的請求
	pageContext.setAttribute("memberName", memberName);
%>


<html>
<head>
<title>歷史訂單</title>

<style>
html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif,
		Microsoft JhengHei;
	background-color: rgb(243, 242, 239);
}

table#table-1 {
	background-color: rgb(230, 239, 217);
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	background-color: rgb(230, 239, 217);
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	/* 	width: 800px;   */
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

th, td {
	border: 1px solid black;
	padding: 5px;
	text-align: center;
}
</style>

</head>

<body>

	<table id="table-1">
		<tr>
			<h4>
				<a href="<%=request.getContextPath()%>/eshop/pages/EShop.jsp">回商城首頁</a>
			</h4>
			<h3>您的歷史訂單如下:</h3>
		</tr>

		<tr>
			<th>訂單編號</th>
			<th>購買人</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
		</tr>
		<c:forEach var="emodrVO" items="${emodrVO2List}">
			<tr>
				<td>${emodrVO.emodr_id}</td>
				<td>${memberName}</td>
				<td>${emodrVO.emodr_date}</td>
				<td>${emodrVO.receipient}</td>
				<td>${emodrVO.addr}</td>
				<td>${emodrVO.mobile}</td>
				<td>${emodrVO.totalprice}</td>
				<td>${(emodrVO.emodr_status== true) ? '成立': '不成立'}</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>