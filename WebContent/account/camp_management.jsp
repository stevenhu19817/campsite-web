<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.camprelease.model.*"%>
<!DOCTYPE html>
<% 
	CampReleaseService CRSvc = new CampReleaseService();
	List<CampReleaseVO> list = CRSvc.getAllforMember((Integer) session.getAttribute("1"));
	pageContext.setAttribute("list", list);
	System.out.println(list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>