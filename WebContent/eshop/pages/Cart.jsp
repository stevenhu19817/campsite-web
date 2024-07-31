<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.eshop.model.*"%>
<html>
<head>
<title>購物車</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/eshop/css_eshop/ShoppingCart.css">
</head>
<body>
	<br>
	<%
		@SuppressWarnings("unchecked")
		Vector<Merchandise> buylist = (Vector<Merchandise>) session.getAttribute("shoppingcart");
	%>

	<font size="+3">您的購物車內容如下：</font>

	<table id="table-1" style="margin: auto;">
		<tr>
			<th width="300">商品照片</th>
			<th width="500">商品名稱</th>
			<th width="100">價格</th>
			<th width="120">數量</th>
			<th width="120"></th>
		</tr>


		<% if (buylist != null && (buylist.size() > 0)) { %>
	
		<%
			for (int index = 0; index < buylist.size(); index++) {
				Merchandise order = buylist.get(index);	
		%>
		<tr>
			<td width="300"><img src="<%=order.getPic()%>"></td>
			<td width=100%><%=order.getName()%></td>
			<td width=100%><%=order.getPrice()%></td>
			<td width=100%><%=order.getQuantity()%></td>

			<td width="120">
				<form name="deleteForm"
					action="<%=request.getContextPath()%>/eshop/Shopping.html"
					method="POST">
					<input type="hidden" name="action" value="DELETE"> <input
						type="hidden" name="del" value="<%=index%>"> <input
						type="submit" value="刪 除" class="button1">
				</form>
			</td>
		</tr>
		<%}%>
	<%}%>
	</table>
	
	<p>
	<% if (buylist != null && (buylist.size() > 0)) { 	%>
	<form name="checkoutForm"
		action="<%=request.getContextPath()%>/eshop/Shopping.html"
		method="POST">
		<input type="hidden" name="action" value="CHECKOUT"> <input
			type="submit" value="付款結帳" class="button2">
	</form>
	<% } %>
	<br>
	<a href="<%=request.getContextPath()%>/eshop/pages/EShop.jsp"><font
		size="+1">繼 續 購 物</font></a>


</body>
</html>