<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campsite.model.*"%>

<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO"); //CampsiteServlet.java(Concroller), 存入req的campsiteVO物件
%>

<html>
<head>
<title>員工資料 - listOneCampsite.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	table-layout: fixed;
	word-wrap: break-word;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

.pic {
	width: 128px;
	height: 90.5px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - ListOneCampsite.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th width="30px">營地編號</th>
			<th width="30px">會員編號</th>
			<th width="80px">營地名稱</th>
			<th width="120px">地址</th>
			<th width="110px">經度</th>
			<th width="110px">緯度</th>
			<th width="120px">營地說明</th>
			<th width="30px">營地價格</th>
			<th width="30px">人數上限</th>
			<th width="80px">上傳時間</th>
			<th width="60px">營業狀態</th>
			<th width="30px">喜歡人數</th>
			<th width="30px">檢舉人數</th>
			<th width="130px">營業執照</th>
			<th width="130px">照片1</th>
			<th width="40px">修改</th>
			<th width="40px">刪除</th>
		</tr>
		<tr>
			<td>${campsiteVO.campId}</td>
			<td>${campsiteVO.memberId}</td>
			<td>${campsiteVO.campName}</td>
			<td>${campsiteVO.location}</td>
			<td>${campsiteVO.latitude}</td>
			<td>${campsiteVO.longtitude}</td>
			<td>${campsiteVO.campDescription}</td>
			<td>${campsiteVO.campPrice}</td>
			<td>${campsiteVO.campLimit}</td>
			<td><fmt:formatDate value="${campsiteVO.listedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${campsiteVO.siteState == 1 ? "營業中":"休息中"}</td>
			<td>${campsiteVO.lovedCount}</td>
			<td>${campsiteVO.reportedCount}</td>
			<td><img
				src="<%=request.getContextPath()%>/CampsiteGifReader?column=camp_license&camp_id=${campsiteVO.campId}"
				class="pic"></td>
			<td><img
				src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campsiteVO.campId}"
				class="pic"></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/campsite/campsite.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="campId" value="${campsiteVO.campId}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/campsite/campsite.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="campId" value="${campsiteVO.campId}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>