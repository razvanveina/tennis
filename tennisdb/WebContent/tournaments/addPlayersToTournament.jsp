<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>

<%
     String[] players = request.getParameterValues("usersArray[]");
     ArrayList<User> usersToAdd = new ArrayList<User>();
     String tournamentName = request.getParameter("name");
     Tournament tournament = ApplicationFactory.getInstance().getDatabase().getTournamentByName(tournamentName);
   %>
<%
  if (players != null) {
    for (String player : players) { 
      String playerName = player.replaceAll("/", "");
      usersToAdd.add(ApplicationFactory.getInstance().getDatabase().getUserByUsername(playerName));
    }
  }
ApplicationFactory.getInstance().getDatabase().addParticipantsToTournament(tournament, usersToAdd);
%>
<%@include file="viewTournaments.jsp" %>  


