<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Campsite: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Campsite: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Campsite: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsite/listAllCampsite_byDAO.jsp'>List</a> all Campsite    <h4>(byDAO).         </h4></li>
  
  <li>
    <FORM METHOD="post" ACTION="campsite.do" >
        <b>輸入營地編號 (如5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller).</h4> 
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="campsite.do" name="form1">
        <b>輸入營地編號 (如5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="送出" onclick="fun1()">  <h4>(資料格式驗證  by Java Script).</h4> 
    </FORM>
  </li>

  <jsp:useBean id="dao" scope="page" class="com.campsite.model.CampsiteDAO" />
   
  <li>
     <FORM METHOD="post" ACTION="campsite.do" >
       <b>選擇營地編號:</b>
       <select size="1" name="campId">
         <c:forEach var="campsiteVO" items="${dao.all}" > 
          <option value="${campsiteVO.campId}">${campsiteVO.campId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>

<h3>營地管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsite/addCampsite.jsp'>Add</a> a new Campsite.</li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (campId.value=="") 
             alert("請輸入營地編號!");
         else if (isNaN(campId.value)) 
             alert("營地編號格式不正確!");
         else if ((campId.value < 5001) || (campId.value > 5010)) 
             alert("請填寫介於5001和5010之間的數量!");
         else
             submit();
      }
   }
</script>

</body>
</html>