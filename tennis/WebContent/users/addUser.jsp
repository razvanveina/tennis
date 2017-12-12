<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
	String user2 = request.getParameter("user");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String surname = request.getParameter("surname");
  String admin = request.getParameter("admin");
	
	User newUser = new User(user2, pass, name, surname, !admin.equals(""));
	Database.getInstance().addUser(newUser); 
%>

<%@include file="users.jsp" %> 
