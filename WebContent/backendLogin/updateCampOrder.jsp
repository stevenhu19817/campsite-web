<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adminList.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.camporder.model.*"%>
<%@ page import="java.util.*"%>
<%
	CampOrderVO campOrderVO = (CampOrderVO) request.getAttribute("campOrderVO");
%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<%
	CampsiteService campsiteService = new CampsiteService();
	pageContext.setAttribute("campsiteSvc", campsiteService);
%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>營地訂單修改</title>
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
		ACTION="<%=request.getContextPath()%>/backendLogin/CampOrderBackendServlet.do">
		<table>
			<tr>
				<td>營地訂單編號:<font color=red><b>*</b></font></td>
				<td><%=campOrderVO.getCampOrderId()%></td>
				<input type="hidden" name="campOrderId" value="<%=campOrderVO.getCampOrderId()%>">
			</tr>
			<tr>
				<td>營地名稱:<font color=red><b>*</b></font></td>
				<td><%=campsiteService.getOneCampsite(campOrderVO.getCampId()).getCampName()%></td>
				<input type="hidden" name="campId" value="<%=campOrderVO.getCampId()%>">
			</tr>
			<tr>
				<td>訂購者:<font color=red><b>*</b></font></td>
				<td><%=memSvc.findByPrimaryKey(campOrderVO.getMemberId()).getName()%></td>
				<input type="hidden" name="memberId" value="<%=campOrderVO.getMemberId()%>">
			</tr>
			<tr>
				<td>預定人數:</td>
				<td><input type="TEXT" name="guestNumber" size="1"
					value="<%=campOrderVO.getGuestNumber()%>" /></td>
			</tr>
			<tr>
				<td>入住日期:</td>
				<td><input type="TEXT" name="checkInDate" size="8"
					value="<%=campOrderVO.getCheckInDate()%>" /></td>
			</tr>
			<tr>
				<td>退房日期:</td>
				<td><input type="TEXT" name="checkOutDate" size="8"
					value="<%=campOrderVO.getCheckOutDate()%>" /></td>
			</tr>
			<tr>
				<td>營地下訂日期:<font color=red><b>*</b></font></td>
				<td><%=campOrderVO.getOrderDate()%></td>
				<input type="hidden" name="orderDate" value="<%=campOrderVO.getOrderDate()%>">
			</tr>
			<tr>
				<td>營地付款截止日期:<font color=red><b>*</b></font></td>
				<td><%=campOrderVO.getPaymentDeadline()%></td>
				<input type="hidden" name="paymentDeadline" value="<%=campOrderVO.getPaymentDeadline()%>">
			</tr>
			<tr>
				<td>訂單總金額:</td>
				<td><input type="TEXT" name="orderTotal" size="1"
					value="<%=campOrderVO.getOrderTotal()%>" /></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><select size="1" name="orderStatus">
						<option value="0" ${campOrderVO.getOrderStatus()==0?'selected':''}>未付款</option>
						<option value="1" ${campOrderVO.getOrderStatus()==1?'selected':''}>已付款</option>
						<option value="2" ${campOrderVO.getOrderStatus()==2?'selected':''}>取消訂單</option>
				</select></td>
			</tr>
		

		</table>
		<br> <input type="hidden" name="campOrderId" value="<%=campOrderVO.getCampOrderId()%>">
			<input type="hidden" name="action" value="Update">
			<input type="hidden" name="xxx" value="123">
			<input type="submit" value="送出新修改">
	</FORM>
</body>
<script src="../js/jquery.js"></script>
<script>
</script>
</body>
</html>