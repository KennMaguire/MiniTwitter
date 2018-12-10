/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import business.Twit;
import business.User;
import business.Follow;
import business.Hashtag;
import business.TweetHashtags;
import business.UserTwit;
import dataaccess.FollowDB;
import dataaccess.HashtagDB;
import dataaccess.TwitDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author kennethmaguire
 */
//allows me to sort UserTwit objects
class SortByDateUserTwit implements Comparator<UserTwit> 
{ 
   
    public int compare(UserTwit a, UserTwit b) 
    { 
        return b.getTwitDate().compareTo(a.getTwitDate()); 
    } 
}
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
            
            String twitPost = request.getParameter("twit");
            String newTwit = twitPost;
            ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>();
            while(twitPost.indexOf("@", startInd) != -1)
            {
                
                int indexOf = twitPost.indexOf("@", startInd);              //get the indices of the mention
                int indexOfLength = twitPost.indexOf(" ", indexOf+1);      
                if(indexOfLength == -1)                                     
                {
                    indexOfLength = twitPost.length();                      
                }
                if((indexOfLength-indexOf) != 1){
                String mention = twitPost.substring(indexOf,indexOfLength);  
                newTwit = newTwit.replace(mention, "<a class='blueX'> " + mention + " </a>");       
                }
                startInd = indexOf+1;
            }
            
            while(twitPost.indexOf("#", startInd) != -1)
            {
                int indexOf = twitPost.indexOf("#", startInd);
                int indexOfLength = twitPost.indexOf(" ", indexOf+1);
                if(indexOfLength == -1)                                     
                {
                    indexOfLength = twitPost.length();                      
                }
                if((indexOfLength - indexOf) != 1){
                    String hashtagText = twitPost.substring(indexOf,indexOfLength);
                   
                    //check if hashtag exists. if it does, add 1 to count if not insert hashtag in hashtag table 
                    //either way, insert in tweetHashtag
                    Hashtag hashtag = new Hashtag();
                    String hashtagID = "0";
                    hashtag = HashtagDB.search(hashtagText);
                    if(hashtag != null)
                    {
                        int plusOne = Integer.parseInt(hashtag.getHashtagCount()) + 1;
                        hashtag.setHashtagCount(Integer.toString(plusOne));         //add one to count
                        hashtagID = hashtag.getHashtagID();         //need hashtagID to reference hashtag when going to hashtag.jsp
                        HashtagDB.addOne(hashtag);
                        
                    }
                    else
                    {
                        hashtag = new Hashtag();
                        hashtag.setHashtagCount("1");
                        hashtag.setHashtagText(hashtagText);
                        HashtagDB.insertHashtag(hashtag);
                        hashtag = HashtagDB.search(hashtagText);
                        hashtagID = hashtag.getHashtagID();         //need hashtagID to reference hashtag when going to hashtag.jsp
                    }
                    newTwit = newTwit.replace(hashtagText, "<a href='userPage?action=getHashtags&amp;hashtagID="+ hashtagID + "' class='bluex'> " + hashtagText + " </a> ");  //create link for hashtags
                    hashtags.add(hashtag);
                }
                startInd = indexOf + 1;
            }
            
            Twit twit = new Twit();
            twit.setUserID(userID);
            twit.setTwit(newTwit);
            twit.setTwitDate(currentTime);
            succeed = TwitDB.insert(twit);
            
            for(int i = 0; i < hashtags.size(); i++)
            {
                Hashtag hashtag = HashtagDB.search(hashtags.get(i).getHashtagText());
                String hashtagID = hashtag.getHashtagID();
                twit = TwitDB.getTwitByDate(twit.getTwitDate());
                String twitID = twit.getTwitID();
                HashtagDB.insertTweetHashtag(hashtagID, twitID);
                
            }
           
            
        }
        if(action.equals("deleteTwit"))
        {
            Twit twit = new Twit();
         
            
            twit.setTwitID(request.getParameter("twitID"));
            twit = TwitDB.getTwitByID(twit.getTwitID());
           //when deleting twit, we also need to delete all relevant tweethashtags, and decrement the count from hashtags
            ArrayList<TweetHashtags> tweetHashtags = HashtagDB.searchByTweetID(twit.getTwitID());        //remove hashtags associated with tweet
            if(!tweetHashtags.isEmpty())
            {    for(int i=0; i < tweetHashtags.size(); i++)
                {
                    Hashtag hashtag = new Hashtag();
                    hashtag = HashtagDB.searchByHashtagID(tweetHashtags.get(i).getHashtagID());     //get the hashtag for decrementing count
                    int minusOne = Integer.parseInt(hashtag.getHashtagCount()) - 1;
                    hashtag.setHashtagCount(Integer.toString(minusOne));
                    HashtagDB.minusOne(hashtag);
                }
            
                HashtagDB.delete(twit.getTwitID());            
            }
            TwitDB.delete(twit);        //always deletes tweet
           
            
        }
        if(action.equals("follow"))
        {
            
            Follow follow = new Follow();
            ArrayList<User> users = new ArrayList<User>();           //holds users for people you may know
            users = UserDB.getAllUsers();
            users.remove(foundUser);
            java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            
            
            String userID = foundUser.getUserID();
            
            follow.setUserID(userID);
            String followUserID = request.getParameter("followUserID");
            followUserID = followUserID.replace("'", "");
            follow.setFollowUserID(followUserID); 
            follow.setDateFollowed(currentTime);
            FollowDB.insert(follow);
            
            follow.whichUsersFollowed(users, foundUser);
            
            session.setAttribute("users",users); //have to set users again since follow is updated
            request.setAttribute("users",users);
            
            
        }
        if(action.equals("unfollow"))
        {
            Follow follow = new Follow();
            ArrayList<User> users = new ArrayList<User>();           //holds users for people you may know
            users = UserDB.getAllUsers();
            users.remove(foundUser);
            java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            
            
            String userID = foundUser.getUserID();
            
            follow.setUserID(userID);
            String followUserID = request.getParameter("followUserID");
            followUserID = followUserID.replace("'", "");
            follow.setFollowUserID(followUserID); 
            follow.setDateFollowed(currentTime);
            FollowDB.delete(follow);
            
            follow.whichUsersFollowed(users, foundUser);
            
            session.setAttribute("users",users); //have to set users again since follow is updated
            request.setAttribute("users",users);
           
        }
        if(action.equals("getHashtags"))  
        {
            //just realizing now that I could've saved a lot of the steps below by creating a view (joing twits on tweetHashtags where twitID = tweetID)...
            
            
            //still not getting the hashtag text, might need to create view so that I can all twits and hashtags
            String hashtagID = request.getParameter("hashtagID");       //get the hashtag ID
                                                                         
            url = "/hashtag.jsp";
            ArrayList<TweetHashtags> tweetHashtags = new ArrayList<TweetHashtags>();
            tweetHashtags = HashtagDB.getHashtagTwits(hashtagID);          //get list of twitIDs with including specified hashtag
            ArrayList<UserTwit> userTwits = new ArrayList<UserTwit>();
            for(int i = 0; i < tweetHashtags.size(); i++)                       
            {
                Twit twit = new Twit();
                twit = TwitDB.getTwitByID(tweetHashtags.get(i).getTweetID());
                String userID = twit.getUserID();
                User newUser = new User();
                newUser = UserDB.searchByID(userID);
                if(twit != null && newUser != null)
                {
                    UserTwit userTwit = new UserTwit();
                    userTwit.setUserID(newUser.getUserID());
                    userTwit.setFullName(newUser.getFullName());
                    userTwit.setUserName(newUser.getUserName());
                    userTwit.setTwitID(twit.getTwitID());
                    userTwit.setTwit(twit.getTwit());
                    userTwit.setTwitDate(twit.getTwitDate());
                    userTwits.add(userTwit);
                }
                    
            }
            
            Collections.sort(userTwits, new SortByDateUserTwit());
            request.setAttribute("userTwits", userTwits);
            
            
            
        }
        
         ArrayList<Twit> twits= new ArrayList<Twit>();
         twits = TwitDB.getUserTwits(foundUser);
         request.setAttribute("twitNumber", twits.size());
         session.setAttribute("twitNumber", twits.size());
         request.setAttribute("twits", twits);                  //gets all twits for user after each action
         session.setAttribute("twits", twits);
            
          getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
     
     
        
    }
    
    

}
