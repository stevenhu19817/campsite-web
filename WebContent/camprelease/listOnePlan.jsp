<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);

// 	PlanVO planVO = (PlanVO) request.getAttribute("planVO");
	
	PlanService planSvc = new PlanService();
	List<PlanVO> planlist = planSvc.getByCampId(Integer.parseInt(request.getParameter("campId")));
	pageContext.setAttribute("planVOList", planlist);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>顯示一個配套資料</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
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

.header {
	text-align: center;
}
</style>
</head>
<body>
<header class="header" >
  <h1 class="header__title">顯示配套資料</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="<%=request.getContextPath()%>/camprelease/images/gocamping.jpg" width="500" height="125" border="0"><br>back Home</a></h4>
</table>
</header>

	<table id="example" class="display nowrap" style="width: 100%">
		<thead>
			<tr>
				<th>配套編號</th>
				<th>營地編號</th>
				<th>配套名稱</th>
				<th>配套人數上限</th>
				<th>配套年齡上限</th>
				<th>配套價格</th>
				<th>配套介紹</th>
				<th>配套修改</th>
<!-- 				<th>刪除</th>				 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="planVO" items="${planVOList}">
			<tr>
				<td>【${planVO.planId}】</td>
				<td>【${planVO.campId}】</td>
				<td>【${planVO.planName}】</td>
				<td>【${planVO.planGuestLimit}人】</td>
				<td>【${planVO.planAgeLimit}歲以下】</td>
				<td>【${planVO.planPrice}元】</td>
				<td>【${planVO.planOutline}】</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" style="margin-bottom: 0px;">
						<input type="submit" value="配套修改"> 
						<input type="hidden" name="planId" value="${planVO.planId}"> 
						<input type="hidden" name="campId" value="${planVO.campId}"> 
						<input type="hidden" name="action" value="getOnePlan_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" style="margin-bottom: 0px;"> --%>
<%-- 						<input type="submit" value="刪除"> <input type="hidden" name="planId" value="${planVO.planId}">  --%>
<!-- 						<input type="hidden" name="action" value="delete_plan"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="<%=request.getContextPath()%>/camprelease/js/jquery_3.5.1.js"></script>
	<script src="<%=request.getContextPath()%>/camprelease/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable({
				"scrollX" : true,
				"searching" : false, //搜尋功能, 預設是開啟
				"paging" : false, //分頁功能, 預設是開啟
				"ordering" : false, //排序功能, 預設是開啟
				"lengthMenu" : false,
				// "aria": {
				//     "sortAscending": ": 升冪排列",
				//     "sortDescending": ": 降冪排列"
				// }
				"language" : {
					"processing" : "處理中...",
					"loadingRecords" : "載入中...",
					"lengthMenu" : "顯示 _MENU_ 項結果",
					"zeroRecords" : "沒有符合的結果",
					"info" : "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
					"infoEmpty" : "顯示第 0 至 0 項結果，共 0 項",
					"infoFiltered" : "(從 _MAX_ 項結果中過濾)",
					"infoPostFix" : "",
					"search" : "搜尋:",
					"paginate" : {
						"first" : "第一頁",
						"previous" : "上一頁",
						"next" : "下一頁",
						"last" : "最後一頁"
					},
				}
			});
		});
	</script>
</body>
</html>