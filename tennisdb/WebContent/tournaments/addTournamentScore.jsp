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
    String score2 = request.getParameter("sc2_" + i);
    Match match = currentMatches.get(i - 1);
    match.setPoints1(Integer.valueOf(score1 != null && score1.length()>0 ? score1 : "0"));
    match.setPoints2(Integer.valueOf(score2 != null && score2.length()>0 ? score2 : "0"));

  }
%> 

<%@include file="viewTournamentForm.jsp"%>
