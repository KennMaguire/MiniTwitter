/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Follow;
import business.Twit;
import business.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import util.DBUtil;

/**
 *
 * @author kennethmaguire
 */
class SortByDateTwit implements Comparator<Twit> 
{ 
   
    public int compare(Twit a, Twit b) 
    { 
        return b.getTwitDate().compareTo(a.getTwitDate()); 
    } 
}

public class TwitDB {
    public static boolean insert(Twit twit) throws IOException,
            ServletException
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        
        
        
        try{
            String query = " insert into twitterdb.Twits (userID, Twit, TwitDate) "
                    + " value (?,?,?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,twit.getUserID());
            preparedStmt.setString(2, twit.getTwit());
            preparedStmt.setString(3, twit.getTwitDate());
            preparedStmt.execute();
            connection.close();
            
            
            
            
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           return false; 
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        return true;
    }
    public static Twit getTwitByID(String twitID)
    {
        Twit twit = new Twit();
        String sqlResult = "";
        String query = " select * from twitterdb.twits where (twitID = ?) ";
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        
        try
        {
            twitID = twitID.replace("'", "");
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, twitID);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
               
               twit.setUserID(rs.getString("userID"));
               twit.setTwit(rs.getString("twit"));
               twit.setTwitDate(rs.getString("twitDate"));
               twit.setTwitID(rs.getString("twitID"));
               return twit;
            }
            return null;
            
        }
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
     
        
                
    }
    public static ArrayList<Twit> getMentionNotifications(String lastLogin, String username)
    {
        ArrayList<Twit> twits = new ArrayList<Twit>();
        String sqlResult = "";
        String query = " select * from twitterdb.twits where (twit like ? AND twitDate > ?) ";      //get twits with matching mention and date after last Login
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        
        try
        {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "%" + "@" + username + " %"); 
            preparedStmt.setString(2, lastLogin);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
               Twit twit = new Twit();
               twit.setUserID(rs.getString("userID"));
               User referenceUser = UserDB.searchByID(twit.getUserID());
               twit.setTwit("posted by @" + referenceUser.getUserName() + ": <br /> <br />" + rs.getString("twit"));
               twit.setTwitDate(rs.getString("twitDate"));
               twit.setTwitID(rs.getString("twitID"));
               twits.add(twit);
            }
            return twits;
            
        }
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
    }
    public static Twit getTwitByDate(String twitDate)
    {
        Twit twit = new Twit();
        String sqlResult = "";
        String query = " select * from twitterdb.twits where (twitDate = ?) ";
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        
        try
        {
           
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, twitDate);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
               
               twit.setUserID(rs.getString("userID"));
               twit.setTwit(rs.getString("twit"));
               twit.setTwitDate(rs.getString("twitDate"));
               twit.setTwitID(rs.getString("twitID"));
               return twit;
            }
            
            
        }
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        return null;
        
                
    }
    public static ArrayList<Twit> getAllTwits(User user)        //gets twits for the user from all mentions and anyone they follow
    {
        ArrayList<Twit> twits = new ArrayList<Twit>();
        ArrayList<Follow> follows = new ArrayList<Follow>();    //used to store user's followers
        String sqlResult = "";
        String query1 = " select * from twitterdb.twits where (userID = ?) ";   //get users twits and later follower twits
        String query2 = " select * from twitterdb.twits where twit like ? ";    //get users mention
        
        follows = FollowDB.getFollows(user);
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        
        try
        {
            preparedStmt = connection.prepareStatement(query1);
            preparedStmt.setString(1, user.getUserID());
            ResultSet rs = preparedStmt.executeQuery();
           
            
            while(rs.next())
            {
               Twit twit = new Twit();
               twit.setUserID(rs.getString("userID"));
               twit.setTwit(rs.getString("twit"));
               twit.setTwitDate(rs.getString("twitDate"));
               twit.setTwitID(rs.getString("twitID"));
               twits.add(twit);
            }
            
            for(int i=0; i<follows.size(); i++)     //get twits from the users the current user is following
            {
                preparedStmt.setString(1, follows.get(i).getFollowUserID());
                rs = preparedStmt.executeQuery();
                while(rs.next())
                {
                    Twit twit = new Twit();
                    twit.setUserID(rs.getString("userID"));
                    twit.setTwit(rs.getString("twit"));
                    twit.setTwitDate(rs.getString("twitDate"));
                    twit.setTwitID(rs.getString("twitID"));
                    twits.add(twit);
                }
            }
            
           
            preparedStmt = connection.prepareStatement(query2);
            preparedStmt.setString(1, "%" + "@" + user.getUserName() + " %"); //included the " " to prevent getting users with similar names
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
               Twit twit = new Twit();
               twit.setUserID(rs.getString("userID"));
               User referenceUser = UserDB.searchByID(twit.getUserID());
               twit.setTwit("posted by @" + referenceUser.getUserName() + ": <br /> <br />" + rs.getString("twit"));
               twit.setTwitDate(rs.getString("twitDate"));
               twit.setTwitID(rs.getString("twitID"));
               twits.add(twit);
            }
           
         
            Collections.sort(twits, new SortByDateTwit());
            return twits;
            
            
        }
        
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
        }
         finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
        
        
    }
    
    public static int delete(Twit twit) throws IOException,
            ServletException
    {
       int result = 0;
       
       // load the driver, get connection
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement preparedStmt = null;
       
       
       
       try{
            String query = " delete from twitterdb.twits  where (twitID = ?) ";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, twit.getTwitID());
            
            result = preparedStmt.executeUpdate();
            return result;
       }
       catch(SQLException e)
       {
           return result;
       }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
    }
}
