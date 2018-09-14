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
        var testP = document.getElementById("test");
        for(i = 0; i < formInputs.length; i++)
        {
            var searchVal;
            if(formInputs[i].type == "text"){           //used this input statement for testing
            
            //use regex to check for '
                    
            valid = false;
          
            testP.innerText = "testing worked";
            }
            
            
        }
        
        return valid;
        
    }
    return validateForm3();
    
    
    
    //part4 Kenn
    
    
    
}
