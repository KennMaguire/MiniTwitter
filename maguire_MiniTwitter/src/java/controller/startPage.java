/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.util.CookieUtil;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;  
import java.util.Date;  
/**
 *
 * @author kennethmaguire
 */
public class startPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL-dd-yyyy");
        String currentDate = localDate.format(formatter);
        
        String url = "";
        String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
        String newEmailAddress = CookieUtil.getCookieValue(cookies, "newEmailCookie");
        String password = CookieUtil.getCookieValue(cookies, "passwordCookie");
        String rememberMe = CookieUtil.getCookieValue(cookies, "rememberMeCookie");
        
         if (emailAddress == null || emailAddress.equals("")) {
                url = "/signup.jsp";
         } 
         else{
             url = "/login.jsp";
         }
            
        
        request.setAttribute("currentDate", currentDate);           //not useful since getting current date through jstl format
        
        if(rememberMe.equals("true")){
            
            request.setAttribute("email", newEmailAddress);
            request.setAttribute("password", password);
        }
    //    request.setAttribute("rememberMe", rememberMe);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
        
        
        
    }

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
