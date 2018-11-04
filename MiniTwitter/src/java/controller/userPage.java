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
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kennethmaguire
 */
public class userPage extends HttpServlet {

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
        doPost(request,response);
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
        
        String url = "/home.jsp";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        if(action.equals("postTwit"))
        {
            boolean succeed;
            User user = (User) session.getAttribute("user");
            User foundUser = UserDB.search(user.getEmail());            //I'm not including the userID in the session object, so I;m getting it from the DB
            String userID = foundUser.getUserID();
            //getting the date
            java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
           
            Twit twit = new Twit();
            twit.setUserID(userID);
            twit.setTwit(request.getParameter("twit"));
            twit.setTwitDate(currentTime);
            succeed = TwitDB.insert(twit);
            
            
            ArrayList<Twit> twits= new ArrayList<Twit>();
            twits = TwitDB.getUserTwits(foundUser);
            request.setAttribute("twitNumber", twits.size());
            session.setAttribute("twitNumber", twits.size());
            request.setAttribute("twits", twits);
            session.setAttribute("twits", twits);
            
        }
        
        
          getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
        
        
    }

    

}
