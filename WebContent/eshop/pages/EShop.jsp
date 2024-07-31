<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<% 
	Integer ob = (Integer) session.getAttribute("id");//接來自loginhandler.java的session.setAttribute("id",)
	pageContext.setAttribute("ob", ob);//這邊將用來判斷使用者是否已登入
%>

<jsp:useBean id="productSvc" scope="page" class="com.Product.model.ProductService" />

<html>
<head>

<title>購物商城</title>

<!-- Bootstrap 的 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/eshop/css_eshop/bootstrap.min.css"> -->
<!-- 商城 的 CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/eshop/css_eshop/EShop.css">


</head>
<body>

	<div>

		<nav class="shopnav">
			<a href="<%=request.getContextPath()%>/homepage/index.jsp" class="camppage" style="font-size: 1.25em;">GoCamping</a>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<a id="errshow" href="#">${errorMsgs}</a>
			</c:if>
			<a href="<%=request.getContextPath()%>/emodr/emodr.do?action=showMyOders" class="myorders" style="font-size: 1.25em;">我的訂單</a>			
			<a href="<%=request.getContextPath()%>/eshop/pages/Cart.jsp" class="shoppingcart" style="font-size: 1.25em;">購物車</a>
			<a href="<%=request.getContextPath()%>/register_and_login/login.jsp" class="loginfromeshop" style="font-size: 1.25em;">${(ob == null)? "登入":""}</a>
			<a href="<%=request.getContextPath()%>/account/logout.do" class="logoutfromeshop" style="font-size: 1.25em;">${(ob == null)? "":"登出"}</a>
			
			
		</nav>

		<div class="top_main">

			<div class="category">
				<ul class="list">
					<li class="item1"><a href="#">帳篷</a></li>
					<li class="item2"><a href="#">餐具</a></li>
					<li class="item3"><a href="#">燈具</a></li>
					<li class="item4"><a href="#">桌椅</a></li>
				</ul>
			</div>

			<div class="slideshow">
				<!-- 已用背景圖取代這一區塊的顯示 -->
			</div>

		</div>
	</div>

	<div class="middle_countainer">
		<h1>快速選購商品區</h1>
	</div>

	<!-- 以下是格線系統+card的區塊 -->
	<div class="container">

		<div class="row">
			<c:forEach var="productVO" items="${productSvc.all}">
				
				<c:if test="${ productVO.situation == 1 }">	<!-- 商品有上架才顯示出來 -->

				<div class="col">
					<form name="shoppingForm" action="<%=request.getContextPath()%>/eshop/Shopping.html" method="POST">
						<div class="card" style="width: 18rem;">
							<img src="<%=request.getContextPath()%>/eshop/PictureServlet?id=${productVO.productno}">
							<div class="card-body">
							<h5 class="card-title">${productVO.pname}</h5>
							<p class="card-text">
								<span>NT: ${productVO.price}</span>
							</p>
							<div align="center">
								數量：<input type="text" name="quantity" size="3" value=1>
							</div>
							<br>
							<div align="center">
								<input type="submit" class="btn btn-primary" value="加入購物車">
								<input type="hidden" name="name" value="${productVO.pname}">
								<input type="hidden" name="price" value="${productVO.price}">
									<input type="hidden" name="pic"
										value="<%=request.getContextPath()%>/eshop/PictureServlet?id=${productVO.productno}">
									<input type="hidden" name="action" value="ADD">
							</div>
							</div>
						</div>
					</form>
				</div>
				</c:if>	
			</c:forEach>
		</div>
	</div>
		
	<!-- 載入Bootstrap 的 JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
	<!-- <script src="/bootstrap.bundle.min.js"></script> -->
</body>
</html>