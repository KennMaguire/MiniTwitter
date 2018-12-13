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
         
        
          <div id="TopRightUsers">
           <h3> Who to Follow </h3>
        <c:forEach var="users" items="${users}" >
        
            
        <div id="userRecommend">
                    <h3 id="twit"><c:out value="${users.fullName}" /></h3>
                    <p id="altUN">@<c:out value="${users.userName}" /></p> 
               
                    <c:choose>
                        <c:when test="${users.followed}">
                            <a href="userPage?action=unfollow&amp;followUserID='${users.userID}'" ><button id="followButton">Unfollow</button><br/></a>
                        </c:when>
                        <c:otherwise>
                            <!--<input type="hidden" name="action" value="follow">-->
                            <a href="userPage?action=follow&amp;followUserID='${users.userID}'" ><button id="followButton">Follow</button><br/></a>
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
            <h2>Trending Topics</h2>
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
        <h1 id ="FormHead2"> Hashtags </h1>
        <c:forEach var="tweet" items="${userTwits}" varStatus="status">
           
                
                <div id="twitPosts">
                    <h3 id="twit"><c:out value="${tweet.fullName}" /></h3>
                    <h3 id="twit">@<c:out value="${tweet.userName}" />: <p id="twitDate"> <c:out value="${tweet.twitDate}" /> </p></h3> 
                    <p id="twit">${tweet.twit}</p>
                </div>
                    
            
            
            
        </c:forEach>
        </div> 
    </body>
</html>
