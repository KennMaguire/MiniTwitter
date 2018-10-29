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
             <meta charset="UTF-8">
             <meta name="viewport" content="width=device-width, initial-scale=1.0">
             <script src="scripts/main.js" type="text/javascript"> </script>
             <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
          <c:if test="${sessionScope.user != null}">
               
               <button class="tab_button" onclick="redirectToUrl('signup.jsp')">Profile</button>
                <button class="tab_button" onclick="redirectToUrl('notification.jsp')">Notification</button>
                <button class="tab_button" onclick="redirectToUrl('home.jsp')">Home</button>
                
               
                <form action="membership" method="post"> 
                    <input type='hidden' name='action' value='signout' >
                    <button class='signout'>Sign out></button>
              
                </form> 
                <p>&nbsp;</p>
               
                <hr>
               
          </c:if>
           
    </body>
</html>
