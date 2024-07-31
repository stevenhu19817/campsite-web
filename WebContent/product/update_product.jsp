<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adminList.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="java.util.*"%>



<html> 
<head>
<title>商品修改</title>

<style>
  
  h4 {
    color: blue;
    display: inline;
  }
  input.input_1{
	height: 45px;
    /* width: 100%; */
    outline: none;
    border-radius:5px;
    border: 1px
 	solid #ccc;
	padding-left: 15px;
    font-size: 10px;
    border-bottom-width: 2px;
    transition: all 0.3s ease;
}
  
</style>

<style>
  table {
	width: 450px;
	background-color: bisque;
	margin-top: 1px;
	margin-bottom: 1px;
	
  }
  table, th, td {
    border: 0px solid #CCCCFF;
	display: flex;
	flex-direction: column;
/* 	justify-content: center; */
  }
  th, td {
    padding: 1px;
  }
  input.toupdate{
	/* color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd;
    border: 1px solid ;
    padding: .375rem .75rem;
    font-size: 1rem;
    border-radius: .25rem;
    transition: color .15s; */
    font-size: 1rem;
	margin-left: 180px;
  }
  .toupdate:hover{
	color:rgb(46, 32, 243);
  	background-color:#fff;
  	border:2px rgb(2, 46, 238) solid;
  }
  img.preview{
        width: 200px;
      }
      ul{
        list-style: none;
        margin: 0;
        padding: 0;
      }
      ul > li{
        display: inline-block;
        vertical-align: top;
      }
  
</style>

</head>
<body bgcolor='bisque'>


<table id="table-2">
	<tr><td>
		 <h3>商品資料修改 </h3>
		 <h4><a href='selectAll.jsp'>返回</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
			<br>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/product/product.do" enctype= "multipart/form-data">
<table id="table-3">
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td>${updateproduct.productno}</td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pname"  class="input_1" size="45" value="${updateproduct.pname}" /></td>
	</tr>
	
	<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="price"  class="input_1" size="45" value="${updateproduct.price}" /></td>
	</tr>
	
	<tr>
		<td>數量:</td>
		<td><input type="TEXT" name="inventory"  class="input_1" size="45" value="${updateproduct.inventory}" /></td>
	</tr>
	
	<tr>
		<td>上架人員:</td>
		<td><input type="TEXT" name="admin_id"  class="input_1" size="45" value="${updateproduct.admin_id}" /></td>
	</tr>
	
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="situation"  class="input_1" size="45" value="${updateproduct.situation}" /></td>		
	</tr>
	
	<tr>
		<td>商品介紹:</td>
		<td><input type="TEXT" name="descript"  class="input_1" size="45" value="${updateproduct.descript}" /></td>
	</tr>
	
	<tr>
		<td>商品圖片1:</td>
		<td><input type="file" name="img1" id="the_file1"
			 value="${updateproduct.picture1}" />
			 <ul class="picture_list1"></ul></td>
	</tr>
	
	<tr>
		<td>商品圖片2:</td>
		<td><input type="file" name="img2" id="the_file2"
			 value="${updateproduct.picture2}" />
			 <ul class="picture_list2"></ul></td>
	</tr>
	
	<tr>
		<td>商品圖片3:</td>
		<td><input type="file" name="img3" id="the_file3"
			 value="${updateproduct.picture3}" />
			 <ul class="picture_list3"></ul></td>
	</tr>

	<jsp:useBean id="productSvc" scope="page" class="com.Product.model.ProductService" />
	
	<tr>
		<td>類別:<font color=red><b>*</b></font></td>
		<td><select size="1" name="psort">
			  <option value="帳篷">帳篷</option>
              <option value="露營燈">露營燈</option>
              <option value="露營杯">露營杯</option>
              <option value="露營扇">露營扇</option>
              <option value="露營椅">露營椅</option>
              <option value="露營桌">露營桌</option>
              <option value="露營餐具">露營餐具</option>
              <option value="露營碗">露營碗</option>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="productno" value="${updateproduct.productno}">
<input type="hidden" name="action" value="update">
<input type="submit" class="toupdate" value="送出修改">
</FORM>
<script src="productstyle/jquery.js"></script>
<script src="productstyle/update.js"></script>

</body>

</html>