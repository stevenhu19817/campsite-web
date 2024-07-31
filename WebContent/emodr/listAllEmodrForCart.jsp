<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emodr.model.*,com.members.model.*"%>

<%
	EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO");
%>

<%
	MemberService memberSvc = new MemberService();
	MembersVO membersVO = memberSvc.findByPrimaryKey(emodrVO.getMember_id());
%>

<html>
<head>
<title>您的訂單資料</title>

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
			<h3>您的訂單資料</h3>
		</tr>

		<tr>
<!-- 			<th>訂單編號</th> -->
			<th>購買人</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
		</tr>
		<tr>
			<%-- 			<td><%=emodrVO.getEmodr_id()%></td> --%>
			<%-- 			<td><%=emodrVO.getMember_id()%></td> --%>
			<td><%=membersVO.getName()%></td>
			<td><%=emodrVO.getEmodr_date()%></td>
			<td><%=emodrVO.getReceipient()%></td>
			<td><%=emodrVO.getAddr()%></td>
			<td><%=emodrVO.getMobile()%></td>
			<td><%=emodrVO.getTotalprice()%></td>
			<td><%=(emodrVO.getEmodr_status() == true) ? "成立" : "不成立"%></td>
		</tr>
	</table>



</body>
</html>