<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*"%>
<%@include file="menu.jsp" %> 
<%    
boolean isAdmin=false;
if (((User)session.getAttribute("user")).isAdmin()) {
  isAdmin=true;
} 
%>