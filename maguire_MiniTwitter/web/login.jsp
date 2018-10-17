<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : xl
--%>

   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
     
    
<c:remove var="user" scope="session" />    
<c:import url="header.jsp" />
<c:import url="footer.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MiniTwitter</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="scripts/main.js" type="text/javascript"> </script>
    </head>
    
  

    
    <body>
        <div id="pagewrap">
        <h2 id ="FormHead"> LOGIN </h2>
        
        <form action="membership" method="post">
           
            <div id="javaErrorMessage" class="divVisible" >  
            
            <c:if test="${conditionLogin}">     
                <p> Login Failed! </p>
            </c:if>
            </div>
            <input type="hidden" name="action" value="login">
            <table class="SignupForm">
                <tr>
                    <td> <label class="FormElement">Email:</label> </td>
                    <td><input type="email" class="FormInput" name="email" value="<c:out value="${email}" />" > </td>
                </tr>
                <tr> 
                    <td><label class="FormElement">Password:</label> </td>
                    <td> <input type="password" class="FormInput" name="password" id="password_id" value="<c:out value="${password}" />" > </td>
                </tr>  
                
                <tr>
                    
                        
                    <td> <input type="submit" class="FormInputSubmit" name="loginAcct" value="Submit"> </td>
                         
                    <td> <label class="FormElement">Remember Me <input type="checkbox" name="check[0]" value="true" /></label></td>
                    
                    
                </tr>
                <tr>
                    <td> <a class="FormElement" href="forgotPassword.jsp"> Forgot Password? </a></td>
                </tr>
                <tr>
                    <td><p class="FormElement"> New?  <a  href=<c:url value="signup.jsp"> </c:url> class="FormElement">  Sign up now > </a></p></td>
                </tr>
                
                
            </table>
            
          
                
                
                
        </form>
        </div>
    </body>

</html>
