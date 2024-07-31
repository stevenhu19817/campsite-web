<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="java.util.*"%>
<%
	AdminListVO adminListVO = (AdminListVO) request.getAttribute("adminListVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta>
<title>管理員資料修改</title>
</head>
<body>
	<div class="logo">
		<a href="#"> <img src="../images/200x1280.png"
			style="width: 100%; height: 200px;">
		</a>
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/backendLogin/AdminServlet.do"
		name="form1">
		<table>
			<tr>
				<td>管理員編號:<font color=red><b>*</b></font></td>
				<td><%=adminListVO.getAdminId()%></td>
			</tr>
			<tr>
				<td>管理員密碼:</td>
				<td><input type="PASSWORD" name="adminPwd" size="45" id=pwd1 /></td>
			</tr>
			<tr>
				<td>重新輸入一樣的密碼:</td>
				<td><input type="PASSWORD" name="adminPwd" size="45" id=pwd2 /></td>
			</tr>
			<tr>
				<td>管理員姓名:</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%=adminListVO.getAdminName()%>" /></td>
						
			</tr>
		</table>
		<br> <input type="hidden" name="adminId"
			value="<%=adminListVO.getAdminId()%>"> <input type="hidden"
			name="action" value="update"> <input type="submit"
			value="送出新修改">
	</FORM>
</body>
<script src="../js/jquery.js"></script>
<script>
$(function(){
    $("#pwd2").blur(function(){
        if($("#pwd1").val()!=$("#pwd2").val()){

            alert("兩次輸入的密碼不一致");

        }
    })
     

})
</script>
</body>
</html>