<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.campsitetentstatus.model.*"%>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
	
	CampsiteTentStatusService cSvc = new CampsiteTentStatusService();
	List<CampsiteTentStatusVO> csList = cSvc.getByCampId(Integer.parseInt(request.getParameter("campId")));
	pageContext.setAttribute("csList", csList);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>顯示營地帳篷狀態資料</title>
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
  <h1 class="header__title">顯示資料</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="<%=request.getContextPath()%>/camprelease/images/gocamping.jpg" width="500" height="125" border="0"><br>back Home</a></h4>
</table>
</header>

	<table id="example" class="display nowrap" style="width: 100%">
		<thead>
			<tr>
				<th>營地編號</th>
			    <th>營業時間</th>
			    <th>尚餘人數上限</th>
				<th>修改</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="campsiteTentStatusVO" items="${csList}">
			<tr>
				<td>【${campsiteTentStatusVO.campId}】</td>
				<td>【<fmt:formatDate value="${campsiteTentStatusVO.campOpeningTime}"
					pattern="yyyy-MM-dd"/>】</td>
				<td>【${campsiteTentStatusVO.emptyCampLeft}】</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="campId" value="${campsiteTentStatusVO.campId}"> 
						<input type="hidden" name="campOpeningTime"
							value="${campsiteTentStatusVO.campOpeningTime}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>營地編號</th> -->
<!-- 			<th>營業時間</th> -->
<!-- 			<th>尚餘人數上限</th> -->
<!-- 			<th>修改</th> -->
<!-- 			<th>刪除</th> -->
<!-- 		</tr> -->
<%-- 		<c:forEach var="campsiteTentStatusVO" items="${list}"> --%>
<!-- 			<tr> -->
<%-- 				<td>${campsiteTentStatusVO.campId}</td> --%>
<%-- 				<td><fmt:formatDate --%>
<%-- 						value="${campsiteTentStatusVO.campOpeningTime}" --%>
<%-- 						pattern="yyyy-MM-dd" /></td> --%>
<%-- 				<td>${campsiteTentStatusVO.emptyCampLeft}</td> --%>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="campId" value="${campsiteTentStatusVO.campId}"><input --%>
<!-- 							type="hidden" name="campOpeningTime" -->
<%-- 							value="${campsiteTentStatusVO.campOpeningTime}"><input --%>
<!-- 							type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="campId" value="${campsiteTentStatusVO.campId}"><input --%>
<!-- 							type="hidden" name="campOpeningTime" -->
<%-- 							value="${campsiteTentStatusVO.campOpeningTime}"><input --%>
<!-- 							type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
<!-- 	</table> -->
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