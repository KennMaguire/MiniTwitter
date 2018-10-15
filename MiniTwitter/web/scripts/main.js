/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function makeSpanErrorVisible(_inputName)               //This function makes span errors visible by using the name of the input, append _error to it, and 
{                                                       //setting the spanError to visible
     var spanErrorName = _inputName + "_error";
     var spanError = document.getElementById(spanErrorName);
     spanError.className = "spanVisible";
}


function makeDivErrorVisible(_errorHtml)
{
     
     var errorDiv = document.getElementById("errorMessage");    
     errorDiv.innerHTML = _errorHtml;     //add html to div
     errorDiv.className = "divVisible";                                  //set div message to visible
    
    
}
function resetFormForChecks()
{
      var formInputs = document.getElementsByClassName("FormInput");                                       //get an array of all form inputs
      var i;  
        
      for(i = 0; i < formInputs.length; i++)
      {
        var inputName = formInputs[i].name;
        resetDivAndSpan(inputName);
        formInputs[i].style.backgroundColor = "rgba(157, 212, 224, 0.38)";
        
      }
    
    
    
    
    
}
//This function can be used to reset the form after fixing errors before the next error test
function resetDivAndSpan(_inputName)
{
     var spanErrorName = _inputName + "_error";
     var spanError = document.getElementById(spanErrorName);
     spanError.className = "notVisible";
     var errorDiv = document.getElementById("errorMessage"); 
     errorDiv.innerHTML = " ";
     errorDiv.className = "notVisible";
}
function validateForm()
{
    /*
    //part1 Daniel
    function validateForm1(){
        var valid = true;
        
        var passwordObj = document.getElementById("password_id");
        var confirmPasswordObj = document.getElementById("confirmPassword_id");
        
        var password = passwordObj.value;
        var confirmPassword = confirmPasswordObj.value;
        
        resetFormForChecks();

        if (password === confirmPassword) {
            return valid;
        }
        valid = false;
        // code to print error
        
        var errorHtml = "<p>Error! Password and confirm password do not match!</p>";
        makeDivErrorVisible(errorHtml);
        makeSpanErrorVisible(passwordObj.name);
        makeSpanErrorVisible(confirmPasswordObj.name);
        passwordObj.style.backgroundColor = "yellow";
        confirmPasswordObj.style.backgroundColor = "yellow";
        return valid;    
    }
    if(validateForm1() !== true)          //if not valid, return false, if valid, don't return and continue to next function
    {
        return validateForm1();
    }
    */
    //part2 Daniel
    function validateForm2() {
        var valid = true;
        
        var fullNameObj = document.getElementById("fullName_id");
        var fullName = fullNameObj.value;
      
      
        resetFormForChecks();
        
        if (fullName.includes(" ")) {
            return valid;
        }
        valid = false;
        var errorHtml = "<p>Full name is not valid</p>";
        makeDivErrorVisible(errorHtml);
        makeSpanErrorVisible(fullNameObj.name);
        fullNameObj.style.backgroundColor = "yellow";
        return valid;
    }
    if(validateForm2() !== true)          //if not valid, return false, if valid, don't return and continue to next function
    {
        return validateForm2();
    }
    
    //part3 Kenn
    function validateForm3()
    {
        var valid = true;                                                                                    //default value true before test loop
        var formInputs = document.getElementsByClassName("FormInput");                                       //get an array of all form inputs
        var i;                                                                    
         
         //for the following: if valid is true, reset the form. This helps when someone has ' once or more, so that if they enter the
         //correct value, the form returns layout to normal before checking again, checking all other checks, or if they fix certain boxes but not others
        
        resetFormForChecks();
        
        
        //get errorMessage div object
        for(i = 0; i < formInputs.length; i++)
        {
            if(formInputs[i].name !== "securityQuestion" && formInputs[i].name !== "dateOfBirth"){           //if any type of text (including email)
            var searchStr = formInputs[i].value;                                                             
            var n = searchStr.search("'");                                                                   //find location of ', if none n = -1
                if(n !== -1)                                                                                 //if ' found, continue
                {   
                    var inputName = formInputs[i].name;
                    makeSpanErrorVisible(inputName);                                    //call function, span error should now be visible
                    valid = false;                                                      //make input invalid
                   
                    formInputs[i].style.backgroundColor = "yellow";                     //change background of text box to yellow
                    var errorHtml = "<p>Input has invalid characters!</p>";
                    makeDivErrorVisible(errorHtml);
                    
                }
            
            }
            
            
        }

        return valid;   //returns true or false, 
        
    }
    if(validateForm3() !== true)          //if not valid, return false, if valid, don't return and continue to next function
    {
        return validateForm3();
    }
    
    
    //part4 Kenn
    function validateForm4()
    {
        var valid = true;                                             //assume true
        
        var pwordObj = document.getElementById("password_id");      //returns password by id
        var pword = pwordObj.value;                                 //create var with password string val
        var lowerC = false;
        var upperC = false;
        var numC = false;
        resetFormForChecks();


        lowerC = /[a-z]+/.test(pword);              //regex for lowercase [a-z] + means one or more
        upperC = /[A-Z]+/.test(pword);              //regex for uppercase [A-Z]
        numC = /\d+/.test(pword);                   //regex for digit     \d
        
        
        
        if(lowerC === false || upperC === false || numC === false)
        {
            var errorHtml = "<p>Password must contain the following minimum chars: 1 lower case, 1 upper case, and 1 digit</p>";
            makeDivErrorVisible(errorHtml);
            var pwordName = pwordObj.name;
            makeSpanErrorVisible(pwordName);
            pwordObj.style.backgroundColor = "yellow"; 
            valid = false;               //set false if 
        }
        
        return valid;
        
    }
    return validateForm4();         //if it makes it to this point, it should always return either true or false
    
}

function showSecurityQuestionResponse() {
    var prompt = document.getElementById("securityQuestionPrompt_id");
    var answer = document.getElementById("securityQuestionAnswer_id");
    
    prompt.className = "FormElement";
    answer.className = "FormInput";
}
