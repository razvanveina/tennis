<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*, java.util.*, com.ssn.core.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View users</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<BODY> 
<%@include file="../checkLogin.jsp" %> 
<%@include file="../checkAdminRights.jsp" %> 

<%
List<User> users = ApplicationFactory.getInstance().getDatabase().getUsers(); 
for (User user : users) {
  user.updateRatings();
}
Collections.sort(users);
%>
<br>

<TABLE>
    <TR>
    <TH width="3"></TH>
      <TH>User</TH>
      <TH>Name</TH>
      <TH>Surname</TH>
      <TH>Rating</TH>
      <TH>Won</TH>
      <TH>Lost</TH>
      <TH>Stars</TH>
      <!-- <TH>Email</TH> -->
    </TR>

<%

int position=1;    
for (int i=0; i<users.size(); i++) {
  User user=users.get(i);
  int stars=user.getStars();//.getInstance().getDatabase().getUserStars(user);
  if(i>0 && users.get(i-1).getRating()>user.getRating()){
    position++;
  }
  %>

    <TR>
    <TD align="center"><strong><%=position %>.</strong></TD>
      <TD><A href="editUserForm.jsp?name=<%=user.getUser() %>"><%= user.getUser() %></A></TD>
      <TD><%= user.getName() %></TD>
      <TD><%= user.getSurname() %></TD>
      <TD><%= user.getRating() %></TD>
       
      <TD><%= user.getWon() %></TD>
      <TD><%= user.getLost() %></TD>
      
      <TD align="center">
      <% 
     while(stars>=10){
       %>
       <img src="../img/star10.png" width="20" height="20"> 
    <%
    stars=stars-10;
     }      
      for(int j=0; j<stars; j++){ %>
      <img src="../img/star.png" width="10" height="10">
      <%} %>
      </TD>
<%--       <TD><A href="mailto:<%= user.getEmail() %>"><%= user.getEmail() %></A></TD> --%>    
  
<% } %>

</TABLE>
 <br />
 <%if(isAdmin){ %>
<FORM action="addUserForm.jsp" method="POST">
<INPUT type="submit" value="Add user"/>
</FORM>
<%} %>

</BODY>
</HTML>     
