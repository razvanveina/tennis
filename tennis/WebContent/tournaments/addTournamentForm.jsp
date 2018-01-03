<%@page import="com.ssn.tennis.model.enums.TournamentType"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormats"%>
<%@page import="com.ssn.tennis.model.format.TournamentFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Add tournament</title>
</head>
  <style type="text/css">
  .addBox1 { 
    float: left;
    padding: 15px;
    width:3%;
    line-height:160%;
    
  }
  .addBox2   {
  text-align: center;
  font-size: 30px;
  vertical-align: -50px;
   padding: 15px;
  float:left;
   width:10%;
  }

.clearfix::after {
    content:"";
    clear: both;
    display: table;
}
</style>
<body>
<%@include file="../checkLogin.jsp" %>
<%@include file="../checkAdminRights.jsp" %> 
<%@include file="../menu.jsp" %> 
<%SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy-HH:mm ");
String date=dateFormat.format(new Date(System.currentTimeMillis()));
Tournament t=null;
if(request.getParameter("name")!=null){
  t=Database.getInstance().getTournamentByName(request.getParameter("name")); 
}
boolean edit=t!=null;
%>

<FORM action="addTournament.jsp" method="POST">
<div class="clearfix">
<div class="addBox1">
Name:<br>
Date:<br>
Type:<br>
Format:<br>
</div>
<div class="addBox2">
 <INPUT type="text" name="name"/ value=<%=edit? t.getName() + " disabled":""%>><BR/>
 <INPUT type="text" name="date" value=<%=edit? dateFormat.format(t.getStartDate()):date%>/><BR/>
 <select name="type">
  <option value="DOUBLE" <%=(edit && t.getType()==TournamentType.DOUBLE)? "selected=\"selected\"":"" %>>DOUBLE</option>
  <option value="SINGLE" <%=(edit && t.getType()==TournamentType.SINGLE)? "selected=\"selected\"":"" %>>SINGLE</option>
</select><BR/>
 <select name="format">
<% for (TournamentFormat tf : new TournamentFormats().getFormats()) { %> 
  <option value="<%=tf.getName() %>" <%=(edit && t.getFormat().getName().equals(tf.getName()))? "selected=\"selected\"":"" %>><%=tf.getName() %></option>
  <% } %>
</select><BR/>
<br>
<INPUT type="hidden" name=edit value=<%=edit%>/>
<INPUT type="submit" value="Add"/>
</div>
</div>

</FORM>