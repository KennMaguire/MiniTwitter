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
import java.util.regex.*;
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
        User user = (User) session.getAttribute("user");
        User foundUser = UserDB.search(user.getEmail());  //I'm not including the userID in the session object, so I;m getting it from the DB
        if(action.equals("postTwit"))
        {
            boolean succeed;
                      
            String userID = foundUser.getUserID();
            //getting the date
            java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            
           /* 
            Pattern p = Pattern.compile("([@][\\S]+)"); regex more complicated than haadi's solution
            Matcher m = p.matcher(twitPost);
            ArrayList<String> mentions = new ArrayList<String>();
            while(m.find())
            {
                mentions.add(m.group(1));
            }
*/
            
            int startInd = 0;
            ArrayList<User> users = new ArrayList<User>();
            String twitPost = request.getParameter("twit");
            String newTwit = twitPost;
            while(twitPost.indexOf("@", startInd) != -1)
            {
                User mentionUser = new User();
                int indexOf = twitPost.indexOf("@", startInd);
                int indexOfLength = twitPost.indexOf(" ", indexOf+1);
                if(indexOfLength == -1)
                {
                    indexOfLength = twitPost.length();
                }
                if((indexOfLength-indexOf) != 1){
                String mention = twitPost.substring(indexOf,indexOfLength);
                mentionUser = UserDB.searchByUN(twitPost.substring(indexOf+1,indexOfLength));
                    if(mentionUser != null)
                    {
                        users.add(mentionUser);
                    }
                newTwit = newTwit.replace(mention, "<a class='blueX'> " + mention + "</a>");
                }
                startInd = indexOf+1;
            }
            Twit twit = new Twit();
            twit.setUserID(userID);
            twit.setTwit(newTwit);
            twit.setTwitDate(currentTime);
            succeed = TwitDB.insert(twit);
            int i = 0;
            while(i < users.size())
            {
                Twit mentionTwit = new Twit();
                User mentionUser = new User();
                mentionUser = users.get(i);
                mentionTwit.setUserID(mentionUser.getUserID());
                mentionTwit.setTwit(newTwit);
                mentionTwit.setTwitDate(currentTime);
                succeed = TwitDB.insert(mentionTwit);
                i++;
            }
            
            ArrayList<Twit> twits= new ArrayList<Twit>();
            twits = TwitDB.getUserTwits(foundUser);
            request.setAttribute("twitNumber", twits.size());
            session.setAttribute("twitNumber", twits.size());
            request.setAttribute("twits", twits);
            session.setAttribute("twits", twits);
            
        }
        if(action.equals("deleteTwit"))
        {
            Twit twit = new Twit();
           // twit.setTwit(request.getParameter("twit"));
           // twit.setTwitDate(request.getParameter("twitDate"));
            twit.setTwitID(request.getParameter("twitID"));
            twit = TwitDB.getTwitByID(twit.getTwitID());
           // twit.setUserID(request.getParameter("userID"));
            TwitDB.delete(twit);
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
