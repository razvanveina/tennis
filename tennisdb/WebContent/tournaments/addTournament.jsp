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
	String name = request.getParameter("name");
if(name==null){
  name=request.getParameter("tourName");
}
  String dateS = request.getParameter("date");
  TournamentType type = TournamentType.valueOf(request.getParameter("type"));
  String tourFormat = request.getParameter("format");
	boolean edit=(request.getParameter("edit").equals("true")? true:false);
  DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm", Locale.ENGLISH);
  Date date = format.parse(dateS);
  if(edit){
Database.getInstance().editTournament(name, date, type, tourFormat);
  }else{
  boolean isDuplicate=Database.getInstance().checkDuplicateTournament(name);
  if(!isDuplicate && !name.equals("")){
	  Database.getInstance().addTournament(name, date, type, tourFormat);  
  }else{
    %>
    <script language="javascript">
    alert( "The tournament you were trying to create is a duplicate. Please try another name or date" );
    </script>
    <% 
  }
	  }
  
%>

<%@include file="viewTournaments.jsp" %> 
