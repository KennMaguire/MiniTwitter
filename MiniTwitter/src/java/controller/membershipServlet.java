/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import dataaccess.UserDB;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie.*;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.lang.Integer.parseInt;


@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    public String returnSignup(String url)
    {   

        url = "/signup.jsp";

        return url;

    }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
    //   String action = request.getParameter("action");
       String url = "/signup.jsp";
       String action = request.getParameter("action");
       HttpSession session = request.getSession();

       boolean condition = false;
    //   boolean questionNoCond = false;
  //     String[] questionText = {"your first pet","your first car", "your first school"};
       if(action.equals("signup"))
       {
          User user = new User(); 
          String fullName = request.getParameter("fullName");
          String userName = request.getParameter("userName");
          String email = request.getParameter("email");
          String password = request.getParameter("password");
          String confirmPassword = request.getParameter("confirmPassword");
          String birthDate = request.getParameter("birthDate");        //TODO Finish here
          String questionNo = request.getParameter("questionNo");
          String answer = request.getParameter("answer");
           
          
          User userCheck = new User();
          userCheck = UserDB.search(email);
         
          String[] userDetails;
          userDetails = new String[] {fullName, userName, email, password, confirmPassword, birthDate, questionNo, answer};
                                     //1        2         3      4         5                6          7           8
         
          user.setFullName(fullName);
          user.setUserName(userName);
          user.setEmail(email);
          user.setPassword(password);
          user.setConfirmPassword(confirmPassword);
          user.setBirthDate(birthDate);
          user.setQuestionNo(questionNo);
          user.setAnswer(answer);
          
         
       if(userCheck == null)         //before running other tests, make sure user doesn't exist                     
       {   
          String[] whichEmptyInput = {"Full Name", "User Name", "Email", "Password", "Confirm Password", "Birth Date", "Security Question", "Response" };  
    
         
          ArrayList<String> emptyInputList = new ArrayList<String>();
          
          for(int i = 0; i < userDetails.length; i++ )
          {
               if(userDetails[i].equals("") || userDetails[i].equals(" ") )
               {
                   condition = true;
            //       questionNoCond = true;
                   emptyInputList.add(whichEmptyInput[i]);
                   request.setAttribute("user", user);
                   request.setAttribute("condition2", condition);   //set blank value condition
            //       request.setAttribute("questionNoCond", questionNoCond);
              //      request.setAttribute("questionString", questionText[(parseInt(questionNo)-1)]);
                   request.setAttribute("emptyInputList", emptyInputList);
                   url = "/signup.jsp";
                  
 
               }
         
          }
     
          //if the input list is not empty, forward request/response
        
    
          if(!password.equals(confirmPassword))
          {
              condition = true;
        ///     questionNoCond = true;
              url = "/signup.jsp";
              request.setAttribute("user", user);
           //   request.setAttribute("questionNoCond", questionNoCond);
            //  request.setAttribute("questionString", questionText[(parseInt(questionNo)-1)]);
              request.setAttribute("condition3", condition);        //set confirm password doesn't match condition
              
              
          }
          /*
          if(condition == true)
          {
           getServletContext()
           .getRequestDispatcher(url)
           .forward(request, response);
          }
          
          */
          // store User object in request
        
        
          //if condition isn't ever found, add to DB
          if(condition != true)
          {
              
              
              
            Cookie c = new Cookie("emailCookie", email);
            c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
            c.setPath("/");                      // allow entire app to access it
            response.addCookie(c);
            
            request.setAttribute("user", user);
            url = "/home.jsp";
            UserDB.insert(user);
          }
       
       }  // end of add user if user does not exist
       else
       {
            condition = true;
            url = "/signup.jsp";  
            request.setAttribute("user", user);
    //        questionNoCond = true;
      //      request.setAttribute("questionNoCond", questionNoCond);
      //      request.setAttribute("questionString", questionText[(parseInt(questionNo)-1)]);
            if(userCheck.getEmail().equals(email)){
                request.setAttribute("condition1_1", condition);      //set user exists condition for email
            }       
            
            if(userCheck.getUserName().equals(userName)){
                request.setAttribute("condition1_2", condition);      //set user exists condition for username    
            }
       
            
       }
      }         //end signup
       if(action.equals("login"))
       {
           String forgotPassword = request.getParameter("forgotPassword");
           if(forgotPassword.equals("true"))
           {
               url = "forgotPassword.jsp";
           }
           
       }
       
       
  
       
             getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
       
   }
    @Override
    protected void doGet(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {
        doPost(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
