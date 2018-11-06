/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Twit;
import business.User;
import dataaccess.TwitDB;
import dataaccess.UserDB;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import murach.util.CookieUtil;


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

   
        String url = "/signup.jsp";
        String action = request.getParameter("action");


        boolean condition = false;
       
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

                    url = "/signup.jsp";
                    request.setAttribute("user", user);

                    request.setAttribute("condition3", condition);        //set confirm password doesn't match condition

                }

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
        if(action.equals("update"))
        {
            User user = new User();
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String birthDate = request.getParameter("birthDate");      
            String questionNo = request.getParameter("questionNo");
            String answer = request.getParameter("answer");
            url = "/home.jsp";                  //if successful, go to home.jsp
            int result = 0;
            User userCheck = new User();
            userCheck = UserDB.search(email);
            String[] whichEmptyInput = {"Full Name", "User Name", "Email", "Password", "Confirm Password", "Birth Date", "Security Question", "Response" };

            String[] userDetails;
            userDetails = new String[] {fullName, userName, email, password, confirmPassword, birthDate, questionNo, answer};
            ArrayList<String> emptyInputList = new ArrayList<String>();
            //check for empty input
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
                    url = "/update.jsp";
                }
            }
            if(!password.equals(confirmPassword))
            {
                condition = true;
                
                url = "/update.jsp";
                request.setAttribute("user", user);
                
                request.setAttribute("condition3", condition);        //set confirm password doesn't match condition
                
            }
            if(userCheck != null)
            {
                user.setFullName(fullName);
                user.setUserName(userName);
                user.setEmail(email);
                user.setPassword(password);
                user.setConfirmPassword(confirmPassword);
                user.setBirthDate(birthDate);
                user.setQuestionNo(questionNo);
                user.setAnswer(answer);
                result = UserDB.update(user);
                if(result != 0)
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    request.setAttribute("user", user);
                }
                else            //need to add failed update code
                {
                    url = "/update.jsp";
                }
                
            }
            
        }
        if(action.equals("login"))
        {
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           String rememberMe = request.getParameter("check[0]");
           User userCheck = UserDB.search(email);
           HttpSession session = request.getSession();
           session.setAttribute("user", null);
           ArrayList<Twit> twits= new ArrayList<Twit>();
           twits = TwitDB.getUserTwits(userCheck);
           
           condition = false;
           url = "/login.jsp";
           if(userCheck != null && userCheck.getEmail().equals(email) && userCheck.getPassword().equals(password))
           {

                   if(rememberMe != null)
                    {
                        Cookie c = new Cookie("newEmailCookie", email);
                        c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                        c.setPath("/");                      // allow entire app to access it
                        response.addCookie(c);
                        Cookie c2 = new Cookie("passwordCookie", password);
                        c2.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                        c2.setPath("/");                      // allow entire app to access it
                        response.addCookie(c2);
                        Cookie c3 = new Cookie("rememberMeCookie", rememberMe);
                        c3.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                        c3.setPath("/");                      // allow entire app to access it
                        response.addCookie(c3);
                    }
                   
                  
                  request.setAttribute("user", userCheck);
                  request.setAttribute("twits", twits);
                  session.setAttribute("user", userCheck);
                  session.setAttribute("twits", twits);
                  request.setAttribute("twitNumber", twits.size());
                  session.setAttribute("twitNumber", twits.size());
                  url = "/home.jsp";


           }
           else
           {
                  condition = true;
                  request.setAttribute("email", email);
                  request.setAttribute("password", password);
                  request.setAttribute("conditionLogin", condition);
           }


        }        //end login
       
       if(action.equals("signout"))
       {
            Cookie[] cookies = request.getCookies();
            HttpSession session = request.getSession();
            String newEmailAddress = CookieUtil.getCookieValue(cookies, "newEmailCookie");
            String password = CookieUtil.getCookieValue(cookies, "passwordCookie");
            String rememberMe = CookieUtil.getCookieValue(cookies, "rememberMeCookie");
            session.removeAttribute("user");
            url = "/login.jsp";
            if(rememberMe.equals("true")){
            
                request.setAttribute("email", newEmailAddress);
                request.setAttribute("password", password);
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
