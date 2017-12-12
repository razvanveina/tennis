<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ssn.tennis.common.*"%>
<%    
  String relLF = Utils.getParentsFromServletPath(request.getServletPath());
%>  
<FORM action="<%=relLF %>login.jsp" method="POST">
User: <INPUT type="text" name="user"/><BR/>
Password: <INPUT type="password" name="pass"/>
<INPUT type="submit" value="Login"/>
</FORM>