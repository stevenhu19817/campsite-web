<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="oracle.net.aso.f"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.facilities.model.*"%>
<%@page import="com.camprelease.model.*"%>
<%@page import="com.campsitetentstatus.model.*"%>
<% 
	Integer id = null;
	if(session.getAttribute("id") != null){
	id = Integer.parseInt(session.getAttribute("id").toString());
	System.out.println("id: " + id);
	pageContext.setAttribute("id", id);		
	}
// 有營地時設備可新增
	CampReleaseService csvc = new CampReleaseService();
	FacilitiesService fsvc = new FacilitiesService();
	List<CampReleaseVO> memberCamp = csvc.getAllforMember(id);
	ArrayList<CampReleaseVO> noFacCamp = new ArrayList<CampReleaseVO>();
	for(int i = 0; i < memberCamp.size(); i++){
		FacilitiesVO fvo = fsvc.getCampId(memberCamp.get(i).getCampId());
		System.out.println(fvo);		
		if(fvo == null){
			noFacCamp.add(memberCamp.get(i));
		} 
	}	
	pageContext.setAttribute("noFacCamp", noFacCamp);
	pageContext.setAttribute("memberCamp", memberCamp);
	
// 有營地時帳篷狀態可新增
	CampReleaseService csvctent = new CampReleaseService();
	CampsiteTentStatusService ctss = new CampsiteTentStatusService();
	List<CampReleaseVO> memberCamptent = csvctent.getAllforMember(1);
	ArrayList<CampReleaseVO> noTentCamptent = new ArrayList<CampReleaseVO>();
	for(int i = 0; i < memberCamp.size(); i++){
		CampsiteTentStatusVO cvo = ctss.getOneCampsiteTentStatus(memberCamp.get(i).getCampId());
		System.out.println(cvo);		
		if(cvo == null){
			noTentCamptent.add(memberCamp.get(i));
		}
	}	
	pageContext.setAttribute("noTentCamptent", noTentCamptent);
	pageContext.setAttribute("memberCamptent", memberCamptent);
%>
<jsp:useBean id="campreleaseSvc" scope="page" class="com.camprelease.model.CampReleaseService" />
<jsp:useBean id="facilitiesSvc" scope="page" class="com.facilities.model.FacilitiesService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首頁</title>
<%@ include file="/template/navbar.jsp" %>

<style>
body {
	background-color: #FFEEE1;
}

.bg-body {
	color: white;
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	

	<main class="container">
		<!-- 標題-->
		<div
			class="d-flex align-items-center p-3 my-3 text-black bg-purple rounded shadow-sm">
			<img class="me-3" src="./svg/triangle-half.svg" alt="" width="48"
				height="38">
			<div class="lh-1">Go Camping</div>
		</div>

		<!-- 營地刊登相關 -->
		<div class="my-3 p-3 rounded ">
			<h6 class="border-bottom pb-2 mb-0">營地資料管理</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">營地上架管理</strong> 
					<a href='<%=request.getContextPath()%>/camprelease/addCampRel.jsp'>Add</a>
					a new Camp.
				</p>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由營地名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/camprelease/camprelease.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>

		<!-- 配套資料相關 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">配套資料管理</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">配套新增管理</strong> 
					a new Plan.
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="go_to_addPlan">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由營地名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/plan/plan.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOnePlan_For_Display">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>
		
		<!--設施資料相關 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">設施資料管理</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">設施新增管理</strong> 
					a new Fac.
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${noFacCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="go_to_addFacil">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由營地名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/facilities/facilities.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action"
								value="getOneFacilities_For_Display"> <input type="submit"
								value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>
		
				<!-- 營地帳篷狀態相關 -->
		<div class="my-3 p-3 rounded shadow-sm">
			<h6 class="border-bottom pb-2 mb-0">營地帳篷狀態資料管理</h6>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">營地帳篷狀態新增管理</strong> 
					a new CampTent.
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="go_to_addCampTent">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
			<div class="d-flex text-muted pt-3">
				<img class="me-3" src="./svg/tree.svg" alt="" width="48" height="38">
				<p class="pb-3 mb-0 small lh-sm border-bottom">
					<strong class="d-block text-gray-dark">由營地名稱查詢</strong>
				</p>
				<ul>
					<li>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/campsitetentstatus/campsitetentstatus.do">
							<select size="1" name="campId">
								<c:forEach var="campreleaseVO" items="${memberCamp}">
									<option value="${campreleaseVO.campId}">${campreleaseVO.campName}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getAllOfOne">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>
			</div>
		</div>
	</main>
	
	<%@ include file="/template/script.html" %>
	<script src="<%=request.getContextPath()%>/camprelease/js/popper.min.2.10.2.js"></script>
</body>
</html>