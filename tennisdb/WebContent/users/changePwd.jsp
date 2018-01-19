<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ssn.tennis.common.*" %>
    
    <%@include file="../checkLogin.jsp" %>
    
<% 
  String oldUser = request.getParameter("olduser");
	String oldPass = request.getParameter("oldpass");
  String newPass = request.getParameter("newpass");
	
  boolean changed=Database.getInstance().changePassword(oldUser, oldPass, newPass);  
if (changed){
%>
<script>
alert("Password changed successfully.");
</script>
 <BR/>
<%
}else{
%>
<script>
alert("Invalid password");
</script>
<%} %>
<%@include file="changePwdForm.jsp" %>
