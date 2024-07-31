<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adminList.model.*"%>

<%
 	ProductService proSvc = new ProductService();
 	List <ProductVO> list = proSvc.getAll();
 	pageContext.setAttribute("list", list);	
%>

<%
 	AdminService adminSvc = new AdminService();
	pageContext.setAttribute("adminSvc", adminSvc);
%>


<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品資料表</title>
    <script src="productstyle/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="productstyle/table.css">
    
    <style>
    	table {
			width: 450px;
			background-color: white;
			margin-top: 1px;
			margin-bottom: 1px;
  		}
   		table, th, td { 
			border: 1px solid blue; 
    
  		}
  		th, td {
    		padding: 1px;
  		}
   
    </style>
</head>

<body>
    <div class="container">
        <nav class="sidebar">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
           <ul>
				<li><a href="#" class="feat-btn">帳號管理 <span
						class="fas fa-caret-down first"></span>
				</a>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/member.jsp">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                        <li><a href="<%=request.getContextPath()%>/product/selectAll.jsp" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/camp.jsp" class="camp_list">營地列表</a></li>
						  <li><a href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp" class="camp_order">營地訂單</a></li>
						<li><a href="<%=request.getContextPath()%>/backendLogin/alert.jsp" class="alert_managament">檢舉管理</a></li>
					</ul></li>
                <li>
                    <a href="#" class="mky-btn">商城管理
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp" class="shopping_list">商城訂單</a></li>
                    </ul>
                </li>
                
                <li>
                <form METHOD="get" ACTION="<%=request.getContextPath()%>/backendLogin/home.do">
                  <button type="submit" class="btn btn-outline-secondary" id="btnlog">logout</button>
                </form>
                </li>
            </ul>
        </nav>
    </div>
    <div class="rightside" id="rig-1">
        <h2>商品資料表</h2><br>
<!--         <h3>商品類型：</h3><br> -->
        
        <%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>     
        <div class="dropdown">
          <div class="btninsert">
<!--          <button type="button" onclick="javascript:location.href='./thanks_page.html'">確定</button>  -->
        <button type="button" class="btny" id="btninsert" onclick="javascript:location.href='PushProduct.jsp'">新增商品</button>      
          </div>        		
            <div class="wrap">
            <div class="search">
            <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/product/product.do" >
            <input class="search-bar" type="text"  name="pname" placeholder="輸入名稱">
<!--             <input type="hidden" name= "action" value="pname"> -->
       		<input type="hidden" name="action" value="search">        	
           <button type="submit" class="search-btn"><i class="fas fa-search"></i></button>
           </FORM>
           </div>
        </div>
        <br>
        <br>
 
        <table class="rwd-table">
            <tr>
                <th>商品編號</th>
				<th>商品名稱</th>
				<th>類別</th>
				<th>價格</th>
				<th>商品數量</th>
				<th>上架人員</th>
				<th>狀態</th>
				<th>商品介紹</th>
				<th>商品圖片1</th>
				<th>商品圖片2</th>
				<th>商品圖片3</th>
				<th>修改</th>
				<th>刪除</th>
            </tr>
            
        <%@ include file="page1.file" %> 
		<c:forEach var="message" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <tr>
               <td>${message.productno}</td>
				<td>${message.pname}</td>
				<td>${message.psort}</td>
				<td>${message.price}</td>
				<td>${message.inventory}</td>
				<td>${adminSvc.getOneAdminList(message.admin_id).adminName}</td>
				<td>${message.situation == 1 ? "已上架" : "已下架"}</td>
				<td>${message.descript}</td>
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=1">
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=2">
				<td><img src="<%=request.getContextPath() %>/PhotoServlet?id=${message.productno}&img=3">
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" class= "btnupdate" value="修改">
					<input type="hidden" name="productno" value="${message.productno}">
			     	<input type="hidden" name="action" value="getforUpdate"></FORM>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" class= "btndanger" value="刪除">
					<input type="hidden" name="productno"  value="${message.productno}">
			     	<input type="hidden" name="action"  value="delete"></FORM>
            </tr>
          </c:forEach> 		
        </table>
       <%@ include file="page2.file" %> 
    </div>
    <div class="bg" id="bgblack"></div>
    <div class="pop">
    <div class="formmember">
        
<%-- <FORM METHOD= post ACTION="<%=request.getContextPath()%>/product/product.do" enctype = "multipart/form-data" class=alert> --%>
<!-- 	<table class="rwd-side"> -->
<!-- 	<tr> -->
<!-- 		<td>商品名稱:</td> -->
<!-- 		<td><input type="TEXT" name="pname" size="40"  -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getPname()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>類別:</td> -->
<!-- 		<td><input type="TEXT" name="psort" size="40" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getPsort()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>價格:</td> -->
<!-- 		<td><input type="TEXT" name="price" size="40"  -->
<%-- 			 value= "<%= (productVO==null)? "" : productVO.getPrice()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>數量:</td> -->
<!-- 		<td><input type="TEXT" name="inventory" size="40" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getInventory()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>上架人員:</td> -->
<!-- 		<td><input type="TEXT" name="admin_id" size="40" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getAdmin_id()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>狀態:</td> -->
<!-- 		<td><input type="TEXT" name="situation" size="40" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getSituation()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>商品介紹:</td> -->
<!-- 		<td><input type="TEXTAREA" name="descript" size="40" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getDescript()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>商品圖片1:</td> -->
<!-- 		<td><input type="file" name="img1" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getPicture1()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>商品圖片2:</td> -->
<!-- 		<td><input type="file" name="img2" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getPicture2()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>商品圖片3:</td> -->
<!-- 		<td><input type="file" name="img3" -->
<%-- 			 value="<%= (productVO==null)? "" : productVO.getPicture3()%>" /></td> --%>
<!-- 	</tr> -->


<!-- 	</table> -->
<!-- <br> -->
<!-- 	<div class="button"> -->
<%-- 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do"> --%>
<!-- 	<input type="hidden" name="action" value="insert"> -->
<!-- 	<input type="submit" value="送出新增"></FORM> -->
	
<!-- 	<button class="button_editok">取消</button> -->
<!-- 	</div> -->

			
                
            
            	            
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<script src="productstyle/table.js"></script>

</body>

</html>