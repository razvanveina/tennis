<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
    
    <%@include file="../checkLogin.jsp" %>


<%
     String[] players = request.getParameterValues("usersArray[]");
     ArrayList<User> usersToAdd = new ArrayList<User>();
     String tournamentName = request.getParameter("name");
     Tournament tournament = Database.getInstance().getTournamentByName(tournamentName);
   %><%=tournament.getName()%>
<%
  if (players != null) {
    for (String player : players) { 
      String playerName = player.replaceAll("/", "");
      usersToAdd.add(Database.getInstance().getUserByUsername(playerName));
    }
  }
Database.getInstance().addParticipantsToTournament(tournament, usersToAdd);
%>
<%@include file="viewTournaments.jsp" %>  


