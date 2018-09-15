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

function validateForm()
{
    //part1 Daniel
    
    
    //part2 Daniel
    
    
    
    //part3 Kenn
    function validateForm3()
    {
        var valid = true;                                                                                    //default value true before test loop
        var formInputs = document.getElementsByClassName("FormInput");                                       //get an array of all form inputs
        var i;           //iterator                                                           
        var errorDiv = document.getElementById("errorMessage");                                              //get errorMessage div object
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
                    errorDiv.innerHTML = "<p>**INPUT HAS INVALID CHARACTERS**</p>";     //add html to div
                    errorDiv.className = "divVisible";                                  //set div message to visible
                    formInputs[i].style.backgroundColor = "yellow";                     //change background of text box to yellow
                    
                    return valid;
                }
            
            }
            
            
        }
        
        return valid;   //should only happen when valid = true, meaning no elements contain '
        
    }
    return validateForm3();
    
    
    
    //part4 Kenn
    
    
    
}
