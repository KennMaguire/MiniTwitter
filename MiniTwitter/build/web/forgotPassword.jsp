<%-- 
    Document   : forgotPassword
    Created on : Oct 16, 2018, 11:38:35 AM
    Author     : kennethmaguire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="scripts/main.js" type="text/javascript"> </script>
        <title>ForgotPassword</title>
    </head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
    <c:import url="header.jsp" />
    <c:import url="footer.jsp" />
    <body>
        
        <div id="pagewrapMiddle">
        
            
            <table class="SignupForm">
                <form action="membership" method="post">
                    <div id="javaErrorMessage" class="divVisible" > 
            
                        <c:if test="${conditionFail}">     
                            <p> Information Incorrect </p>
                        </c:if>
                    </div>
                    <div id="javaErrorMessage" class="divVisible" ></div>
                        <h2>Forgot password?</h2>
                        <p>Please enter the following to get a temporary code</p>
                        <input type="hidden" name="action" value="forgotPW">
                        <tr>
                            <td> <label class="FormElement">Email:</label> </td>
                            <td><input type="email" class="FormInput" name="email" value="<c:out value='${user.email}' />" ><span id="email_error" class="notVisible">*</span><br> </td>
                        </tr>
                        <tr>
                            <td> <label class="FormElement">Select a security question:</label> </td>
                            <td> <select name="questionNo" class="FormInput" onchange="showSecurityQuestionResponse()" required>
                                    <option id="notSelectable"  disabled selected value>Select Value</option>
                                    <option value="1">your first pet</option>
                                    <option value="2">your first car</option>
                                    <option value="3">your first school</option>
                                </select> <span id="questionNo_error" class="notVisible">*</span>
                            </td>
                        </tr>
                    
                        <tr>
                            <td> <label id="securityQuestionPrompt_id" class="notVisible">Please enter a response:</label> </td>
                            <td> <input type="text" class="notVisible" name="answer" id="securityQuestionAnswer_id" value="<c:out value='${user.answer}' />" ><span id="answer_error" class="notVisible">*</span><br> </td>
                        </tr>
                    
                        <tr>
                            <td> <input type="submit" class="FormInputSubmit" name="registerAcct" value="Submit"><br> </td>
                        </tr>
                </form>
            </table>
        </div>
    </body>
</html>
