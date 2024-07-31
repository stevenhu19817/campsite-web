<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.campAlert.model.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
  table{
 	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 10px;
    text-align: center;
  }
  img{
  width:100px;
  height:100px;	
  }
  
</style>
</head>
<body>
<%
	CampAlertDAO dao = new CampAlertDAO();
	 List<CampAlertVO> list = dao.getALL();
	  pageContext.setAttribute("list",list);
%>
<table>
	<tr>
		<th>檢舉流水號</th>
		<th>檢舉人</th>
		<th>營地編號</th>
		<th>檢舉時間</th>
		<th>檢舉內容</th>
		<th>圖片1</th>
		<th>圖片2</th>
		<th>圖片3</th>
		<th>檢舉狀態</th>
		<th>承辦人編號</th>
		<th>編輯</th>
		<th>刪除</th>
		
	</tr>
	
		<c:forEach var="message" items="${list}">
			<tr>	
		
			<td>${message.alertId}</td>
			<td>${message.memberId}</td>
			<td>${message.campId}</td>
			<td>${message.report_Time}</td>
			<td>${message.content}</td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=1"></td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=2"></td>
			<td><img src="CampAlertServlet?id=${message.alert_Id}&img=3"></td>
			<td>${message.report_Status}</td>
			<td>${message.handeler}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="alertId"  value="${empVO.empno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="alertId"  value="${empVO.empno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
			</tr>
		</c:forEach>	
		
	</table>	


</body>
</html>