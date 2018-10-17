

   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
      <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<html>
   
    <body>
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="todaysDate" value="${now}" pattern="MM-dd-yyyy" />
        <p id="bottomMiddle">  <c:out value="${todaysDate}" />  <br> &copy; Kenneth Maguire  </p>
    </body>
</html>
