<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.plan.model.*"%>

<%
	PlanService planSvc = new PlanService();
	List<PlanVO> list = planSvc.getAll();
	pageContext.setAttribute("list", list);

	PlanVO planVO = (PlanVO) request.getAttribute("planVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>All配套資料</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
<style>
div.dataTables_wrapper {
	background-color: whitesmoke;
	width: 800px;
	margin: 0 auto;
}

body {
	background-color: #7B7571;
}

.header {
    width:400px;
    height:200px;
    text-align:center;
    }
</style>

</head>
<body>

<header class="header" >
  <table id="table-1">
		 <a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp">back home</a>
</table>
</header>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<br>
	<br>
	<br>
	<div class="container">
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/camprelease/addCampRel.jsp"
			role="button"><image src="svg/patch-plus.svg"></image>Add</a>
			
		<table id="example" class="display nowrap" style="width: 100%">
			<thead>
				<tr>
					<th>配套編號</th>
					<th>營地編號</th>
					<th>配套名稱</th>
					<th>配套人數上限</th>
					<th>配套年齡上限</th>
					<th>配套價格</th>
					<th>配套修改</th>
					<th>刪除</th>					
				</tr>
			</thead>
			<c:forEach var="planVO" items="${list}">
				<tbody>
					<tr>
						<td>【${planVO.planId}】</td>
						<td>【${planVO.campId}】</td>
						<td>【${planVO.planName}】</td>
						<td>【${planVO.planGuestLimit}人】</td>
						<td>【${planVO.planAgeLimit}歲以下】</td>
						<td>【${planVO.planPrice}元】</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" style="margin-bottom: 0px;">
								<input type="submit" value="配套修改"> 
								<input type="hidden" name="planId" value="${planVO.planId}"> 
								<input type="hidden" name="action" value="getOnePlan_For_Update">
							</FORM>
						</td>

						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do" style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden" name="planId" value="${planVO.planId}"> 
								<input type="hidden" name="action" value="delete_plan">
							</FORM>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
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