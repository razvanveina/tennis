<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*"%>
    <link rel="stylesheet" type="text/css" href="../css/style.css"> 
<%    
  String relLF = Utils.getParentsFromServletPath(request.getServletPath());
%>  
<div class="clearfix">
<div class="addBox1">
<strong>Login</strong>
User: <br>
Password: <br>
</div>
<div class="addBox1">
<strong> &nbsp</strong>
<FORM action="<%=relLF %>login.jsp" method="POST">
<INPUT type="text" name="user"/><BR/>
<INPUT type="password" name="pass"/><br>
<br>
<INPUT type="submit" value="Login"/>
</FORM>
</div>
</div>