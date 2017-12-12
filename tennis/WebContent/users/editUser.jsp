<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.ssijs.common.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    <%@include file="../checkAdminRights.jsp" %> 
    
<% 
  String oldUser = request.getParameter("olduser");
	String user2 = request.getParameter("user");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String surname = request.getParameter("surname");
	String email = request.getParameter("email");
	
	DataSource dataSource = DataSourceFactory.getDataSource();
  dataSource.editUser(oldUser, user2, pass, name, surname, email);
%>

<%@include file="users.jsp" %>
