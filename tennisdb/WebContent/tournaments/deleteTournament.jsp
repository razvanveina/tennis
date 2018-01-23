<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="com.ssn.core.*"%>
    
    
    <%@include file="../checkLogin.jsp" %>
    
    
<% 
  String tourName = request.getParameter("name");

  Tournament tour = ApplicationFactory.getInstance().getDatabase().getTournamentByName(tourName);
  ApplicationFactory.getInstance().getDatabase().removeTournament(tour);
%>

<%@include file="viewTournaments.jsp" %>  
