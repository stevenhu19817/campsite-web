
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<%
	CampsiteService campsiteService = new CampsiteService();
	pageContext.setAttribute("campsiteSvc", campsiteService);
%>
<%
	AdminService adminSvc = new AdminService();
	pageContext.setAttribute("adminSvc", adminSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢單筆營地檢舉</title>
<script src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/alert.css">
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
							class="camp_order">營地訂單</a></li>
						<li><a
							href="<%=request.getContextPath()%>/backendLogin/alert.jsp"
							class="alert_managament">檢舉管理</a></li>
					</ul></li>
				<li><a href="#" class="mky-btn">商城管理 <span
						class="fas fa-caret-down second_2"></span>
				</a>
					<ul class="mky-show">
						<li><a
							href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp"
							class="shopping_list">商城訂單</a></li>
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
		<h2>檢舉管理</h2>
		<br>
		<h3>
			<a href='alert.jsp'>回檢舉列表</a>
		</h3>
		<br>
		<!--         <div class="searcher"> -->
		<!--             <form action="" class="parent"> -->
		<!--                 <input type="text" class="search" placeholder="營地檢舉查詢"> -->
		<!--                 <input type="button" name="" id="" class="btn_search"> -->
		<!--             <button type="button" class="btn btn-outline-success">查詢</button> -->
		<!--             </form> -->
		<!--         </div> -->
		<table class="rwd-table " id="myTable"
			style="position: relative; top: 100px;">
			<tr>
				<th>檢舉流水號</th>
				<th>檢舉人</th>
				<th>營地名稱</th>
				<th>檢舉時間</th>
				<th>檢舉內容</th>
				<th>檢舉圖片1</th>
				<th>檢舉圖片2</th>
				<th>檢舉圖片3</th>
				<th>檢舉狀態</th>
				<th>承辦人</th>
			</tr>

			<tr>
				<td>${campAlertVO.alertId}</td>
				<td>${memSvc.findByPrimaryKey(campAlertVO.memberId).name}</td>
				<td>${campsiteSvc.getOneCampsite(campAlertVO.campId).campName}</td>
				<td>${campAlertVO.reportTime}</td>
				<td>${campAlertVO.content}</td>
				<td><img
					src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${campAlertVO.alertId}&img=1"></td>
				<td><img
					src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${campAlertVO.alertId}&img=2"></td>
				<td><img
					src="<%=request.getContextPath()%>/backendLogin/CampAlert.do?Id=${campAlertVO.alertId}&img=3"></td>
				<td>${campAlertVO.reportStatus == 1 ? "檢舉成功":"未審核"}</td>
				<td>${adminSvc.getOneAdminList(campAlertVO.handeler).adminName}</td>
			</tr>
		</table>


	</div>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/jquery.table2excel.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="../js/alert.js"></script>
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
</body>
</html>