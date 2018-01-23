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
  Tournament tour = ApplicationFactory.getInstance().getDatabase().getTournamentByName(request.getParameter("name"));
String oldName=tour.getName();
String dupName="duplicate";
String suffix=oldName; 
int counter=0;
boolean nameValid=false;
while(!nameValid){      
  if(ApplicationFactory.getInstance().getDatabase().getTournamentByName(suffix)==null){
    nameValid=true;
    break;
  }
  else{
    oldName=suffix;
      counter++;
      suffix="";
    }
  if(oldName.contains(dupName)){
      int index=oldName.indexOf(dupName)+dupName.length();
      if(index==oldName.length()){
        suffix=oldName+(counter);
      }else{
      suffix=oldName.substring(0,index);
      suffix=suffix+(counter);
     }
  
    }else{
      suffix=oldName+dupName;
    }

}

 String name=suffix;
 SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy-HH:mm ");
 String dateS=dateFormat.format(new Date(System.currentTimeMillis()));
 Date date=dateFormat.parse(dateS);
 TournamentType type = tour.getType();
 String tourFormat=tour.getFormat().getName();
 ApplicationFactory.getInstance().getDatabase().addTournament(name, date, type, tourFormat);
Tournament tx=ApplicationFactory.getInstance().getDatabase().getTournamentByName(name);
tx.setParticipants(tour.getParticipants()); 

%>

<%@include file="viewTournaments.jsp" %> 
