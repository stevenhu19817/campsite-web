<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("name", session.getAttribute("account"));
%>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首頁</title>
<!-- 日期選擇器的 CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<title>Document</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/homepage/index.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>

<body>
	<h1 id="title" class="text-center mt-5">Find yourself outside</h1>
	<h3 class="text-center mb-5">
		Discover and book tent camping, RV parks,<br> cabins, treehouses,
		and glamping.
	</h3>

	<div id="decoImg" class="container-sm ">
		<div id="search" class="container">
			<form method="post" id="form" action="campsite.do">
				<div
					class="d-flex container row mt-3 gx-2 align-items-center justify-content-center has-search"
					id="searchSet">
					<div class="col-md-4">
						<i class="fas fa-location-arrow form-control-feedback"></i> <input
							type="text" name="CAMP_NAME" class="form-control has-icon"
							placeholder="請輸入營地或地址..." autocomplete="off">
					</div>
					<div class="col-md-4">
						<i class="fas fa-calendar-alt form-control-feedback"></i> <input
							type="text" id="date" class="form-control choose-date has-icon"
							name="CAMP_OPENING_TIME" value="" placeholder="請選擇日期..."
							autocomplete="off" readonly>
					</div>
					<div class="col-md-2">
						<i class="fas fa-user-friends form-control-feedback"></i> <input
							type="text" name="EMPTY_CAMP_LEFT" class="form-control has-icon"
							placeholder="請輸入人數..." autocomplete="off">
					</div>
					<input type="hidden" name="action"
							value="listCampsites_ByCompositeQuery">
					<div class="col-md-1">
						<button type="submit" class="btn btn-success">
							<i class="bi bi-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
      <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
        <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
      </a>
      <span class="text-muted">© 2021 <a href="<%=request.getContextPath()%>/backendLogin/backendLogin.jsp">GoCamping, Inc</a></span>
    </div>
  </footer>
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
	<script>
		$(function() {
			var date = new Date();
			$('input[name="CAMP_OPENING_TIME"]').daterangepicker(
					{
						autoUpdateInput : false,
						"alwaysShowCalendars" : true,
						opens : "center",
						startDate : date,
						endDate : moment(date).add(2, 'days'),
						minDate : date,
						maxDate : moment(date).add(60, 'days'),
						autoApply : true,
						locale : {
							format : "YYYY/MM/DD",
							separator : " ~ ",
							applyLabel : "確定",
							cancelLabel : "清除",
							fromLabel : "開始日期",
							toLabel : "結束日期",
							daysOfWeek : [ "日", "一", "二", "三", "四", "五", "六" ],
							monthNames : [ "1月", "2月", "3月", "4月", "5月", "6月",
									"7月", "8月", "9月", "10月", "11月", "12月" ],
							firstDay : 1
						}

					});

			$('input[name="CAMP_OPENING_TIME"]').on(
					'apply.daterangepicker',
					function(ev, picker) {
						$(this).val(
								picker.startDate.format('YYYY-MM-DD') + ' ~ '
										+ picker.endDate.format('YYYY-MM-DD'));
					});

			$('input[name="CAMP_OPENING_TIME"]').on('cancel.daterangepicker',
					function(ev, picker) {
						$(this).val('');
					});
		});
	</script>
</body>

</html>