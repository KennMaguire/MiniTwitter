/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm()
{
    //part1 Daniel
    
    
    //part2 Daniel
    
    
    
    //part3 Kenn
    function validateForm3()
    {
        var valid = true;
        var formInputs = document.getElementsByClassName("FormInput");
        var i;
        var errorDiv = document.getElementById("errorMessage");
        for(i = 0; i < formInputs.length; i++)
        {
            var searchVal;
            if(formInputs[i].name !== "securityQuestion" && formInputs[i].name !== "dateOfBirth"){           //used this input statement for testing
            //use regex to check for '
            

            var searchStr = formInputs[i].value;
            var n = searchStr.search("'");
                if(n !== -1)
                {
                    valid = false;
                    errorDiv.innerHTML = "<p>**INPUT HAS INVALID CHARACTERS**</p>";
                    errorDiv.className = "visible";
                    return valid;
                }
            
            }
            
            
        }
        
        return valid;
        
    }
    return validateForm3();
    
    
    
    //part4 Kenn
    
    
    
}
