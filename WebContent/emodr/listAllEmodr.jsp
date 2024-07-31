<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emodr.model.*"%>
<%@ page import="com.members.model.*"%>

<%
	EmodrService emodrSvc = new EmodrService();
	List<EmodrVO> list = emodrSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MemberService" />

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商城訂單</title>

<script src="<%=request.getContextPath()%>/emodr/js_emodr/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/emodr/css_emodr/table.css">

<style>
body{
	background-color: #ffe4c4;
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

ul#ul_1{
	list-style: none;
	margin-left: 0px;
	padding:0px;
}
li#li_1 , li#li_2{
	display: inline-block;
}
form#form_1{
	margin-right: 30px;
}
div#alldata{
	padding-top:100px;
}
</style>

</head>

<body>
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
		<a href="<%=request.getContextPath()%>/backendLogin/home.jsp">返回後台管理首頁</a> 
<!-- 記得要改成連到後台首頁!!!!!!!!!!!!!!!!!!! -->
	</h4><br><br>
	
	<%-- ================================================================== --%>
		<%-- 利用jsp的useBean+el語法來抓資瞭庫中的訂單表格唯一鍵pk值顯示在下拉式表單，讓使用者選擇要查哪一筆，此數值傳入當作controller的servlet.java作數值格式驗證 --%>
		<jsp:useBean id="emodrSvc2" scope="page" class="com.emodr.model.EmodrService" />
	<ul id="ul_1">
		<li id="li_1">
			<form id="form_1" method="post" action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>請選擇訂單編號:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc2.all}">
						<option value="${emodrVO.emodr_id}">${emodrVO.emodr_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
		<%-- 下面這段code的功能跟上面一樣，差別在於下拉式選單顯示的改成用會員名來顯示 --%>
		<jsp:useBean id="membersSvc2" scope="page" class="com.members.model.MemberService" />
		<li id="li_2">
			<form method="post" action="<%=request.getContextPath()%>/emodr/emodr.do">
				<b>請選擇收貨人:</b> <select size="1" name="emodr_id">
					<c:forEach var="emodrVO" items="${emodrSvc2.all}">
						<option value="${emodrVO.emodr_id}">
							<c:forEach var="membersVO" items="${membersSvc2.all}">
								<c:if test="${emodrVO.member_id==membersVO.memberId}">
									${membersVO.name}
							    </c:if>
							</c:forEach>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
	</ul>
<%-- ================================================================== --%>
	
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
			<th>訂單編號</th>
			<th>買方編號與名字</th>
			<th>訂單日期</th>
			<th>收貨人</th>
			<th>收貨地址</th>
			<th>收貨電話</th>
			<th>總價</th>
			<th>訂單狀態</th>
			<th>修改</th>
			<th>隱藏</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="emodrVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${emodrVO.emodr_id}</td>
			<td>${emodrVO.member_id}【${membersSvc.findByPrimaryKey(emodrVO.member_id).name}】</td>
			<td>${emodrVO.emodr_date}</td>
			<td>${emodrVO.receipient}</td>
			<td>${emodrVO.addr}</td>
			<td>${emodrVO.mobile}</td>
			<td>${emodrVO.totalprice}</td>
			<td>${(emodrVO.emodr_status == true)? '成立': '不成立'}</td>			
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emodr/emodr.do" style="margin-bottom: 0px;">
					<input type="submit" value="編輯">
					<input type="hidden" name="emodr_id" value="${emodrVO.emodr_id}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emodr/emodr.do" style="margin-bottom: 0px;">
					<input type="submit" value="隱藏"> 
					<input type="hidden" name="emodr_id" value="${emodrVO.emodr_id}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>	
</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/emodr/js_emodr/table.js"></script>

</body>
</html>