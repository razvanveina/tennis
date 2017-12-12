<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.model.*" %>
<% 
	String userS = request.getParameter("user");
	String pass = request.getParameter("pass");
	User userLogin = Database.getInstance().checkLogin(userS, pass);
	if (userLogin != null) {
		session.setAttribute("user", userLogin);
	} else {
    throw new RuntimeException("Invalid user / password");
	}
%>

<%@include file="index.jsp" %> 