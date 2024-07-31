<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.facilities.model.*"%>
<%@ page import="java.util.List.*"%>

<%
FacilitiesVO facilitiesVO = (FacilitiesVO) request.getAttribute("facilitiesVO");
%>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>  
<title>設備資料更新</title>
<!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/camprelease/css/icon.css">
<style>
   body{
       background-color: #FFEEE1;
       background-position: center;
       background-size: cover;
       font-family: sans-serif;
       margin-top: 40px;
       font-family: arial;
       margin: 30px;
      }
      
#main {
	background-color: #E4F5E9;
	margin-top: 150px;
	border-radius: 30px;
	width: 600px;
	height: 70%;
	box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);
}
h3 {
	margin-top: 15px;
	text-align: center;
	margin-bottom: 10px;
}

h4 {
	text-align: center;
	color: #9c9494;
	margin-bottom: 40px;
}   

#submit {
	margin-top: 25px;
	border-radius: 20px;
	margin-bottom: 20px;
	text-align: center;
}
a{
font-size:5px;
}
</style>
</head>
<body>
<div id="main" class="container">
<h3>設施修改</h3>
				<h4>	<a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="<%=request.getContextPath()%>/camprelease/images/title_camp.png" 
					width="100" height="32" border="0"><br>Home</a>
				</h4>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do" name="form1" enctype="multipart/form-data">
<!--           <div class="col-md-8"> -->
<%--             <label for="inputcampId" class="form-label">營地編號<font color=red><b>*</b></font>${facilitiesVO.campId}</label> --%>
<!--           </div> -->

<!-- 設備 -->
          <div class="col-md-6">
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="bbq" id="bbq" value="1" ${(facilitiesVO.bbq == 0) ? '1' : 'checked' }>
            <span class="material-icons md-18">outdoor_grill</span></label>
            
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="wifi" id="wifi" value="1" ${(facilitiesVO.wifi == 0) ? '1' : 'checked' }>
            <span class="material-icons md-18">wifi</span></label>
            
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="nosmoke" id="nosmoke" value="1" ${(facilitiesVO.nosmoke == 0) ? '1' : 'checked' }>
            <span class="material-icons md-18">smoke_free</span></label> 
            
            <label class="setting-label circle-line" for="setting[]"><input type="checkbox" name="pets" id="pets" value="1"${(facilitiesVO.pets == 0) ? '1' : 'checked' } >
            <span class="material-icons md-18">pets</span></label>
  </div>          
  <div class="button-row d-flex mt-4">
					<div>
						<input type="hidden" name="action" value="facilities_update"> 
			            <input type="hidden" name="facilitiesId" value="<%=facilitiesVO.getFacilitiesId()%>">
			            <input type="hidden" name="campId" value="<%=facilitiesVO.getCampId()%>">
						<button class="btn btn-success ml-auto" type="submit" id="submit">Send</button>
					</div>
				</div>
  </FORM>
  </div>
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> 
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script> 
</body>
</html>