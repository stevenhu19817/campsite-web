<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.campsite.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>
<%
	CampsiteService dao = new CampsiteService();
	List<CampsiteVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<%
	MemberService memSvc = new MemberService();
	pageContext.setAttribute("memSvc", memSvc);
%>
<!DOCTYPE html>
<html>
<style>
img {
	width: 100px;
	height: 100px;
}
</style>
<head>
<meta charset="UTF-8">
<title>營地列表</title>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/camp.css">
</head>
<body>
	<div class="container">
		<nav class="sidebar">
			<div class="min_picture">
				<h1>後臺管理</h1>
			</div>
			<ul>
				<li><a href="#" class="feat-btn">帳號管理 <span
						class="fas fa-caret-down first"></span>
				</a>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/member.jsp" class="member_list">會員帳號管理</a></li>
					</ul>
					<ul class="feat-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
					</ul></li>
				<li><a href="#" class="serv-btn">商品管理 <span
						class="fas fa-caret-down second"></span>
				</a>
					<ul class="serv-show">
						<li><a href="<%=request.getContextPath()%>/product/selectAll.jsp"
							class="product_list">商品資料表</a></li>
					</ul></li>
				<li><a href="#1" class="bom-btn">營地管理 <span
						class="fas fa-caret-down second_1"></span>
				</a>

					<ul class="bom-show">
						<li><a href="<%=request.getContextPath()%>/backendLogin/camp.jsp" class="camp_list">營地列表</a></li>
						 <li><a href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp"class="camp_order">營地訂單</a></li>
						<li><a href="<%=request.getContextPath()%>/backendLogin/alert.jsp" class="alert_managament">檢舉管理</a></li>
					</ul></li>
				<li><a href="#" class="mky-btn">商城管理 <span
						class="fas fa-caret-down second_2"></span>
				</a>
					<ul class="mky-show">
						<li><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp" class="shopping_list">商城訂單</a></li>
					</ul>
					</li>
					<li>
                <form METHOD="get" ACTION="<%=request.getContextPath()%>/backendLogin/home.do">
                  <button type="submit" class="btn btn-outline-secondary" style="margin-left: 50px;">logout</button>
                </form>
                </li>
			</ul>
		</nav>
	</div>
	<div class="rightside">
		<h2>營地列表</h2>
		<br><br><br>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<div class="searcher">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/backendLogin/camplistone.do">
				<input type="text" class="search" name="campId" placeholder="營地編號查詢">
				<input type="hidden" name="action" id="" class="btn_search"
					value="getOne_For_Display">
				<button type="submit" class="btn btn-outline-success">查詢</button>
				<button type="button" class="btn btn-outline-success" id="export3">匯出</button>
			</Form>
		</div>
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th>營地編號</th>
					<th>營地業主</th>
					<th>營地名稱</th>
					<th>營地位置</th>
					<th>營地上架日期</th>
					<th>營地審核狀態</th>
					<th>營地檢舉次數</th>
					<th>營地營業許可證</th>
					<th>修改</th>

				</tr>
			</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="VO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>

					<td>${VO.campId}</td>
					<td>${memSvc.findByPrimaryKey(VO.memberId).name}</td>
					<td>${VO.campName}</td>
					<td>${VO.location}</td>
					<td>${VO.listedTime}</td>
					<td>${VO.siteState==0 ?  "已停權":"營業中"}</td>
					<td>${VO.reportedCount}</td>
					<div id="outerdiv" style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
						<div id="innerdiv" style="position: absolute;">
						<img id="bigimg" style="border: 5px solid #fff; height: 500px; width:300px;" src="" />
						</div>
					<td><img class="pimg"
						src="<%=request.getContextPath()%>/CampsiteGifReader?column=camp_license&camp_id=${VO.campId}"
						class="pic"></td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backendLogin/camplistone.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-primary"> <input type="hidden"
								name="campId" value="${VO.campId}"> <input type="hidden"
								name="action" value="getOne_For_Update">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	<script src="../js/jquery.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/jquery.table2excel.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/css/theme.blue.min.css"></link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.5/js/jquery.tablesorter.min.js"></script>
	<script>
		$("#myTable").tablesorter({
			theme : "blue",
			widgets : [ 'zebra' ]
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
	<script src="../js/camp.js"></script>
	<script>
		$(document).ready(function(){			
		$("#export3").on("click", function() {
				  $("#myTable").table2excel({
				    // exclude CSS class
				    exclude: ".noExl",
				    name: "Worksheet Name",
				    filename: "營地列表", //do not include extension
				    fileext: ".xls" // file extension
				  }); 
		});
		});
	</script>
	<script>
		$(document).ready(function() {
			$("#export4").on("click", function() {
				$("#myTable").table2excel({
					// exclude CSS class
					exclude : ".noExl",
					name : "Worksheet Name",
					filename : "營地檢舉列表", //do not include extension
					fileext : ".xls" // file extension
				});
			});
		});
	</script>

	<script>
		$(function() {
			$(".pimg").click(function() {
				var _this = $(this);//将当前的pimg元素作为_this传入函数
				imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
			});
		});
		function imgShow(outerdiv, innerdiv, bigimg, _this) {
			var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
			$(bigimg).attr("src", src);//设置#bigimg元素的src属性
			/*获取当前点击图片的真实大小，并显示弹出层及大图*/
			$("<img/>").attr("src", src).load(function() {
				var windowW = $(window).width();//获取当前窗口宽度
				var windowH = $(window).height();//获取当前窗口高度
				var realWidth = this.width;//获取图片真实宽度
				var realHeight = this.height;//获取图片真实高度
				var imgWidth, imgHeight;
				var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放       
				if (realHeight > windowH * scale) {//判断图片高度
					imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
					imgWidth = imgHeight/realHeight*realWidth; // 等比例缩放宽度
					if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
						imgWidth = windowW * scale;//再对宽度进行缩放
					}
				} else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
					imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放	
					imgHeight = imgWidth/realWidth*realHeight; // 等比例缩放高度
				} else {//如果图片真实高度和宽度都符合要求，高宽不变
					imgWidth = realWidth;
					imgHeight = realHeight;
				}
				$(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放
				var w = (windowW - imgWidth)/2;  // 计算图片与窗口左边距
				var h = (windowH - imgHeight)/2; // 计算图片与窗口上边距
				$(innerdiv).css({
					"top" : h,
					"left" : w
				});//设置#innerdiv的top和left属性
				$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
			});
			$(outerdiv).click(function() {//再次点击淡出消失弹出层
				$(this).fadeOut("fast");
			});
		}
	</script>
	
</body>
</html>