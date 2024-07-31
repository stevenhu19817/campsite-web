<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="com.post.model.PostVO"%>
<%@page import="com.post.model.PostService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	PostService postSvc = new PostService();
	PostVO postVO = postSvc.findByPostId(Integer.parseInt(request.getParameter("postId")));
	pageContext.setAttribute("postId", Integer.parseInt(request.getParameter("postId")));
	pageContext.setAttribute("postVO", postVO);
%>
<jsp:useBean id="memSvc" class="com.members.model.MemberService"
	scope="request"></jsp:useBean>
<jsp:useBean id="commentSvc"
	class="com.postComment.model.PostCommentService" scope="page"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/template/navbar.jsp"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link rel="stylesheet" href="post.css" />
<style>
button:focus {
	/* border: none !important; */
	outline: none !important;
	box-shadow: none !important;
}

#userImage {
	background-size: cover;
	max-width: 50px;
	height: 50px;
	max-height: 50px;
	border-radius: 50%;
	display: inline-block;
	background-image:
		url('data:image/jpg;base64,${memSvc.findByPrimaryKey(postVO.authorId).base64Image}');
	max-height: 50px;
}

#commentImage {
	background-size: cover;
	max-width: 50px;
	height: 50px;
	max-height: 50px;
	border-radius: 50%;
	display: inline-block;
	max-height: 50px;
}

#option {
	background-color: white;
	border: 0px;
	color: #6E6865;
	font-size: 10px;
	margin-top: 15px;
	margin-left: 15px;
}
</style>
<title>${postVO.title}</title>
</head>
<body style="background-color: #484442">

	<div class="container" id="main"
		style="padding-bottom: 25px; margin-top: 50px; max-width: 1100px; background-color: white; border-radius: 10px">
		<div class="container">
			<div class="px-3">
				<div class="row">
					<h1 class="col-11" style="padding-top: 30px;">${postVO.title}</h1>
					<div class="col-1 text-end">
						<div class="btn">
							<button id="option" style="border: 0px;"
								class="btn btn-outline-secondary btn-sm" type="button"
								data-bs-toggle="dropdown" aria-expanded="false">
								<i class="fas fa-ellipsis-v fa-2x"></i>
							</button>
							<ul class="dropdown-menu" style="width: 60px">
								<li class="dropdown-item text-end">
									<button data-bs-toggle="modal" s
										data-bs-target="#popEdit${postVO.postId}"
										style="background-color: white; border: 0px">編輯</button>
								</li>
								<li class="dropdown-item text-end">
									<form method="post"
										action="<%=request.getContextPath()%>/post/post.do">
										<input id="delete"
											style="background-color: white; border: 0px" type="submit"
											value="刪除"> <input type="hidden" name="action"
											value="delete"> <input type="hidden" name="postId"
											value="${postVO.postId}">
									</form>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row mb-3 px-2 py-2">
					<div class="col-1" id="userImage"></div>
					<div class="col-2">
						<small>${memSvc.findByPrimaryKey(postVO.authorId).name}<br>
							${postVO.passed}
						</small>
					</div>
				</div>
				${postVO.article}
				<div class="col" id="filter" style="margin-bottom: 20px">
					<button type="button" style="border: 0px;"
						class="btn btn-outline-secondary p-2">
						<i class="far fa-comment-dots" style="margin-right: 5px;"></i>${commentSvc.findByPostId(postVO.postId).size()}
					</button>
					<button type="button" style="border: 0px;"
						class="btn btn-outline-secondary p-2">
						<i class="far fa-flag" style="margin-right: 5px;"></i>檢舉
					</button>
				</div>
				<form class="pt-2"
					action="<%=request.getContextPath()%>/post/comment.do"
					method="post">
					<textarea style="resize: none;" placeholder="快來發表你的評論吧~"
						class="form-control" name="comment"
						id="exampleFormControlTextarea1" rows="4"></textarea>
					<button type="submit" class="btn btn-outline-secondary float-end"
						style="margin-top: 15px">發送</button>
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="postId" value="${postId}">
				</form>
				<div style="margin-top: 90px">
					<hr>
				</div>
				<div class="container">
					<c:forEach var="commentVO"
						items="${commentSvc.findByPostId(postVO.postId)}">
						<div class="row mb-2 py-2">
							<div class="col-1" id="commentImage"
								style="background-image:
									url('data:image/jpg;base64,${memSvc.findByPrimaryKey(commentVO.memberId).base64Image}');"></div>
							<div class="col-2">
								<small>${memSvc.findByPrimaryKey(commentVO.memberId).name}<br>
									${commentVO.passed}
								</small>
							</div>
						</div>
						<div>${commentVO.content}</div>
						<hr style="margin-bottom: 5px;">
					</c:forEach>
				</div>
			</div>
		</div>
		<div></div>

	</div>

	<%@ include file="/template/script.html"%>
</body>
</html>