<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : xl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="header.jsp" />
<c:import url="footer.jsp" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiniTwitter</title>
    </head>
    <body>
         <c:if test="${sessionScope.user == null}">
            <c:redirect url = "/login.jsp"/>
        </c:if>
        <p> 
            Welcome to your homepage <c:out value="${user.userName}" /> 
        </p>
    </body>
</html>
