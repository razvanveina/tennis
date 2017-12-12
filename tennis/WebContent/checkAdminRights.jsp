<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*"%>
<%    
if (!((User)session.getAttribute("user")).isAdmin()) {
%>
<%@include file="menu.jsp" %> 
<%
return;
} 
%>