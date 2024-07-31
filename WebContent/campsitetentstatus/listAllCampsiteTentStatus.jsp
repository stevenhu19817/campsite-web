<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.campsitetentstatus.model.*"%>

<%
	CampsiteTentStatusDAO dao = new CampsiteTentStatusDAO();
	List<CampsiteTentStatusVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>營地帳篷狀態資料</title>

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
				<h3>營地帳篷狀態資料表</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>營地編號</th>
			<th>營業時間</th>
			<th>尚餘人數上限</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="campsiteTentStatusVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${campsiteTentStatusVO.campId}</td>
				<td><fmt:formatDate
						value="${campsiteTentStatusVO.campOpeningTime}"
						pattern="yyyy-MM-dd" /></td>
				<td>${campsiteTentStatusVO.emptyCampLeft}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="campId" value="${campsiteTentStatusVO.campId}"><input
							type="hidden" name="campOpeningTime"
							value="${campsiteTentStatusVO.campOpeningTime}"><input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="campId" value="${campsiteTentStatusVO.campId}"><input
							type="hidden" name="campOpeningTime"
							value="${campsiteTentStatusVO.campOpeningTime}"><input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>
</body>
</html>