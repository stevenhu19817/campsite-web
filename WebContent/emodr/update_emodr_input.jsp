<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emodr.model.*"%>

<%
  EmodrVO emodrVO = (EmodrVO) request.getAttribute("emodrVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>訂單資料修改</title>
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
/*     border: 2px solid black; */
/* 	text-align: center; */
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
/* border: 1px solid blue; */
  	border-collapse:collapse;
/*      border: 1px solid #CCCCFF;  */
  }
  th{
    padding: 10px;
    text-align: center;
  }
  div#alldata{
	padding-top:100px;
}
td{
	width:10px;
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
		 <h4><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp">返回訂單總表</a></h4>
			<br><br>
<h3>訂單資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emodr.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=emodrVO.getEmodr_id()%></td>
	</tr>
	
	<jsp:useBean id="membersSvcrex" scope="page" class="com.members.model.MemberService" />
	<tr>
		<td>買方會員:<font color=red><b>*</b></font></td>
		<td><select size="1" name="member_id">
			<c:forEach var="membersVO" items="${membersSvcrex.all}">
				<option value="${membersVO.memberId}" ${(emodrVO.member_id==membersVO.memberId)?'selected':'' } >${membersVO.name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input type="TEXT" name="emodr_date" size="20"	value="<%=emodrVO.getEmodr_date()%>" /></td>
	</tr>
	<tr>
		<td>收貨人:</td>
		<td><input type="TEXT" name="receipient" size="20"	value="<%=emodrVO.getReceipient()%>" /></td>
	</tr>
	<tr>
		<td>收貨地址:</td>
		<td><input type="TEXT" name="addr" size="20"	value="<%=emodrVO.getAddr()%>" /></td>
	</tr>
	<tr>
		<td>收貨電話:</td>
		<td><input type="TEXT" name="mobile" size="20" value="<%=emodrVO.getMobile()%>" /></td>
	</tr>
	<tr>
		<td>總價:</td>
		<td><input type="TEXT" name="totalprice" size="20" value="<%=emodrVO.getTotalprice()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="emodr_status" size="10" value="<%=emodrVO.getEmodr_status()%>" /></td>
	</tr>
	</table>
		<br>
		<input type="hidden" name="action" value="update">  <!-- 送出update字串讓servlet去接，告訴servlet要做的事是update -->
		<input type="hidden" name="emodr_id" value="<%=emodrVO.getEmodr_id()%>"> <!-- 送出<%=emodrVO.getEmodr_id()%>讓servlet去接，告訴servlet emodr_id字串-->
		<input type="submit" value="送出修改">
	</FORM>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/emodr/js_emodr/table.js"></script>
	</body>
</html>