<%@page import="com.ssn.core.ApplicationFactory"%>
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
	
 if(!user2.equals("") && (ApplicationFactory.getInstance().getDatabase().getUserByUsername(user2)==null)){ 
  
	User newUser = new User(user2, pass, name, surname, admin == null || !admin.equals(""));
	ApplicationFactory.getInstance().getDatabase().addUser(newUser);%>
<%@include file="users.jsp" %>    
<%
 }else{
%>
<script>
alert("Invalid username");
</script>

<%@include file="addUserForm.jsp" %> 
<%} %>
