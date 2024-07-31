<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.camprelease.model.*"%>
<%@ page import="java.util.List.*"%>

    
<%
CampReleaseVO campreleaseVO = (CampReleaseVO) request.getAttribute("campreleaseVO"); //CampReleaseServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的campreleaseVO, 也包括輸入資料錯誤時的campreleaseVO物件)
%>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>  
<title>營地刊登資料更新</title>
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
  <h1 class="header__title">Go camping營地更新</h1><br>
  <table id="table-1">
		 <h4><a href="<%=request.getContextPath()%>/camprelease/Select_Page.jsp"><img src="images/gocamping.jpg" width="500" height="125" border="0"><br>back Home</a></h4>
</table>
</header>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

    <div class="container mt-5" id="main">
        <div class="row g-3">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do" name="form1" enctype="multipart/form-data">
<!--           <div class="col-md-8"> -->
<%--             <label for="inputcampId" class="form-label">營地編號<font color=red><b>*</b></font><%=campreleaseVO.getCampId()%></label> --%>
<!--           </div> -->
<!--           <div class="col-md-6"> -->
<%--             <label for="inputmemberId" class="form-label">會員編號<font color=red><b>*</b></font><%=campreleaseVO.getMemberId()%></label> --%>
<!--           </div> -->
          <div class="col-md-6">
            <label for="inputcampName" class="form-label">營地名稱</label>
            <input type="text" class="form-control" name="campName" id="campName" value="${campreleaseVO.campName == null ? '' : campreleaseVO.campName}" required>
          </div>
          <div class="col-md-6">
            <label for="inputcampPrice" class="form-label">營地價格(一晚)</label>
            <input type="text" class="form-control" name="campPrice" id="campPrice" value="${campreleaseVO.campPrice == null ? '' : campreleaseVO.campPrice}">
          </div>
          <div class="col-md-6">
            <label for="inputcampLimit" class="form-label">營地人數上限</label>
            <input type="text" class="form-control" name="campLimit" id="campLimit" value="${campreleaseVO.campLimit == null ? '' : campreleaseVO.campLimit}">
          </div>
          <div class="col-md-12">
            <label for="inputcampDescription" class="form-label">營地介紹</label>
            <input type="text" class="form-control" name="campDescription" value="${campreleaseVO.campDescription == null ? '' : campreleaseVO.campDescription}" >
          </div>
<!--           <div class="col-md-6"> -->
<!--             <label for="inputlistedTime" class="form-label">營業開始時間</label> -->
<%--             <input type="text" class="form-control" name="openTime" id="openTime" value="${campreleaseVO.openTime == null ? '' : campreleaseVO.openTime}"> --%>
<!--             <label for="inputlistedTime" class="form-label">營業結束時間</label> -->
<%--             <input type="text" class="form-control" name="closeTime" id="closeTime" value="${campreleaseVO.closeTime == null ? '' : campreleaseVO.closeTime}"> --%>
<!--           </div> -->
          <div class="col-md-6">
            <label for="inputlistedTime" class="form-label">上架日期</label>
            <input type="text" class="form-control" name="listedTime" id="listedTime" value="${campreleaseVO.listedTime == null ? '' : campreleaseVO.listedTime}">
          </div>
  <!-- 地點 -->
          <div class="form-row mt-4">
            <h3>地點更新</h3><br>
            <div class="col">
              <div id="webbulutumap" style="height: 280px;"></div>
                <input type="text" name="location" value="${campreleaseVO.location == null ? '' : campreleaseVO.location}" size="40" class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required" id="address" aria-required="true" aria-invalid="false" placeholder="Street Address"/><br><br>
                 <input type="text" name="latitude" value="${campreleaseVO.latitude}" placeholder="latitude" id="latitude"/><br><br>
                 <input type="text" name="longtitude" value="${campreleaseVO.longtitude == null ? '' : campreleaseVO.longtitude}" placeholder="longitude" id="longitude"/><br><br>
                 <a href="#" id="find-address" title="Find Address" class="button">Find Address</a>
            </div>
          </div>
          <div class="form-row mt-4">
            <h3>圖片更新</h3><br>
					<h5>
						<label>Pic1: <input type="file" accept="image/*"
							name="picture1"  value="<%= (campreleaseVO==null)? "" :campreleaseVO.getPicture1()%>"></label>
					</h5>
					<h5>
						<label>Pic2: <input type="file" accept="image/*"
							name="picture2" value="<%= (campreleaseVO==null)? "" :campreleaseVO.getPicture2()%>"></label>
					</h5>
					<h5>
						<label>Pic3: <input type="file" accept="image/*"
							name="picture3" value="<%= (campreleaseVO==null)? "" :campreleaseVO.getPicture3()%>"></label>
					</h5>
					<h5>
						<label>Pic4: <input type="file" accept="image/*"
							name="picture4" value="<%= (campreleaseVO==null)? "" :campreleaseVO.getPicture4()%>"></label>
					</h5>
					<h5>
						<label>Pic5: <input type="file" accept="image/*"
							name="picture5" value="<%= (campreleaseVO==null)? "" :campreleaseVO.getPicture5()%>"></label>
					</h5>
				</div>
				<div class="button-row d-flex mt-4">
					<div>
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="campId" value="<%=campreleaseVO.getCampId()%>"> 
						<input type="hidden" name="memberId" value="<%=campreleaseVO.getMemberId()%>">
						<button class="btn btn-success ml-auto" type="submit" id="submit">Send</button>
					</div>
				</div>

			</FORM>
        </div>
    </div>
    
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> 
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script> 

    
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsqt74NPCV93dg4iOpJtLL0RDvMSfsnYM"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
    var map;
    var marker;
    var myLatlng = new google.maps.LatLng('25.0511536', '121.5675248');
    var geocoder = new google.maps.Geocoder();
    var infowindow = new google.maps.InfoWindow();
    function initialize() {
        var mapOptions = {
        zoom: 10,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("webbulutumap"), mapOptions);
        marker = new google.maps.Marker({
            map: map,
            position: myLatlng,
            draggable: true
        });
        google.maps.event.addListener(marker, 'dragend', function() {
            geocoder.geocode({'latLng': marker.getPosition()}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        var address_components = results[0].address_components;
                        var components={};
                        jQuery.each(address_components, function(k,v1) {jQuery.each(v1.types, function(k2, v2){components[v2]=v1.long_name});});
                        $('#latitude').val(marker.getPosition().lat());
                        $('#longitude').val(marker.getPosition().lng());
                        infowindow.setContent(results[0].formatted_address);
                        infowindow.open(map, marker);
                    }
                }
            });
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);
</script>
<script>
  $("#find-address").click(function(){
    var apiKey = 'AIzaSyBsqt74NPCV93dg4iOpJtLL0RDvMSfsnYM';
    var  address =  $('#address').val();
    var addressClean = address.replace(/\s+/g, '+');
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
      address: addressClean 
    }, function(results, status) {
      console.log(status);
      if (status == 'OK') {
        longitude = results[0].geometry.location.lng();
        latitude = results[0].geometry.location.lat();
        document.getElementById("longitude").value = longitude;
        document.getElementById("latitude").value = latitude;
        // geocoder is asynchronous, do this in the callback function
        longitude = $("input#longitude").val();
        latitude = $("input#latitude").val();
        if (longitude && latitude) {
          longitude = parseFloat(longitude);
          latitude = parseFloat(latitude);
          initMap(longitude, latitude);
        }
      } else alert("geocode failed")
    });
    function initMap(longitude, latitude) {
    var myLatlng = new google.maps.LatLng(latitude, longitude);
    var mapOptions = {
      zoom: 12,
      center: myLatlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("webbulutumap"), mapOptions);
    var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      draggable: true,
      title: "Where's your garden?"
    });
    google.maps.event.addListener(marker, 'dragend', function() {
            geocoder.geocode({'latLng': marker.getPosition()}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[0]) {
                        var address_components = results[0].address_components;
                        var components={};
                        jQuery.each(address_components, function(k,v1) {jQuery.each(v1.types, function(k2, v2){components[v2]=v1.long_name});});

                        $('#latitude').val(marker.getPosition().lat());
                        $('#longitude').val(marker.getPosition().lng());
                        infowindow.setContent(results[0].formatted_address);
                        infowindow.open(map, marker);
                    }
                }
            });
        });
  };
}) 
</script>
  </body>
  <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
// listedTime
        $.datetimepicker.setLocale('zh');
        $('#listedTime').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=campreleaseVO.getListedTime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           startDate:	            '2021/11/01',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
// // openTime
//         $.datetimepicker.setLocale('zh'); // kr ko ja en
//         $('#openTime').datetimepicker({
//            theme: '',          //theme: 'dark',
//            timepicker: true,   //timepicker: false,
//            step: 30,            //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format: 'H:i:s',
<%-- 	       value: '<%=campreleaseVO.getOpenTime()%>' , --%>
//            //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	        '2021/11/01',  // 起始日
//            //minDate:           '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
//         // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//         //      1.以下為某一天之前的日期無法選擇
//              var somedate1 = new Date('2021-11-01');
//              $('#openTime').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate1.getYear() || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
//         //      2.以下為某一天之後的日期無法選擇
//              var somedate2 = new Date('2021-11-30');
//              $('#openTime').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});
        
// // closeTime
//                 $.datetimepicker.setLocale('zh'); // kr ko ja en
//         $('#closeTime').datetimepicker({
//            theme: '',          //theme: 'dark',
//            timepicker: true,   //timepicker: false,
//            step: 30,            //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format: 'H:i:s',
<%-- 	       value: '<%=campreleaseVO.getCloseTime()%>' , --%>
//            //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	        '2021/11/01',  // 起始日
//            //minDate:           '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
//         // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//         //      1.以下為某一天之前的日期無法選擇
//              var somedate1 = new Date('2021-11-01');
//              $('#closeTime').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate1.getYear() || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
//         //      2.以下為某一天之後的日期無法選擇
//              var somedate2 = new Date('2021-11-30');
//              $('#closeTime').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});
        
</script>
</html>