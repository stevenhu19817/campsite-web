<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan.model.*"%>

<%
	PlanVO planVO = (PlanVO) request.getAttribute("planVO");

	// PlanService planSvc = new PlanService();
	// List<PlanVO> list = planSvc.getAll();
	// pageContext.setAttribute("planVOList", list);
	pageContext.setAttribute("campId", request.getAttribute("campId"));
// 	pageContext.setAttribute("planId", request.getAttribute("planId"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增配套</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<style>
body {
	background-color: #FFEEE1;
	background-position: center;
	background-size: cover;
	font-family: sans-serif;
	margin-top: 40px;
	font-family: arial;
	margin: 30px;
}

#main {
	background-color: #E4F5E9;
	margin-top: 150px;
	border-radius: 30px;
	width: 600px;
	height: 70%;
	box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);
}

h3 {
	margin-top: 15px;
	text-align: center;
	margin-bottom: 10px;
}

h4 {
	text-align: center;
	color: #9c9494;
	margin-bottom: 40px;
}

#submit {
	margin-top: 25px;
	border-radius: 20px;
	margin-bottom: 20px;
	text-align: center;
}
a{
font-size:5px;
}
</style>
<body>

	<div id="main" class="container">
		<h3>配套資料新增</h3>
		<h4>
			<a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp">
			<img src="<%=request.getContextPath()%>/camprelease/images/title_camp.png" width="100" height="32" border="0"><br>Home</a>
		</h4>
		
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" name="form1">
			<!-- 		<div class="col-md-6"> -->
			<%-- 			<label for="inputcampId" class="form-label">營地編號<font color=red><b>*</b></font>${planVO.CampId}</label> --%>
			<!-- 		</div> -->
			<!-- 		<div class="col-md-6"> -->
			<%-- 			<label for="inputplanId" class="form-label">配套編號<font color=red><b>*</b></font><%=planVO.getPlanId()%></label> --%>
			<!-- 		</div> -->
			<div class="col-md-6">
				<label class="form-label">配套名稱</label> 
				<input type="text" class="form-control" name="planName" id="planName" placeholder="請輸入名稱" value="" />
			</div>
			<div class="col-md-6">
				<label class="form-label">配套人數上限</label> 
				<input type="text" class="form-control" name="planGuestLimit" id="planGuestLimit" placeholder="請輸入人數限制" value="" />
			</div>
			<div class="col-md-6">
				<label class="form-label">配套年齡上限</label> 
				<input type="text" class="form-control" name="planAgeLimit" id="planAgeLimit" placeholder="請輸入年齡限制" value="" />
			</div>
			<div class="col-md-4">
				<label class="form-label">配套價格</label> 
				<input type="text" class="form-control" name="planPrice" id="planPrice" placeholder="請輸入價格" value="" />
			</div>
			<div class="col-md-12">
				<label class="form-label">配套介紹</label> 
				<input type="TEXT" class="form-control" name="planOutline" placeholder="請輸入....">
			</div>
			<div class="button-row d-flex mt-4">
				<div>
					<input type="hidden" name="action" value="insert_plan">
					<button class="btn btn-success ml-auto" type="submit" id="submit">Send</button>
					<input type="hidden" name="campId" value="${campId}">
<%-- 					<input type="hidden" name="planId" value="${planId}"> --%>
				</div>
			</div>
		</FORM>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
		integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>