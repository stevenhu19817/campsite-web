<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.camporder.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<%
	CampsiteService campsiteService = new CampsiteService();
	pageContext.setAttribute("campsiteService", campsiteService);
%>

<!DOCTYPE html>
<html>
<style>
img {
	width: 100px;
	height: 100px;
}
</style>
<head>
<meta charset="UTF-8">
<title>查詢單筆營地訂單</title>
<script src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/manager.css">
</head>
<body>
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
						<li><a
							href="<%=request.getContextPath()%>/backendLogin/member.jsp"
							class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a
							href="<%=request.getContextPath()%>/backendLogin/manager.jsp"
							class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span
						class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a
							href="<%=request.getContextPath()%>/product/selectAll.jsp"
							class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a
							href="<%=request.getContextPath()%>/backendLogin/camp.jsp"
							class="camp_list">營地列表</a></li>
						<li><a
							href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp"
							class="camp_list">營地訂單</a></li>
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
							style="margin-left: 50px;">logout</button>
					</form>
				</li>
			</ul>
		</nav>
	</div>
	<div class="rightside">
		<h2>營地訂單單筆查詢</h2>
		<br>
		<h3>
			<a href='campOrder.jsp'>回營地訂單列表</a>
		</h3>
		<div class="searcher">
			<form action="" class="parent">
				<input type="text" class="search" placeholder="營地訂單編號查詢"> <input
					type="button" name="" id="" class="btn_search">
			</form>

			<button type="button" class="btn btn-outline-success">查詢</button>
		</div>
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th>露營訂單編號</th>
					<th>營地名稱</th>
					<th>會員名稱</th>
					<th>預定人數</th>
					<th>入住日期</th>
					<th>退房日期</th>
					<th>營地下訂時間</th>
					<th>營地付款截止時間</th>
					<th>訂單總金額</th>
					<th>訂單狀態</th>
				</tr>
			</thead>
			<tr>
				<td>${campOrderVO.campOrderId}</td>
				<td>${campsiteService.getOneCampsite(campOrderVO.campId).campName}</td>
				<td>${memSvc.findByPrimaryKey(campOrderVO.memberId).name}</td>
				<td>${campOrderVO.guestNumber}</td>
				<td>${campOrderVO.checkInDate}</td>
				<td>${campOrderVO.checkOutDate}</td>
				<td>${campOrderVO.orderDate}</td>
				<td>${campOrderVO.paymentDeadline}</td>
				<td>${campOrderVO.orderTotal}</td>
				<td><c:if test="${campOrderVO.orderStatus == 0}"> ${"未付款"}</c:if>
					<c:if test="${campOrderVO.orderStatus == 1}"> ${"已付款"}</c:if> <c:if
						test="${campOrderVO.orderStatus == 2}"> ${"取消訂單"}</c:if></td>
		</table>
	</div>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
	<script>
		$("#myTable").tablesorter({
			theme : "blue",
			widgets : [ 'zebra' ]
		});
	</script>
<script>
    function prohibitpreviouspage(){

    	if(navigator.userAgent.indexOf('Firefox') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Firefox') + 8)) >= 3.6 ){

    	//Firefox
    	setTimeout("fn_forward()",1);
    	window.history.go(1);
    	}else{ //IE.Chrome.Edge
    	window.history.forward();
    	}
    	}
    	function fn_forward() {
    	history.forward();
    	setTimeout("fn_forward()",1)
    	}
    </script>
        <script type="text/javascript">prohibitpreviouspage();</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="../js/manager.js"></script>
</body>
</html>