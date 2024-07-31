<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.facilities.model.*"%>

<%
FacilitiesService facilitiesSvc = new FacilitiesService();
	List<FacilitiesVO> list = facilitiesSvc.getAll();
	pageContext.setAttribute("list", list);

	FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>All設施資料</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/camprelease/css/jquery.dataTables.min.css' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/bootstrap.min5.1.0.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">

<style>
div.dataTables_wrapper {
	background-color: whitesmoke;
	width: 800px;
	margin: 0 auto;
}

body {
	background-color: #7B7571;
}

.setting-label {
	position: relative;
	display: inline-block;
	line-height: 1em;
	overflow: hidden;
	margin: 0 5px 5px 0;
	cursor: pointer;
}

.setting-label>input {
	position: absolute;
	top: -20px;
	left: -20px;
}

.setting-label>span {
	position: relative;
	display: block;
	padding: 10px 12px 10px 10px;
	color: #000;
	font-weight: 500;
	background-color: lightgray;
	white-space: nowrap;
	border-radius: 2em;
	-webkit-border-radius: 2em;
	-moz-border-radius: 2em;
}

.setting-label>span>i {
	opacity: 1;
}

.setting-label:hover>span {
	color: #fff;
	background-color: #F4A249;
}

.setting-label:hover>span.male {
	background-color: #F4A249;
}

.setting-label input:checked+span {
	background-color: #f23557;
	color: white;
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
					<th>設施編號</th>
					<th>營地編號</th>
					<th>烤肉區</th>
					<th>WIFI網路</th>
					<th>禁菸</th>
					<th>寵物</th>
					<th>設施修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<c:forEach var="facilitiesVO" items="${list}">
				<tbody>
			<tr>
				<td>【${facilitiesVO.facilitiesId}】</td>
				<td>【${facilitiesVO.campId}】</td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="bbq" id="bbq" value="1"${(facilitiesVO.bbq == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">outdoor_grill</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="wifi" id="wifi" value="1"${(facilitiesVO.wifi == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">wifi</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="nosmoke" id="nosmoke" value="1"${(facilitiesVO.nosmoke == 0) ? '1' : 'checked' } disabled="disabled">
				<span class="material-icons md-18">smoke_free</span></label></td>
				
				<td><label class="setting-label" for="setting[]">
				<input type="checkbox" name="pets" id="pets" value="1"${(facilitiesVO.pets == 0) ? '1' : 'checked' }  disabled="disabled">
				<span class="material-icons md-18">pets</span></label></td>
				
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" style="margin-bottom: 0px;">
						<input type="submit" value="設施修改"> 
						<input type="hidden" name="facilitiesId" value="${facilitiesVO.facilitiesId}"> 
						<input type="hidden" name="campId" value="${facilitiesVO.campId}"> 
						<input type="hidden" name="action" value="getOneFacilities_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden" name="facilitiesId" value="${facilitiesVO.facilitiesId}"> 
						<input type="hidden" name="action" value="delete_facilities">
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
				"searching" : true, //搜尋功能, 預設是開啟
				"paging" : true, //分頁功能, 預設是開啟
				"ordering" : true, //排序功能, 預設是開啟
				"lengthMenu" : [5, 10],
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