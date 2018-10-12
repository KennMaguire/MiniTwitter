/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



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
           
          String[] userDetails;
          userDetails = new String[] {fullName, userName, email, password, confirmPassword, birthDate, questionNo, answer};
          
          user.setFullName(fullName);
          user.setUserName(userName);
          user.setEmail(email);
          user.setPassword(password);
          user.setConfirmPassword(confirmPassword);
          user.setBirthDate(birthDate);
          user.setQuestionNo(questionNo);
          user.setAnswer(answer);
          
          
          for(int i = 0; i < userDetails.length; i++ )
          {
               if(userDetails[i].equals("") || userDetails[i].equals(" ") )
               {

                   request.setAttribute("user", user);
                   url = returnSignup(url);
                   getServletContext()
                   .getRequestDispatcher(url)
                   .forward(request, response);
 
               }
          }
          url = "/home.jsp";
          
        
          
          // store User object in request
          request.setAttribute("user", user);
          
          UserDB.insert(user);
          
       
          
          
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
