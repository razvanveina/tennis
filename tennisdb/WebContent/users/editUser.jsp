<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*, com.ssn.core.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
  String oldUser = request.getParameter("olduser");
	String user2 = request.getParameter("user");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String surname = request.getParameter("surname");
	String admin = request.getParameter("admin");
 
	ApplicationFactory.getInstance().getDatabase().editUser(oldUser, user2, pass, name, surname, admin!=null);
%>

<%@include file="users.jsp" %>
