<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emodr.model.*"%>
<%@ page import="com.members.model.*"%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO");
%>

<%-- 取出 對應的membersVO物件--%>
<%
	MemberService memberSvc = new MemberService();
	MembersVO membersVO = memberSvc.findByPrimaryKey(emodrVO.getMember_id());
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>單筆訂單資料</title>

<script src="<%=request.getContextPath()%>/emodr/js_emodr/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/emodr/css_emodr/table.css">

<style>
body{
	background-color: #ffe4c4;
}
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
	display: inline-block;
}
a:hover{
	color:red;
	font-size: 25px;
}

table {
	width: 95%;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid blue;
	border-collapse:collapse;
}

th, td {
	padding: 5px;
	text-align: center;
}

div#alldata{
	padding-top:100px;
}
</style>

</head>
<body >
<!-- 側邊攔結構=============================================================================================================== -->
	<div class="container">
		<nav class="sidebar">
			<div class="min_picture">
				<h1>後臺管理</h1>
			</div>
			<ul>
				<li><a href="#" class="feat-btn">帳號管理 <span
						class="fas fa-caret-down first"></span>
				</a>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/member.jsp" class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a href="<%=request.getContextPath()%>/product/selectAll.jsp" class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/camp.jsp" class="camp_list">營地列表</a></li>
						<li><a href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp"
							class="camp_order">營地訂單</a></li>
						<li><a href="<%=request.getContextPath()%>/backendLogin/alert.jsp" class="alert_managament">檢舉管理</a></li>
					</ul></li>
				<li><a href="#" class="mky-btn">商城管理 <span
						class="fas fa-caret-down second_2"></span>
				</a>
					<ul class="mky-show">
						<li><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp" class="shopping_list">商城訂單</a></li>
					</ul></li>

				<li>
					<form METHOD="get"
						ACTION="<%=request.getContextPath()%>/backendLogin/home.do">
						<button type="submit" class="btn btn-outline-secondary"
							id="btnlog">logout</button>
					</form>
				</li>
			</ul>
		</nav>
	</div>
<!-- 側邊攔結構=============================================================================================================== -->

<div id="alldata">

	<h4>
		<a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp">返回訂單總表</a>
	</h4>


	<table>
		<tr>
			<th>訂單編號</th>
			<th>買方編號與名字</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
		</tr>
		<tr>
			<td><%=emodrVO.getEmodr_id()%></td>
			<%-- 			<td><%=emodrVO.getMember_id()%></td> --%>
			<td><%=emodrVO.getMember_id()%>【<%=membersVO.getName()%>】</td>
			<td><%=emodrVO.getEmodr_date()%></td>
			<td><%=emodrVO.getReceipient()%></td>
			<td><%=emodrVO.getAddr()%></td>
			<td><%=emodrVO.getMobile()%></td>
			<td><%=emodrVO.getTotalprice()%></td>
			<td><%= (emodrVO.getEmodr_status()== true)? "成立": "不成立"%></td>
		</tr>
	</table>
</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/emodr/js_emodr/table.js"></script>


</body>
</html>