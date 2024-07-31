<%@page import="com.campsitetentstatus.model.CampsiteTentStatusService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("id") == null) {
		session.setAttribute("location", request.getRequestURI());
	}
	pageContext.setAttribute("memberId", (Integer) session.getAttribute("id"));
	pageContext.setAttribute("campId", 5001);
	Integer guestCount = null;
	try{
		guestCount = new Integer (request.getParameter("guestCount"));
	}catch(NumberFormatException nfe){
		guestCount = null;
	}
	CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
	pageContext.setAttribute("unavilibleList", CTSSvc.getUnavailibleDatewithGuestNumberOnly(5001, 1));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
</head>
<body>
	<div class="container"
		style="max-width: 500px; max-height: 750px; margin-top: 120px; box-shadow: 0 2px 4px rgb(0 0 0/ 10%), 0 8px 16px rgb(0 0 0/ 10%);">
		<form action="<%=request.getContextPath()%>/campOrder.do"
			method="post">
			<h2 style="padding-top: 30px;">$1350</h2>
			<small>每人每晚</small>
			<hr>
			<p>人數:</p>
			<input type="text" class="form-control" name="headCount" id="headCount" value="">
			<hr>
			<p>入住時間:</p>
			<input class="flatpickr flatpickr-input active" id="selectDate"
				name="date" type="text" placeholder="停留時間" data-id="rangePlugin"
				readonly="readonly"> <input type="hidden" id="from"
				name="from" value=""> <input type="hidden" id="to" name="to"
				value="">
			<hr>
			<h3 style="padding-bottom: 20px;">
				總價: <span id="price"></span>
			</h3>
			<small style="color: red">${missing}</small> 
			<small style="color: red">${noSession}</small> 
			<small style="color: red">${noSpace}</small> 
			<small style="color: red">${repeat}</small> 
			<hr>
			<div class="d-flex justify-content-center">
				<button style="margin-bottom: 20px;" type="submit"
					class="btn btn-success btn-lg" id="book">預定</button>
				<input type="hidden" name="action" value="book"> 
				<input type="hidden" name="memberId" id="memberId" value="${memberId}">
				<input type="hidden" name="campId" value="${campId}">
				<input type="hidden" id="orderDate" name="orderDate" value=""> 
				<input type="hidden" id="deadline" name="deadline" value="">
				<input type="hidden" id="price" name="price" value="">
			</div>
		</form>
	</div>
	<div class="modal fade" id="loginFilter" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">提醒</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">在您預定營地前，請先登入</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">取消</button>
					<a
						href="<%=request.getContextPath()%>/register_and_login/login.jsp"
						class="btn btn-primary">登入</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/template/script.html"%>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
	<script type="text/javascript">
	$("#book").click(function (e) {
		if ($("#memberId").val() == '') {
			event.preventDefault();
			$("#loginFilter").modal("show");
		}
	})
		$(document).ready(function() {
			var today = new Date();
			var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
			var deadline = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+(today.getDate()+2);
			var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
			console.log(date);
			$("#orderDate").val(date+' '+time);
			$("#deadline").val(deadline+' '+time);
		})
		let daysBetween;
		const flatpickr = $("#selectDate").flatpickr(
				{
					mode: "range",
					altInput: true,
					altFormat: "F j",
					dateFormat: "Y-m-d",
					minDate: "today",
					maxDate: new Date().fp_incr(60), // 30 days from now
					showMonths : 2,
					disable: ${unavilibleList},
					locale: {
						weekdays: {
							shorthand: [ "週日", "週一", "週二", "週三", "週四", "週五",
									"週六" ],
							longhand: [ "星期日", "星期一", "星期二", "星期三", "星期四",
									"星期五", "星期六", ],
						},
						months: {
							shorthand: [ "一月", "二月", "三月", "四月", "五月", "六月",
									"七月", "八月", "九月", "十月", "十一月", "十二月", ],
							longhand: [ "一月", "二月", "三月", "四月", "五月", "六月",
									"七月", "八月", "九月", "十月", "十一月", "十二月", ],
						},
						rangeSeparator: " 至 ",
						weekAbbreviation: "週",
						scrollTitle: "滾動切換",
						toggleTitle: "點擊切換 12/24 小時時制",
					},
					onChange: function(dates) {
				        if (dates.length == 2) {
				            let start = flatpickr.formatDate(dates[0],"Y-m-d");
				            let end = flatpickr.formatDate(dates[1], "Y-m-d");
				            $("#from").val(start);
				            $("#to").val(end);				            
				            if (start && end) {
				                console.log({ start, end });
				              }
				            let newStart = new Date(start).getTime();
				            let newend = new Date(end).getTime();
				            daysBetween = eval((newend - newStart)/86400/1000);
				            console.log(daysBetween);
				            if (daysBetween != null && $("#headCount").val() > 0) {
				    			let price = daysBetween * $("#headCount").val() * 1350;
				    			$("#price").text(price);
				    			$("[name='price']").val(price);
				    			$("#headCounts").val($("#headCount").val());
				    		}
				        }
				    }
				});
		$("#headCount").change(function() {	
		if (daysBetween != null && $("#headCount").val() > 0) {
			let price = daysBetween * $("#headCount").val() * 1350;
			$("#price").text(price);
			$("[name='price']").val(price);
			}
		let campId = $("[name='campId']").val();
		let totalGuest = $("#headCount").val();
		let json = [campId, totalGuest];
		console.log(json);
		$.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/availibleDate",
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (response) {
            	var array = JSON.parse(response);
            	flatpickr.clear();
            	$("#price").text("");
            	flatpickr.set("disable", array);
             },
        });
		})
	</script>
</body>
</html>