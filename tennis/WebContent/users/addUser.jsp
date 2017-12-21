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
	
 if(user2.equals("") || (Database.getInstance().getUserByUsername(user2)!=null)){ 
  
	User newUser = new User(user2, pass, name, surname, admin == null || !admin.equals(""));
	Database.getInstance().addUser(newUser);
 }else{
%>
invalid or used username
<%} %>
<%@include file="users.jsp" %> 
