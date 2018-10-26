<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
             <script src="scripts/main.js" type="text/javascript"> </script>
    </head>
    <body>
          <c:if test="${sessionScope.user != null}">
              <div id="topRight"><a href="login.jsp">Sign out></a></div>
              <div id="tab">
                <button  onclick="redirectToUrl('signup.jsp')">Profile</button>
                <button onclick="redirectToUrl('notification.jsp')">Notification</button>
                <button onclick="redirectToUrl('home.jsp')">Home</button>
              </div>
          </c:if>

    </body>
</html>
