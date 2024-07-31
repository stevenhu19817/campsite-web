<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="java.util.*"%>
<%
  AdminListVO adminListVO = (AdminListVO) request.getAttribute("AdminListVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta >
<title>新增管理員</title>
</head>
<body>
 <div class="logo">
        <a href="#">
            <img src="../images/200x1280.png" style="width: 1280px; height: 200px;">
        </a>
    </div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backendLogin/AdminServlet.do" name="form1">
<table>
	<tr>
		<td>管理員編號:(請填寫四位數字)</td>
		<td><input type="TEXT" name="adminId" size="45" 
			 value="<%= (adminListVO==null)? "0035" : adminListVO.getAdminId()%>"/></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="password" name="adminPwd" size="45"
			 value="<%= (adminListVO==null)? "jacky0229" : adminListVO.getAdminPwd()%>"/></td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="adminName" size="45"
			 value="<%= (adminListVO==null)? "吳狄軒" : adminListVO.getAdminName()%>"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="add">
<input type="submit" value="送出新增"></FORM>
</body>
</body>
</html>