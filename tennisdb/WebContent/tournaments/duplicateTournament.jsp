<%@page import="com.ssn.tennis.model.format.TournamentFormats"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="com.ssn.tennis.model.enums.*" %>
        <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
        <%@page import="com.ssn.core.*"%>
    
    
    <%@include file="../checkLogin.jsp" %>
    
<%
      ApplicationFactory.getInstance().getDatabase().duplicateTournament(request.getParameter("name")); 
      
    %>

<%@include file="viewTournaments.jsp" %> 
