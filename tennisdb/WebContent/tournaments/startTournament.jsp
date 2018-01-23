<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
        <%@page import="com.ssn.core.*"%>
    
    
    <%@include file="../checkLogin.jsp" %>
    
<%
String tournamentString=request.getParameter("name");
Tournament tour= ApplicationFactory.getInstance().getDatabase().getTournamentByName(tournamentString);
tour.start();
%>
<%@include file="viewTournamentForm.jsp" %>   


