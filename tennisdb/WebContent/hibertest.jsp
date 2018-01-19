<%@page import="com.ssn.persistence.SessionFactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="org.hibernate.*, org.hibernate.dialect.*, org.hibernate.cfg.*, com.ssn.*, java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Insert title here</title>
</head>
<body>
<%
SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

Session sessionx = sessionFactory.openSession();
Transaction tx = sessionx.beginTransaction();
Message message = new Message("Hello World"); 
sessionx.save(message);
Message message2 = new Message("Hello World2 ");
sessionx.save(message2);
tx.commit();
sessionx.close();
%>
</body>
</html>