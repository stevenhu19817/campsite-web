<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List.*"%>
<%@ page import="com.campsitetentstatus.model.*"%>

<%
   CampsiteTentStatusVO campsiteTentStatusVO = (CampsiteTentStatusVO) request.getAttribute("campsiteTentStatusVO");

// pageContext.setAttribute("campId", request.getAttribute("campId"));
%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>  
<title>帳篷狀態更新管理</title>
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
#gocamping {
margin-top: 15px;
margin-bottom: 10px;
text-align: center;
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
</style>

</head>
<body>

<header class="header" id="gocamping">
  <h1 class="header__title">Go camping帳篷狀態更新</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="<%=request.getContextPath()%>/camprelease/images/gocamping.jpg" width="500" height="125" border="0"><br>back Home</a></h4>
</table>
</header>
	
	<div class="container mt-5" id="main">
        <div class="row g-3">
        <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do" name="form1" >
<!--           <div class="col-md-8"> -->
<%--             <label for="inputcampId" class="form-label">營地編號<font color=red><b>*</b></font><%=campsiteTentStatusVO.getCampId()%></label> --%>
<!--           </div> -->
          <div class="col-md-6">
            <label for="inputCampOpeningTime" class="form-label">營業時間</label>
            <input type="text" class="form-control" name="campOpeningTime" id="campOpeningTime" value="${campsiteTentStatusVO.campOpeningTime == null ? '' : campsiteTentStatusVO.campOpeningTime}">
          </div>
<!--           <div class="col-md-8"> -->
<%--             <label for="inputcampId" class="form-label">營業日期<font color=red><b>*</b></font><%=campsiteTentStatusVO.getCampOpeningTime()%></label> --%>
<!--           </div> -->

          <div class="col-md-6">
            <label for="inputemptyCampLeft" class="form-label">尚餘人數上限</label>
            <input type="text" class="form-control" name="emptyCampLeft" placeholder="請輸入人數..." id="emptyCampLeft" value="${campsiteTentStatusVO.emptyCampLeft == null ? '' : campsiteTentStatusVO.emptyCampLeft}">
          </div>
				<div class="button-row d-flex mt-4">
					<div>
						<input type="hidden" name="action" value="update">
<%-- 						<input type="hidden" name="campOpeningTime" value="${campsiteTentStatusVO.campOpeningTime}"> --%>
						<input type="hidden" name="campId" value="<%=campsiteTentStatusVO.getCampId()%>">
						<button class="btn btn-success ml-auto" type="submit" id="submit">Send</button>
					</div>
				</div>
			</FORM>
        </div>
    </div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> 
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script> 
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	// 	java.sql.Date campOpeningTime = null;
	// 	java.util.Date utilCampOpeningTime = null;
	// 	try {
	// 		utilCampOpeningTime = new java.util.Date(campsitetentstatusVO.getCampOpeningTime().getTime());
	// 		listedTime = new java.sql.Date(utilCampOpeningTime.getTime());
	// 	} catch (Exception e) {
	// 		campOpeningTime = new java.sql.Date(System.currentTimeMillis());
	// 	}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#campOpeningTime').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '<%=campsiteTentStatusVO.getCampOpeningTime()%>',
// 	disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	startDate:	            '2021/11/01',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
// 	     var somedate1 = new Date('2021-10-30');
// 	     $('#campOpeningTime').datetimepicker({
// 	         beforeShowDay: function(date) {
// 	       	  if (  date.getYear() <  somedate1.getYear() || 
// 			           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
// 			           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
// 	             ) {
// 	                  return [false, ""]
// 	             }
// 	             return [true, ""];
// 	     }});

	//      2.以下為某一天之後的日期無法選擇
// 	     var somedate2 = new Date('2021-12-01');
// 	     $('#campOpeningTime').datetimepicker({
// 	         beforeShowDay: function(date) {
// 	       	  if (  date.getYear() >  somedate2.getYear() || 
// 			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
// 			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
// 	             ) {
// 	                  return [false, ""]
// 	             }
// 	             return [true, ""];
// 	     }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>