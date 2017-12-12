<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
  String oldUser = request.getParameter("olduser");
	String oldPass = request.getParameter("oldpass");
  String newPass = request.getParameter("newpass");
	
  Database.getInstance().changePassword(oldUser, oldPass, newPass);  
%>
Password changed successfully. <BR/>
<%@include file="changePwdForm.jsp" %>
