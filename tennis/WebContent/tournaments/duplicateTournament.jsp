<%@page import="com.ssn.tennis.model.format.TournamentFormats"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    <%@page import="com.ssn.tennis.model.enums.*" %>
        <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
  Tournament tour = Database.getInstance().getTournamentByName(request.getParameter("name"));
String oldName=tour.getName();
String dupName="duplicate";
String suffix=""; 
if(oldName.contains(dupName)){
  if(oldName.endsWith(dupName)){
    suffix=oldName+"1";    
  }else{
      int index=oldName.indexOf(dupName)+dupName.length();
      int dupNumber=1;
      try{
        dupNumber=Integer.parseInt(oldName.substring(index, oldName.length()));
      }catch(Exception e){
        %>
        <script language="javascript">
        alert( "You troll, stop using the \"duplicate\" key word in tour names..." );
        </script>
        <% 
        oldName="***"+oldName;
        tour.setName("***");
      }
      dupNumber++;
      suffix=oldName.substring(0, index)+dupNumber;
    }
  
  }else{
    suffix=oldName+dupName;
  }
 String name=suffix;
 SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy-HH:mm ");
 String dateS=dateFormat.format(new Date(System.currentTimeMillis()));
 Date date=dateFormat.parse(dateS);
 TournamentType type = tour.getType();
 String tourFormat=tour.getFormat().getName();
Database.getInstance().addTournament(name, date, type, tourFormat);
%>

<%@include file="viewTournaments.jsp" %> 
