<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="java.util.*"%>
<%
	CampsiteVO campsiteVO = (CampsiteVO) request.getAttribute("campsiteVO");
%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>營地資訊修改</title>
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
		ACTION="<%=request.getContextPath()%>/backendLogin/camplistone.do"
		enctype="multipart/form-data"
		name="form1">
		<input type="hidden" name="latitude" value="<%=campsiteVO.getLatitude() %>">
		<input type="hidden" name="longtitude" value="<%=campsiteVO.getLongtitude()%>">
		<input type="hidden" name="campDescription" value="<%=campsiteVO.getCampDescription()%>">
		<input type="hidden" name="listedTime" value="<%=campsiteVO.getListedTime()%>">
		<table>
			<table>
			<tr>
				<td>營地編號:<font color=red><b>*</b></font></td>
				<td><%=campsiteVO.getCampId()%></td>
			</tr>
			<tr>
				<td>營地業主:<font color=red><b>*</b></font></td>
				<td><%=memSvc.findByPrimaryKey(campsiteVO.getMemberId()).getName()%></td>
				<input type="hidden" name="memberId" value="<%=campsiteVO.getMemberId()%>">
			</tr>
			<tr>
				<td>營地名稱:</td>
				<td><input type="TEXT" name="campName" size="45"
					value="<%=campsiteVO.getCampName()%>" /></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="location" size="45"
					value="<%=campsiteVO.getLocation()%>" /></td>
			</tr>
			<tr>
				<td>營地價格:</td>
				<td><input type="TEXT" name="campPrice" size="45"
					value="<%=campsiteVO.getCampPrice()%>" /></td>
			</tr>
			<tr>
				<td>人數上限:</td>
				<td><input type="TEXT" name="campLimit" size="45"
					value="<%=campsiteVO.getCampLimit()%>" /></td>
			</tr>
			<tr>
				<td>營業狀態:</td>
				<td><select size="1" name="siteState">
						<option value="0" ${campsiteVO.getSiteState()==0?'selected':''}>已停權</option>
						<option value="1" ${campsiteVO.getSiteState()==1?'selected':''}>營業中</option>
				</select></td>
			</tr>
			<tr>
				<td>喜歡人數:</td>
				<td><input type="TEXT" name="lovedCount" size="45"
					value="<%=campsiteVO.getLovedCount()%>" /></td>
			</tr>
			<tr>
				<td>檢舉人數:</td>
				<td><input type="TEXT" name="reportedCount" size="45"
					value="<%=campsiteVO.getReportedCount()%>" /></td>
			</tr>
			<tr>
				<td>營業執照:</td>
				<td><input type="file" name="campLicense" /></td>
			</tr>
			
		</table>
		</table>
		<br> <input type="hidden" name="campId"
			value="<%=campsiteVO.getCampId()%>"> <input type="hidden"
			name="action" value="update"> <input type="submit"
			value="送出新修改">
	</FORM>
</body>
<script src="../js/jquery.js"></script>
<script>
</script>
</body>
</html>