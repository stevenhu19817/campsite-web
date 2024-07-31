<%@page import="java.util.List"%>
<%@page import="com.camporder.model.*"%>
<%@page import="com.post.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="CSvc" class="com.campsite.model.CampsiteService"
	scope="page"></jsp:useBean>
<jsp:useBean id="memSvc" class="com.members.model.MemberService"
	scope="page"></jsp:useBean>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	Object account = session.getAttribute("account");
	if (account == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/register_and_login/login.jsp");
		return;
	}
	CampOrderService COSvc = new CampOrderService();
	List<CampOrderVO> list = COSvc.getByMemberId((Integer) session.getAttribute("id"));
	PostService PSvc = new PostService();
	List<PostVO> postList = PSvc.findByAuthor((Integer)session.getAttribute("id"));
	pageContext.setAttribute("postList", PSvc.findByAuthor((Integer)session.getAttribute("id")));
	
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("id", session.getAttribute("id"));
	
%>


<!DOCTYPE html>
<html lang="zh-tw">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet" href="./style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/post/summernote-0.8.18-dist/summernote-lite.css">
<style>
#option {
	background-color: white;
	border: 0px;
	color: black;
}
button:focus {
	/* border: none !important; */
	outline: none !important;
	box-shadow: none !important;
}

textarea:focus {
	border: none !important;
	outline: none !important;
	box-shadow: none !important;
}

input:focus {
	outline: none !important;
	box-shadow: none !important;
}

</style>
</head>

<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col">
				<ul class="nav nav-tabs mb-4" id="pills-tab" role="tablist">
					<li class="nav-item mx-4" role="presentation">
						<button class="nav-link active" id="pills-home-tab"
							data-bs-toggle="pill" data-bs-target="#pills-home" type="button"
							role="tab" aria-controls="pills-home" aria-selected="true">
							<h3>行程</h3>
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="pills-profile-tab"
							data-bs-toggle="pill" data-bs-target="#pills-profile"
							type="button" role="tab" aria-controls="pills-profile"
							aria-selected="false">
							<h3>個人文章</h3>
						</button>
					</li>
					<li class="nav-item mx-4" role="presentation">
						<button class="nav-link" id="pills-contact-tab"
							data-bs-toggle="pill" data-bs-target="#pills-contact"
							type="button" role="tab" aria-controls="pills-contact"
							aria-selected="false">
							<h3>儲存營地</h3>
						</button>
					</li>
				</ul>
				<div class="tab-content" id="pills-tabContent">
					<div class="tab-pane fade show active" id="pills-home"
						role="tabpanel" aria-labelledby="pills-home-tab">
						<div class="container">
							<div class="row">
								<c:forEach var="order" items="${list}">
									<div class="col-12 mb-4">
										<div class="card mx-auto" style="width: 40rem;">
											<img
												src="<%=request.getContextPath()%>/CampsiteGifReader?column=picture1&camp_id=${order.campId}"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">${CSvc.getOneCampsite(order.campId).campName}</a>
														</h5>
													</div>
													<div class="col d-flex justify-content-end">
														<span class="badge rounded-pill bg-info">已預訂行程</span>
													</div>
												</div>
												<p class="card-text">
													<a href=""
														onclick="window.open('https://www.google.com.tw/maps/place/${CSvc.getOneCampsite(order.campId).location}', '_blank');">${CSvc.getOneCampsite(order.campId).location}</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">
												<li class="list-group-item">預訂期間:
													${order.checkInDate}至${order.checkOutDate}</li>
												<li class="list-group-item">人數: ${order.guestNumber}</li>
												<li class="list-group-item">總價: ${order.orderTotal}</li>
											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-primary">修改訂單</button>
												<button type="button" class="btn btn-danger">取消訂單</button>
											</div>
										</div>
									</div>
								</c:forEach>
								<div class="col-12 mb-4">
									<div class="card mx-auto" style="max-width: 40rem;">
										<img src="./img/campsite/741692.jpg" class="card-img-top"
											alt="...">
										<div class="card-body">
											<div class="row align-items-center">
												<div class="col">
													<h5 class="card-title mb-2">
														<a href="">拉拉山營區</a>
													</h5>
												</div>
												<div class="col d-flex justify-content-end">
													<span class="badge rounded-pill bg-secondary">過往行程</span>
												</div>
											</div>
											<p class="card-text">
												<a href="">336桃園市復興區華陵村11鄰180-11號</a>
											</p>
										</div>
										<ul class="list-group list-group-flush">
											<li class="list-group-item">預訂期間: 8/17至8/20</li>
											<li class="list-group-item">人數: 3</li>
											<li class="list-group-item">總價: 1500元</li>
										</ul>
										<div class="card-body">
											<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#review">給予評價</button>
											<button type="submit"
												class="btn btn-outline-danger float-end"
												data-bs-toggle="modal" data-bs-target="#staticBackdrop">檢舉營地</button>
											<div class="modal fade" id="staticBackdrop"
												data-bs-backdrop="static" data-bs-keyboard="false"
												tabindex="-1" aria-labelledby="staticBackdropLabel"
												aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="staticBackdropLabel">檢舉營地</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close"></button>
														</div>
														<div class="modal-body">
															<form method="POST" enctype="multipart/form-data"
																ACTION="<%=request.getContextPath()%>/backendLogin/CampAlert.do">
																<div class="mb-1">
																	<input name="title" type="text" placeholder="檢舉事由"
																		class="form-control" id="recipient-name" />
																</div>
																<div class="mb-3">
																	<textarea style="resize: none;" placeholder="檢舉內容"
																		class="form-control" name="comment"
																		id="exampleFormControlTextarea1" rows="4"></textarea>
																</div>
																<div class="mb-3">
																	<label for="formFileMultiple" class="form-label">檢舉照片</label>
																	<input class="form-control" type="file"
																		id="formFileMultiple" name="file" multiple>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-bs-dismiss="modal">取消</button>
																	<button type="submit" class="btn btn-primary">送出</button>
																	<input type="hidden" name="action" value="insert">
																	<input type="hidden" name="id" value="${id}"> <input
																		type="hidden" name="campId" value=5001>
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
											<div class="modal fade" id="review"
												data-bs-backdrop="static" data-bs-keyboard="false"
												tabindex="-1" aria-labelledby="staticBackdropLabel"
												aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="staticBackdropLabel">給予此營地評語</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close"></button>
														</div>
														<div class="modal-body">
															<form method="POST"
																ACTION="<%=request.getContextPath()%>/campOrder.do">
																<div class="mb-3">
																	<textarea style="resize: none;" placeholder="留下評論吧"
																		class="form-control" name="comment"
																		id="exampleFormControlTextarea1" rows="4" required="required"></textarea>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-bs-dismiss="modal">取消</button>
																	<button type="submit" class="btn btn-primary">送出</button>
																	<input type="hidden" name="action" value="addComment">
																	<input type="hidden" name="orderid" value="1037"> <input
																		type="hidden" name="campId" value=5001>
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="pills-profile" role="tabpanel"
						aria-labelledby="pills-profile-tab">
						<div class="container">
							<c:forEach var="postVO" items="${postList}">
								<div id="post" class="row card mb-3" style="max-width: 800px">
									<div class="row g-0">
										<div class="col">
											<div class="card-body">
												<div class="row">
													<div class="col-11">
														<p class="card-text">
															<small class="text-muted">發布於 ${postVO.passed} -
																<a id="author" href="">${memSvc.findByPrimaryKey(postVO.authorId).name}</a>
															</small>
														</p>
														<h4 class="card-title">
															<a
																href="<%=request.getContextPath()%>/post/post.jsp?postId=${postVO.postId}"
																style="text-decoration: none; color: inherit;">${postVO.title}</a>
														</h4>
													</div>
													<div class="col-1 text-end">
														<div class="btn-group dropend">
															<button id="option" class="btn btn-secondary btn-sm"
																type="button" data-bs-toggle="dropdown"
																aria-expanded="false" style="background-color: #F3F2EF;">
																<i class="fas fa-ellipsis-v"></i>
															</button>
															<ul class="dropdown-menu">
																<li class="dropdown-item text-end">
																	<button data-bs-toggle="modal"
																		data-bs-target="#popEdit${postVO.postId}" style="background-color: white; border: 0px">編輯</button>
																</li>
																<li class="dropdown-item text-end">
																	<form method="post"
																		action="<%=request.getContextPath()%>/post/post.do">
																		<input id="delete"
																			style="background-color: white; border: 0px"
																			type="submit" value="刪除"> <input
																			type="hidden" name="action" value="delete"> <input
																			type="hidden" name="postId" value="${postVO.postId}">
																	</form>
																</li>
															</ul>
														</div>
													</div>
												</div>
												<p class="card-text">${postVO.article}</p>
												<p class="read-more"></p>
											</div>
										</div>
									</div>
								</div>
								<div class="modal fade" id="popEdit${postVO.postId}"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-xl">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="staticBackdropLabel">編輯文章</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form method="POST"
													ACTION="<%=request.getContextPath()%>/post/post.do">
													<div class="mb-1">
														<input name="title" type="text" placeholder="文章標題"
															class="form-control" id="recipient-name"
															value="${postVO.title}" />
													</div>
													<div class="mb-3">
														<textarea name="article" class="summernote"
															id="summernote" cols="30" rows="30">${postVO.article}</textarea>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<button type="submit" class="btn btn-primary">確認</button>
														<input type="hidden" name="action" value="update">
														<input type="hidden" name="postId"
															value="${postVO.postId}">
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="tab-pane fade" id="pills-contact" role="tabpanel"
						aria-labelledby="pills-contact-tab">
						<div class="container">
							<div id="carouselExampleDark"
								class="carousel carousel-dark slide" data-bs-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img src="./img/campsite/EVFKVCY3BYZU2SCFWTUVA3OBMY.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">比撒日灣山中漫城露營區</a>
														</h5>
													</div>

												</div>
												<p class="card-text">
													<a href="">556南投縣信義鄉開高巷101-9號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>

									</div>
									<div class="carousel-item" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img src="./img/campsite/984404_15071414010032265180.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">大雪山喜樂之地露營區</a>
														</h5>
													</div>

												</div>
												<p class="card-text">
													<a href="">423台中市東勢區東坑路978-6號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>

									</div>
									<div class="carousel-item" data-bs-interval="10000">
										<div class="card mx-auto" style="width: 40rem;">
											<img style="height: 370px;"
												src="./img/campsite/Three+Hares+Campsite-5.jpg"
												class="card-img-top" alt="...">
											<div class="card-body">
												<div class="row align-items-center">
													<div class="col">
														<h5 class="card-title mb-2">
															<a href="">鹿野高台大草原露營區</a>
														</h5>
													</div>
												</div>
												<p class="card-text">
													<a href="">台東縣鹿野鄉永安村高台路42巷133號</a>
												</p>
											</div>
											<ul class="list-group list-group-flush">

											</ul>
											<div class="card-body">
												<button type="button" class="btn btn-success">立即預定</button>
												<button type="button" class="btn btn-danger">移除</button>
											</div>
										</div>
									</div>
								</div>
								<button class="carousel-control-prev" type="button"
									data-bs-target="#carouselExampleDark" data-bs-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button"
									data-bs-target="#carouselExampleDark" data-bs-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Next</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/template/script.html"%>
		<script src="<%=request.getContextPath()%>/post/summernote-0.8.18-dist/summernote-lite.js"></script>
		<script src="<%=request.getContextPath()%>/post/summernote-0.8.18-dist/lang/summernote-zh-TW.js"></script>
	<script>
	$(document).ready(function(){
		$('.summernote').summernote({
			spellCheck: false,
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'italic', 'clear']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['insert', ['picture'], ['elfinder']],
				['height', ['height']],
				['view', ['fullscreen', 'codeview', 'help']]],
			lang: 'zh-TW',
			lineHeights: ['0.4', '0.5',
				'0.6', '0.8', '1.0',
				'1.2', '1.4', '1.5',
				'2.0', '3.0'],
			placeholder: "寫些什麼吧"
		});
		$('div.note-editable').height(300);
	})
	
	</script>

</body>
</html>