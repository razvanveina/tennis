<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormats"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add tournament</title>
</head>
<body>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %> 
<%SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy-HH:mm ");
String date=dateFormat.format(new Date(System.currentTimeMillis()));
%>

<FORM action="addTournament.jsp" method="POST">
Name: <INPUT type="text" name="name"/><BR/>
Date: <INPUT type="text" name="date" value=<%=date%>/><BR/>
Type: <select name="type">
  <option value="DOUBLE">DOUBLE</option>
  <option value="SINGLE">SINGLE</option>
</select><BR/>
Format: <select name="format">
<% for (TournamentFormat tf : new TournamentFormats().getFormats()) { %> 
  <option value="<%=tf.getName() %>"><%=tf.getName() %></option>
  <% } %>
</select><BR/>
<INPUT type="submit" value="Add"/>
</FORM>