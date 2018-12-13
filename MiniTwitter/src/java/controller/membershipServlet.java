/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Follow;
import business.Hashtag;
import business.Notifications;
import business.Twit;
import business.User;
import business.UserTwit;
import dataaccess.FollowDB;
import dataaccess.HashtagDB;
import dataaccess.TwitDB;
import dataaccess.UserDB;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import murach.util.CookieUtil;
import murach.util.MailUtilGmail;
import util.HashPasswordUtil;


class SortByDateNotifications implements Comparator<Notifications>
    {
        @Override
        public int compare(Notifications a, Notifications b)
        {
            return b.getNotifyDate().compareTo(a.getNotifyDate());
        }
        
    }

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
          //will hash password for user before adding to DB
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
                    String saltAndHashedPassword = "";      
                    String salt = HashPasswordUtil.getSalt();       //get salt value
                    
                    
                    try{
                    saltAndHashedPassword = HashPasswordUtil.hashPassword(password + salt); //use salt plus password to get hashed value in DB
                    user.setPassword(saltAndHashedPassword);        //set hashed password for password to be stored in DB
                    user.setSalt(salt); //set salt for adding to db
                    }
                    catch(NoSuchAlgorithmException ex)
                    {
                        saltAndHashedPassword = ex.getMessage();
                    }
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
                //setting password after hashing
                user.setConfirmPassword(confirmPassword);
                user.setBirthDate(birthDate);
                user.setQuestionNo(questionNo);
                user.setAnswer(answer);
                String saltAndHashedPassword = "";      
                String salt = HashPasswordUtil.getSalt();       //get salt value
                
                
                try{
                    saltAndHashedPassword = HashPasswordUtil.hashPassword(password + salt); //use salt plus password to get hashed value in DB
                    user.setPassword(saltAndHashedPassword);        //set hashed password for password to be stored in DB
                    user.setSalt(salt); //set salt for adding to db
                }
                catch(NoSuchAlgorithmException ex)
                {
                    saltAndHashedPassword = ex.getMessage();
                }
                
                
                
                result = UserDB.update(user);           //update User
                if(result != 0)     //
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
           ArrayList<Twit> twits= new ArrayList<Twit>();            //holds twits for user
           ArrayList<User> users = new ArrayList<User>();           //holds users for people you may know
           ArrayList<Hashtag> topHashtags = new ArrayList<Hashtag>();
           
           condition = false;
           url = "/login.jsp";
           String saltAndHashedPassword = "";
           
           if(userCheck != null)        //check if user is null, if not, salt and hash password to confirm
           {
               String salt = userCheck.getSalt();
                try{
                    
                    saltAndHashedPassword = HashPasswordUtil.hashPassword(password + salt); //use salt plus password to get hashed value in DB        
                }
                 catch(NoSuchAlgorithmException ex)
                {
                    saltAndHashedPassword = ex.getMessage();
                }
           }
           //need to check if user is null, email is the same email, and the hash value matches the one stored in the DB for password
           if(userCheck != null && userCheck.getEmail().equals(email) && userCheck.getPassword().equals(saltAndHashedPassword)) 
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
                   
                   
                  //getLastLogin from before
                  //set new lastLogin
                  String lastLogin = UserDB.getLastLogin(userCheck);
                  
                  
                  ArrayList<Twit> notifyTwits = new ArrayList<Twit>();
                  ArrayList<Follow> notifyFollow = new ArrayList<Follow>();
                  ArrayList<User> followUsers = new ArrayList<User>();
                  notifyTwits = TwitDB.getMentionNotifications(lastLogin, userCheck.getUserName());
                  
                  
                  
                  //get user name along with twit so they know hwo posted it
                  notifyFollow = FollowDB.getFollowNotifications(lastLogin, userCheck.getUserID());
                  
                  ArrayList<Notifications> notifications = new ArrayList<Notifications>();
                  
                  for(int i=0; i < notifyFollow.size(); i++)        //add un and follows to notifications for extra credit
                  {
                      Notifications notification = new Notifications();
                      User followUser = new User();
                      Follow follow = new Follow();
                      followUser = UserDB.searchByID(notifyFollow.get(i).getUserID());
                      notification.setUserName(followUser.getUserName());
                      follow = notifyFollow.get(i);
                      notification.setNotifyDate(follow.getDateFollowed());
                      notification.setFollow(follow);
                      notifications.add(notification);
                      /*
                      User followUser = new User();
                      followUser = UserDB.searchByID(notifyFollow.get(i).getUserID());
                      followUsers.add(followUser);
                      */
                  }
                  for(int i=0; i < notifyTwits.size(); i++)
                  {
                      Notifications notification = new Notifications();             //use notifications class for sorting later
                      UserTwit userTwit = new UserTwit();
                      Twit twit = new Twit();
                      twit = notifyTwits.get(i);
                      User twitUser = new User();
                      twitUser = UserDB.searchByID(twit.getUserID());
                      userTwit.setFullName(twitUser.getFullName());
                      userTwit.setUserID(twitUser.getUserID());
                      userTwit.setUserName(twitUser.getUserName());
                      userTwit.setTwit(twit.getTwit());
                      userTwit.setTwitDate(twit.getTwitDate());
                      userTwit.setTwitID(twit.getTwitID());
                      
                      notification.setUserTwit(userTwit);
                      notification.setNotifyDate(userTwit.getTwitDate());
                      notifications.add(notification);
                      
                      
                  }
                  Collections.sort(notifications, new SortByDateNotifications());       //sort before setting for request
                  session.setAttribute("notifications", notifications);
                  
                  //session.setAttribute("notifyTwits", notifyTwits);
                  //session.setAttribute("notifyFollows", followUsers);
                  
                  
                  //getting the date
                  java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime
                  
                  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  String currentTime = sdf.format(dt); 
                  
                  userCheck.setLastLogin(currentTime);
                  UserDB.changeLastLogin(userCheck);
                  
                  
                  
                  twits = TwitDB.getAllTwits(userCheck);
                  
                  ArrayList<UserTwit> userTwits = new ArrayList<UserTwit>();
                  int total = 0;
                  for(int i=0; i<twits.size(); i++)
                  { 
                      UserTwit userTwit = new UserTwit();       //get all the twits in the format of userTwit
                      User user = new User();
                      
                      user = UserDB.searchByID(twits.get(i).getUserID());
                      
                      if(user.getUserID().equals(userCheck.getUserID()))
                      {
                          total += 1;
                      }
                      //using UserTwit class so the correct username and fullname is displayed
                      userTwit.setFullName(user.getFullName());
                      userTwit.setUserID(user.getUserID());
                      userTwit.setUserName(user.getUserName());
                      userTwit.setTwit(twits.get(i).getTwit());
                      userTwit.setTwitDate(twits.get(i).getTwitDate());
                      userTwit.setTwitID(twits.get(i).getTwitID());  
                      userTwits.add(userTwit);
                 
                  }
                  
                  topHashtags = HashtagDB.getTopHashtags();
                  users = UserDB.getAllUsers();
                  Follow follow = new Follow();
                  int numFollowed = 0;
                  int numFollowing = 0;
                  users.remove(userCheck);
                  numFollowing = follow.whichUsersFollowing(users, userCheck);
                  numFollowed = follow.whichUsersFollowed(users, userCheck);
                  
                  //set all request and session attribute necessary for home.jsp
                  request.setAttribute("topHashtags", topHashtags);
                  session.setAttribute("topHashtags", topHashtags);
                  request.setAttribute("numFollowed", numFollowed);
                  session.setAttribute("numFollowed", numFollowed);
                  request.setAttribute("numFollowing", numFollowing);
                  session.setAttribute("numFollowing", numFollowing);
                  session.setAttribute("users", users);
                  request.setAttribute("users", users);
                  request.setAttribute("user", userCheck);
                  session.setAttribute("user", userCheck);
                  request.setAttribute("twits", userTwits);
                  session.setAttribute("twits", userTwits);
                  request.setAttribute("twitNumber", total);
                  session.setAttribute("twitNumber", total);
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
       if(action.equals("forgotPW"))
       {
            
            url = "/login.jsp";
            String email = request.getParameter("email");
            String questionNo = request.getParameter("questionNo");
            String answer = request.getParameter("answer");
            User userCheck = UserDB.search(email);
            if(userCheck != null)
            {
                if(userCheck.getEmail().equals(email) && userCheck.getQuestionNo().equals(questionNo) && userCheck.getAnswer().equals(answer))
                {
                                                   //get random password, update in DB, send email, and pass "Email Sent" to login
                    String RandChars= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&";
                    StringBuilder randPW = new StringBuilder();
                    Random rand = new Random();
                    while(randPW.length() < 9)
                    {
                        int index = (int) (rand.nextFloat() * RandChars.length());
                        randPW.append(RandChars.charAt(index));
                    }
                    String tempPW = randPW.toString();          //set random String to tempPW
                    
                    
                    String saltAndHashedPassword = "";      
                    String salt = HashPasswordUtil.getSalt();       //get salt value
                    
                    
                    try{
                    saltAndHashedPassword = HashPasswordUtil.hashPassword(tempPW + salt); //use salt plus password to get hashed value in DB
                    userCheck.setPassword(saltAndHashedPassword);        //set hashed password for password to be stored in DB
                    userCheck.setSalt(salt); //set salt for adding to db
                    }
                    catch(NoSuchAlgorithmException ex)
                    {
                        saltAndHashedPassword = ex.getMessage();
                    }
                    UserDB.update(userCheck);                   //update password to random string
                    String to = email;
                    String from = "kennethmaguire@webApp.com";
                    String subject = "Temporary Password";
                    String body = "Your tempory password is: " + tempPW 
                            + "\nPlease update this password immediately after logging in.";
                    
                    boolean isBodyHTML = false;
                    try
                    {
                        MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
                        condition = true;
                        request.setAttribute("conditionSuccess", condition);
                    }
                    catch (MessagingException e)
                    {
                        String errorMessage =
                                "ERROR: Unable to send email. " +
                                "Check Tomcat logs for details.<br>" +
                                "NOTE: You may need to configure your system " +
                                "as described in chapter 14.<br>" +
                                "ERROR MESSAGE: " + e.getMessage();
                        request.setAttribute("errorMessage", errorMessage);
                        this.log(
                                "Unable to send email. \n" +
                                        "Here is the email you tried to send: \n" +
                                        "=====================================\n" +
                                        "TO: " + email + "\n" +
                                                "FROM: " + from + "\n" +
                                                        "SUBJECT: " + subject + "\n" +
                                                                "\n" +
                                        body + "\n\n");
                    } 
                
                }
                else
                {
                    condition = true;
                    request.setAttribute("conditionFail", condition);
                    url = "/forgotPassword.jsp";
                                                   //pass "information incorrect to forgotPassword
                }
            }
            else
            {
                condition = true;
                request.setAttribute("conditionFail", condition);
                url = "/forgotPassword.jsp";
                //pass "information incorrect to forgotPassword
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
