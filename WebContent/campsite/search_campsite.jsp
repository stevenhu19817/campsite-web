<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.campsite.model.*"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="java.util.*"%>

<%
	CampsiteDAO dao = new CampsiteDAO();
	List<CampsiteVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<%
	List<CampsiteVO> campsiteList = (List<CampsiteVO>) request.getAttribute("campsiteList"); //CampsiteServlet.java(Concroller), 存入req的List<CampsiteVO>物件
	System.out.println("jsp campsiteList= " + campsiteList);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>搜尋露營地</title>
<%@ include file="/template/navbar.jsp"%>

<!-- 日期選擇器的 CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<!-- 載入 CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/campsite/vendors/bootstrap/css/SearchCamp.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<form method="post" action="campsite.do">
				<div class="col-12 search-bar" style="background-color: #fbefe7">
					<div class="row">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<ul>
								<%-- 								<c:forEach var="message" items="${errorMsgs}"> --%>
								<%-- 									<li style="color: red; font-weight: bold;">${message}</li> --%>
								<%-- 								</c:forEach> --%>
								<script>
									alert(`
										<c:forEach var="message" items="${errorMsgs}">
											${message}\r
										</c:forEach>
									`.replaceAll('\t', '').replaceAll('\n', ''))
								</script>
							</ul>
						</c:if>
						<div class="col-md-4">
							<!-- Actual search box -->
							<div class="form-group has-search">
								<p class="fw-bold">目的地?</p>
								<i class="fas fa-location-arrow form-control-feedback"></i> <input
									type="text" name="CAMP_NAME" class="form-control has-icon"
									placeholder="請輸入營地或地址..." autocomplete="off">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group has-search">
								<p class="fw-bold">選擇日期</p>
								<i class="fas fa-calendar-alt form-control-feedback"></i> <input
									type="text" id="date" class="form-control choose-date has-icon"
									name="CAMP_OPENING_TIME" value="" placeholder="請選擇日期..."
									autocomplete="off" readonly>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group has-search">
								<p class="fw-bold">顧客人數</p>
								<i class="fas fa-user-friends form-control-feedback"></i> <input
									type="text" name="EMPTY_CAMP_LEFT"
									class="form-control has-icon" placeholder="請輸入人數..."
									autocomplete="off">
							</div>
						</div>
						<div class="col-md-2">
							<div class="has-search">
								<p class="fw-bold">價格範圍</p>
								<i class="fas fa-wallet form-control-feedback"></i> <select
									name="CAMP_PRICE" class="form-select has-icon"
									aria-label="Default select example">
									<option style="color: #757575" value="" selected>請選擇價格...</option>
									<option value="300~1000">300~1000</option>
									<option value="1001~2000">1001~2000</option>
									<option value="2001~3000">2001~3000</option>
									<option value="3001~4000">3001~4000</option>
									<option value="4001~5000">4001~5000</option>
								</select>
							</div>
						</div>
						<input type="hidden" name="action"
							value="listCampsites_ByCompositeQuery">
						<div>
							<button type="submit" class="search-icon">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row row-2">
			<div class="col-md-5 map">
				<div id="map"></div>
				<script>
				  var geocoder;
				  var map;
				  var addressArray = [];
				  var campNameArray = [];
				  <c:set var="i" value="0" scope="page" />
				  <c:forEach var="campsiteVO" items="${campsiteList}">
					 addressArray[${i}]='${campsiteVO.location}';
					<c:set var="i" value="${i + 1}" scope="page"/>
				  </c:forEach>
				  <c:set var="i" value="0" scope="page" />
				  <c:forEach var="campsiteVO" items="${campsiteList}">
					 campNameArray[${i}]='${campsiteVO.campName}';
					<c:set var="i" value="${i + 1}" scope="page"/>
				  </c:forEach>
				  
				  function initialize() {
				    geocoder = new google.maps.Geocoder();
				    var latlng = new google.maps.LatLng(23.612794670237307, 120.88138952653813);
				    var myOptions = {
				      zoom: 8,
				      center: latlng,
				    gestureHandling: 'cooperative',
				    mapTypeControl: true,
				    mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
				    navigationControl: true,
				      mapTypeId: google.maps.MapTypeId.ROADMAP
				    };
				    map = new google.maps.Map(document.getElementById("map"), myOptions);
				    
				    for (i = 0; i < addressArray.length; i++) {
				   	let address = addressArray[i];
				   	let campName = campNameArray[i];
				    if (geocoder) {
				      geocoder.geocode( {'address': address}, function(results, status) {
				        if (status == google.maps.GeocoderStatus.OK) {
				          if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
				          map.setCenter(results[0].geometry.location);
				            var infowindow = new google.maps.InfoWindow(

				                { content: '<b>'+
				                	campName
								  +'</b>',
				                  size: new google.maps.Size(150,50)
				                });

				            var marker = new google.maps.Marker({
				                position: results[0].geometry.location,
				                map: map, 
				                title:address
				            }); 
				            google.maps.event.addListener(marker, 'click', function() {
				                infowindow.open(map,marker);
				            });

				          } else {
				            alert("No results found");
				          }
				        } else {
// 				          alert("Geocode was not successful for the following reason: " + status);
				        }
				      });
				    }
				    }
				  }
				</script>
				<script
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXvwD_h4qVCbRmi7_BkRKrc4ySwpnm604&callback=initialize"
					async defer></script>
			</div>
			<div class="col-md-7 camp-list">
				<div class="list-group">
					<c:forEach var="campsiteVO" items="${campsiteList}">
						<a
							href="campsite.do?action=getReserveCampsite&campId=${campsiteVO.campId}"
							class="list-group-item list-group-item-action flex-column align-items-start">
							<div class="row a-tag">
								<div class="col-md-6">
									<div class="camp-content-div">
										<div class="d-flex w-100 justify-content-between camp-name">
											<h5>${campsiteVO.campName}</h5>
										</div>
										<div class="camp-addr">
											<p>地址: ${campsiteVO.location}</p>
										</div>
										<div class="camp-content">
											<p class="ellipsis">${campsiteVO.campDescription}</p>
										</div>
									</div>
									<div class="camp-price">
										<p>$${campsiteVO.campPrice} (平均每人一晚)</p>
									</div>
								</div>
								<div class="col-md-6 camp-pic-div">
									<img
										src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campsiteVO.campId}"
										class="rounded float-right camp-picture" alt="...">
								</div>
							</div>
						</a>
					</c:forEach>
					<input type="hidden" id="listExist" value="${listExist}">
				</div>
			</div>
		</div>
		</div>
		<%@ include file="/template/script.html"%>
		<!-- 日期選擇器的 JS -->
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<!-- 載入 Font Awesome -->
		<script src="https://kit.fontawesome.com/846e361093.js"
			crossorigin="anonymous"></script>
		<!-- 載入 JS -->
		<script
			src="<%=request.getContextPath()%>/campsite/vendors/bootstrap/js/SearchCamp.js"></script>
		<script>
			if($("#listExist").val()=="empty"){
				$(".default-list").attr("style", "display:none");
			}
		</script>
</body>

</html>