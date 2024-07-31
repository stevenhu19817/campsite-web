<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	Integer finalPrice = new Integer(request.getParameter("finalPrice"));
	pageContext.setAttribute("finalPrice", finalPrice);
%>
<jsp:useBean id="planSvc" class="com.plan.model.PlanService" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
#submit:hover {
	background-color: yellow;
	cursor: pointer;
}

</style>
</head>
<body>
	<div class="container mt-3 px-5">
		<div class="mb-4">
			<h2>確認訂單和結帳</h2>
			<span>please make the payment, after that you can enjoy all
				the features and benefits.</span>
		</div>
		<div class="row">
			<div class="col-md-7">
				<div class="card p-3" style="background-color: #F9F9F9">
					<h6 class="text-uppercase">Payment details</h6>
					<div class="inputbox mt-3">
						<input type="text" name="name" class="form-control"
							required="required"> <span>卡號</span>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="inputbox mt-3 mr-2">
								<input type="text" name="name" class="form-control"
									required="required"> <i class="fa fa-credit-card"></i>
								<span>姓名</span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="d-flex flex-row">
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>到期月份</span>
								</div>
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>安全碼</span>
								</div>
							</div>
						</div>
					</div>
					<div class="mt-4 mb-4">
						<h6 class="text-uppercase">Billing Address</h6>
						<div class="row mt-3">
							<div class="col-md-6">
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>Street Address</span>
								</div>
							</div>
							<div class="col-md-6">
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>City</span>
								</div>
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-md-6">
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>State/Province</span>
								</div>
							</div>
							<div class="col-md-6">
								<div class="inputbox mt-3 mr-2">
									<input type="text" name="name" class="form-control"
										required="required"> <span>Zip code</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="mt-4 mb-4 d-flex justify-content-between">
					<span>Previous step</span>
					<span style="color: red;">${repeat}</span>
				</div>
			</div>
			<div class="col-md-5 d-flex justify-content-center">
				<div class="card" style="width: 18rem;">
					<img src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${campOrderVO.campId}" class="card-img-top" alt="camp-site-image">
					<div class="card-body">
						<h5 class="card-title">那山那谷</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">入住日期: <span class="float-end">${campOrderVO.checkInDate}</span></li>
						<li class="list-group-item">退住日期: <span class="float-end">${campOrderVO.checkOutDate}</span></li>
						<li id="afterHere" class="list-group-item">人數: <span class="float-end">${campOrderVO.guestNumber}</span></li>
						<c:forEach var="planVO" items="${planList}">
							<li class="list-group-item">${planSvc.getOnePlanByPlanId(planVO.planId).planName}<span class="selectedPlan">/${planVO.planGuestNumber}位</span><span class="float-end">${planVO.planOrderPrice}</span></li>
						</c:forEach>
						<li class="list-group-item">
							<h5 style="display: inline;">總價:</h5>
							<h5 style="display: inline;"><span class="float-end total"></span></h5>
						</li>
						<li id="submit" class="list-group-item text-center" style="font-size:1.33rem; border: 0px; color: white; height: 45px; background-color: #40D9AC;">付款
						<form action="<%=request.getContextPath()%>/campOrder.do" method="post">
						<input type="hidden" name="action" value="createOrder">
						<input type="hidden" name="finalPrice" value="${finalPrice}">
					</form></li>
					</ul>
					<div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			$(".float-end.total").text(<%=finalPrice%>);
			$("#submit").click(function () {
			    $('form').submit();
			});
		})
	</script>
</body>
</html>