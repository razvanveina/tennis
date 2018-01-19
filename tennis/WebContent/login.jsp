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
    %>
<font face="verdana" color="green">Invalid username or password! please try again </font>
<br />
    <% 
	}
%>

<%@include file="index.jsp" %> 