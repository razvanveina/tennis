<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*, com.ssn.core.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
    
<% 
	String userName = request.getParameter("name");

	User user2 = ApplicationFactory.getInstance().getDatabase().getUserByUsername(userName);
	ApplicationFactory.getInstance().getDatabase().removeUser(user2);
%>

<%@include file="users.jsp" %>   
