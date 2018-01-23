<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.ssn.tennis.common.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.ssn.core.*"%>

<%@include file="../checkLogin.jsp"%>

<%
  String tournamentN = request.getParameter("name");
  Tournament tour = ApplicationFactory.getInstance().getDatabase().getTournamentByName(tournamentN);
  List<Match> currentMatches = tour.getMatches();
  for (int i = 1; i <= currentMatches.size(); i++) {
    String matchNumber = request.getParameter("id" + i);
    String score1 = request.getParameter("sc1_" + i);
    int sc1=Integer.valueOf(score1 != null && score1.length()>0 ? score1 : "0");
    String score2 = request.getParameter("sc2_" + i);
    int sc2=Integer.valueOf(score2 != null && score2.length()>0 ? score2 : "0");
    Match match = currentMatches.get(i - 1);
    ApplicationFactory.getInstance().getDatabase().addMatchScore(match, sc1, sc2);
  }
%> 

<%@include file="viewTournamentForm.jsp"%>
