<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService dao = new MemberService();
	List<MembersVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員管理</title>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/member.css">
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
						<li><a href="<%=request.getContextPath()%>/backendLogin/member.jsp" class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span
						class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a href="<%=request.getContextPath()%>/product/selectAll.jsp"
							class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/camp.jsp" class="camp_list">營地列表</a></li>
					 <li><a href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp"class="camp_order">營地訂單</a></li>
						<li><a href="<%=request.getContextPath()%>/backendLogin/alert.jsp" class="alert_managament">檢舉管理</a></li>
					</ul></li>
				<li><a href="#" class="mky-btn">商城管理 <span
						class="fas fa-caret-down second_2"></span>
				</a>
					<ul class="mky-show">
						<li><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp" class="shopping_list">商城訂單</a></li>
					</ul>
					</li>
					<li>
                <form METHOD="get" ACTION="<%=request.getContextPath()%>/backendLogin/home.do">
                  <button type="submit" class="btn btn-outline-secondary" style="margin-left: 50px;">logout</button>
                </form>
                </li>
			</ul>
		</nav>
	</div>
	<div class="rightside">
		<h2>會員管理</h2>
		<br>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		
		<div class="searcher">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/backendLogin/updatemember.do">
				<input type="text" class="search" name="memberId"
					placeholder="會員編號查詢"> <input type="hidden" name="action"
					id="" class="btn_search" value="getOne_For_Display">
				<button type="submit" class="btn btn-outline-success">查詢</button>
				<button type="button" class="btn btn-outline-success" id="export1">匯出</button>
			</Form>
		</div>
		<table id="myTable1" class="tablesorter">
			<thead>
				<tr>
					<th>會員編號</th>
					<th>姓名</th>
					<th>電子信箱</th>
					<th>連絡電話</th>
					<th>身分</th>
					<th>狀態</th>
					<th width=300px>地址</th>
					<th>編輯</th>
				</tr>
			</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${VO.memberId}</td>
					<td>${VO.name}</td>
					<td>${VO.email}</td>
					<td>${VO.phone}</td>
					<td>${VO.membership == 1 ? "營地業主":"一般會員"}</td>
					<td>${VO.memberStatus == 1 ? "已停權":"使用中"}</td>
<%-- 					<td>${VO.memberStatus}</td> --%>
					<td>${VO.address}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/updatemember.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" id="upd" class="btn btn-primary"> <input type="hidden"
								name="memberId" value="${VO.memberId}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
				</tr>

			</c:forEach>
		</table>

		<%@ include file="page2.file"%>


	</div>
	<script src="../js/jquery.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/jquery.table2excel.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></link>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
	<script>
		$("#myTable1").tablesorter({
			theme : "blue",
			widgets : [ 'zebra']
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="../js/member.js"></script>
	<script>
	
	$(document).ready(function(){			
		$("#export1").on("click", function() {
				  $("#myTable1").table2excel({
				    // exclude CSS class
				    exclude: ".noExl",
				    name: "Worksheet Name",
				    filename: "會員列表", //do not include extension
				    fileext: ".xls" // file extension
				  }); 
		});
		});
	</script>
	
		</body>
		</html>
	