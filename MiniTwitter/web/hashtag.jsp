<%-- 
    Document   : hashtag
    Created on : Dec 3, 2018, 4:36:04 PM
    Author     : kennethmaguire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<c:import url="header.jsp" />
<c:import url="footer.jsp" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="scripts/main.js" type="text/javascript"> </script>
        <title>MiniTwitter</title>
    </head>
    <body>
        <c:forEach var="tweet" items="${userTwits}" varStatus="status">
            <div id='MiddleTopPosts'>
                
                <div id="twitPosts">
                    <h3 id="twit"><c:out value="${tweet.fullName}" /></h3>
                    <h3 id="twit">@<c:out value="${tweet.userName}" />: <p id="twitDate"> <c:out value="${tweet.twitDate}" /> </p></h3> 
                    <p id="twit">${tweet.twit}</p>
                </div>
                    
            </div> 
            
            
        </c:forEach>
    </body>
</html>
