<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%Member member = (Member)session.getAttribute("member");%> --%>
<div
	style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
	SPMS(simple project management system) <span style="float: right;">
		<c:if test="${member.name!=null}">${member.name}
		<a style="color: white;" href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>
		</c:if> 
		<c:if test="${member.name==null}">
		<a style="color: white;" href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
		</c:if>
	</span>
</div>