<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>MiniTwitter</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="scripts/main.js" type="text/javascript"> </script>
       
    </head>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>

    <c:import url="header.jsp" />
    <c:import url="footer.jsp" />
    <body>
        <div id="pagewrapMiddle">
        <h1 id ="FormHead">Sign Up for MiniTwitter!</h1>
       
        <form action="membership" method="post" onsubmit="return validateForm()">  <!--Change Registration in this line to give another page submission -->
            <div id="errorMessage" class="notVisible"></div>
            <div id="javaErrorMessage" class="divVisible" > 
                <c:if test="${condition1_1}">
                    <p> User already exists for email address: <c:out value='${user.email}' /> </p>
                </c:if>
                <c:if test="${condition1_2}">
                    <p> User already exists for username: <c:out value='${user.userName}' /> </p>
                </c:if>
                <c:if test="${condition2}">
                    <c:forEach items="${emptyInputList}" var="element" >
                        <p> <c:out value="${element}" /> cannot be empty! </p>
                    </c:forEach>
                </c:if> 
                <c:if test="${condition3}">
                    <p> Password and ConfirmPassword do not match! </p>
                </c:if>
            </div>
            <input type="hidden" name="action" value="signup">
            <table class="SignupForm">
                <tr>
                    <td><label class="FormElement">Full Name:</label> </td>
                    <td><input type="text" class="FormInput" name="fullName" id="fullName_id" value="<c:out value='${user.fullName}'/>"> <span id="fullName_error" class="notVisible">*</span> <br></td>
                </tr>
                
                <tr>
                    <td> <label class="FormElement">Email:</label> </td>
                    <td><input type="email" class="FormInput" name="email" value="<c:out value='${user.email}' />" ><span id="email_error" class="notVisible">*</span><br> </td>
                </tr>
                
                <tr>
                    <td> <label class="FormElement">Username:</label> </td>
                    <td> <input type="text" class="FormInput" name="userName" value="<c:out value='${user.userName}' />" ><span id="userName_error" class="notVisible">*</span><br> </td>
                </tr>
                
                <tr>
                    <td> <label class="FormElement">Password:</label> </td>
                    <td> <input type="password" class="FormInput" name="password" id="password_id" value="<c:out value='${user.password}' />" ><span id="password_error" class="notVisible">*</span><br> </td>
                </tr>
                
                <tr>
                    <td> <label class="FormElement">Confirm Password:</label> </td>
                    <td> <input type="password" class="FormInput" name="confirmPassword" id="confirmPassword_id" value="<c:out value='${user.confirmPassword}' />" ><span id="confirmPassword_error" class="notVisible">*</span><br> </td>
                </tr>
            
                <tr>
                    <td> <label class="FormElement">Date of Birth:</label> </td>
                    <td> <input type="date" class="FormInput" name="birthDate" value="<c:out value='${user.birthDate}' />" ><span id="birthDate_error" class="notVisible">*</span><br> </td>
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
            </table>
        </form>
        </div>
    </body>
    
</html>
