<%-- 
    Document   : notification.jsp
    Created on : Nov 30, 2018, 1:42:20 PM
    Author     : kennethmaguire
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="scripts/main.js" type="text/javascript"> </script>
        <title>MiniTwitter</title>
    </head>
    <body>
         <div id="TopRightUsers">
           <h3> Who to Follow </h3>
        <c:forEach var="users" items="${users}" >
        
            
        <div id="userRecommend">
                    <h3 id="twit"><c:out value="${users.fullName}" /></h3>
                    <p id="altUN">@<c:out value="${users.userName}" /></p> 
               
                    <c:choose>
                        <c:when test="${users.followed}">
                            <a href="userPage?action=unfollow&amp;followUserID='${users.userID}'" ><button>Unfollow</button></a>
                        </c:when>
                        <c:otherwise>
                            <!--<input type="hidden" name="action" value="follow">-->
                            <a href="userPage?action=follow&amp;followUserID='${users.userID}'" ><button>Follow</button></a>
                        </c:otherwise>
                        
                    </c:choose>
                    
        </div>
           
        </c:forEach>        
        </div>  
        
        
        
         <div id='pagewrapTopLeft'>
        
          <h2> 
            <c:out value="${user.fullName}" /> 
          
        </h2>
            <p id="username">
               @<c:out value="${user.userName}" /> 
            </p>
            
            <h3 id="nums">
                Twits: <c:out value="${twitNumber}" />
            </h3>
            <h3 id="nums">
                Following: <c:out value="${numFollowed}" />
            </h3>
            <h3 id="nums">
                Followed By: <c:out value="${numFollowing}" />
            </h3>
      </div>
      
      <div id='pagewrapBottomLeft'>
            <h2>Trending Hashtags</h2>
            <table>
                
                <c:forEach var="hashtag" items="${topHashtags}">
                <tr>
                        
                    <td id="trends"> <a href='userPage?action=getHashtags&amp;hashtagID=${hashtag.hashtagID}'><c:out value="${hashtag.hashtagText}"/></a> </td>
                    <td id="trends"><c:out value="${hashtag.hashtagCount}"/> </td>
                    
                </c:forEach>
                </tr>
            </table>    
      </div>
    
    <div id='MiddleTopPosts'>
        <h1> Notifications </h1>
        <c:forEach var="notification" items="${notifications}">
            
            <c:if test="${!empty notification.twit}">
                <h3>Tweet</h3>
            <div id='twitPosts'>
                
                <h3 id="twit"><c:out value="${user.fullName}" /></h3>
                <h3 id="twit">@<c:out value="${user.userName}" />: <p id="twitDate"> <c:out value="${notification.twit.twitDate}" /> </p></h3>
                <p id="twit">${notification.twit.twit}</p>
           
            </div>
            </c:if>
           
            <c:if test="${!empty notification.follow}">
                <h3>Follow</h3>
                <div id='twitPosts'>
                    <p>You were followed By: @<c:out value="${notification.userName}"/> <br/> 
                    <c:out value="${notification.notifyDate}"/></p>  
                </div>
           
            </c:if>
        </c:forEach>
    </div>   
     
    </body>
</html>
