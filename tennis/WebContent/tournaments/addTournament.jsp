<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
	String name = request.getParameter("name");
  String dateS = request.getParameter("date");
  TournamentType type = TournamentType.valueOf(request.getParameter("type"));
	
  DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
  Date date = format.parse(dateS);
  
	Database.getInstance().addTournament(name, date, type); 
%>

<%@include file="viewTournaments.jsp" %> 
