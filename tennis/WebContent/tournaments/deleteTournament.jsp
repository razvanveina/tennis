<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
    
<% 
  String tourName = request.getParameter("name");

  Tournament tour = Database.getInstance().getTournamentByName(tourName);
  Database.getInstance().removeTournament(tour);
%>

<%@include file="viewTournaments.jsp" %>  
