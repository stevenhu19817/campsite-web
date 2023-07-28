<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CampsiteTentStatus: Home</title>

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
   <tr><td><h3>CampsiteTentStatus: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CampsiteTentStatus: Home</p>

<h3>營地帳篷狀態資料查詢:</h3>
	
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
  <li><a href='<%=request.getContextPath()%>/campsitetentstatus/listAllCampsiteTentStatus.jsp'>List</a> all CampsiteTentStatus    <h4>(byDAO).         </h4></li>
  
  <li>
    <FORM METHOD="post" ACTION="campsitetentstatus.do" >
        <b>輸入營地編號 (如5001):</b>
        <input type="text" name="campId">
        <input type="hidden" name="action" value="getAllOfOne">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller).</h4> 
    </FORM>
  </li>

<%--   <jsp:useBean id="dao" scope="page" class="com.campsitetentstatus.model.CampsiteTentStatusDAO" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="campsitetentstatus.do" > -->
<!--        <b>選擇營地編號:</b> -->
<!--        <select size="1" name="campId"> -->
<%--          <c:forEach var="campsiteVO" items="${dao.all}" >  --%>
<%--           <option value="${campsiteVO.campId}">${campsiteVO.campId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getAllOfOne"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
</ul>

<h3>營地帳篷狀態管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/campsitetentstatus/addCampsiteTentStatus.jsp'>Add</a> a new CampsiteTentStatus.</li>
</ul>

</body>
</html>