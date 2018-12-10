/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Hashtag;
import business.TweetHashtags;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import util.DBUtil;

/**
 *
 * @author kennethmaguire
 */
public class HashtagDB {
    public static boolean insertHashtag(Hashtag hashtag) throws IOException,
            ServletException
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        
        
        
        try{
            String query1 = " insert into twitterdb.hashtag (hashtagText, hashtagCount) "
                    + " value (?,?)";
            String query2 = " insert into twitterdb.tweetHashtag ";
            preparedStmt = connection.prepareStatement(query1);
            preparedStmt.setString(1,hashtag.getHashtagText());
            preparedStmt.setString(2, hashtag.getHashtagCount());
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
 public static void insertTweetHashtag(String hashtagID, String tweetID)
 {
     String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        
        
        
        try{
            String query1 = " insert into twitterdb.tweetHashtag (hashtagID, tweetID) "
                    + " value (?,?)";
            String query2 = " insert into twitterdb.tweetHashtag ";
            preparedStmt = connection.prepareStatement(query1);
            preparedStmt.setString(1,hashtagID);
            preparedStmt.setString(2, tweetID);
            preparedStmt.execute();
            connection.close();
            
            
            
            
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
     
 }
 public static Hashtag search(String hashtagText) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.hashtag where (hashtagText = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
         preparedStmt = connection.prepareStatement(query);
         preparedStmt.setString(1, hashtagText);
         ResultSet rs = preparedStmt.executeQuery();
         Hashtag hashtag = new Hashtag();
         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
             hashtag.setHashtagID(rs.getString("hashtagID"));
             hashtag.setHashtagText(rs.getString("hashtagText"));
             hashtag.setHashtagCount(rs.getString("hashtagCount"));
             return hashtag;
       
         }
         return null;
         
         }
        catch (SQLException e) {
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
 public static ArrayList<TweetHashtags> searchByTweetID(String tweetID) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.tweetHashtag where (tweetID = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
         preparedStmt = connection.prepareStatement(query);
         preparedStmt.setString(1, tweetID);
         ResultSet rs = preparedStmt.executeQuery();
        
                  
         ArrayList<TweetHashtags> tweetHashtags = new ArrayList<TweetHashtags>();
         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
             TweetHashtags tweetHashtag = new TweetHashtags();
             tweetHashtag.setHashtagID(rs.getString("hashtagID"));
             tweetHashtag.setTweetID(rs.getString("tweetID"));
             tweetHashtags.add(tweetHashtag);
       
         }
         
         return tweetHashtags;
        
         
         }
        catch (SQLException e) {
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
 //gets a single hashtag by its hashtagID
  public static Hashtag searchByHashtagID(String hashtagID) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.Hashtag where (hashtagID = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
         preparedStmt = connection.prepareStatement(query);
         preparedStmt.setString(1, hashtagID);
         ResultSet rs = preparedStmt.executeQuery();
         Hashtag hashtag = new Hashtag();

         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
             hashtag.setHashtagID(rs.getString("hashtagID"));
             hashtag.setHashtagText(rs.getString("hashtagText"));
             hashtag.setHashtagCount(rs.getString("hashtagCount"));
             return hashtag;  
             
         }
     
         return null;
         
         }
        catch (SQLException e) {
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
 public static ArrayList<TweetHashtags> getHashtagTwits(String hashtagID) 
    {
         
        ArrayList<TweetHashtags> tweetHashtags = new ArrayList<TweetHashtags>();
        

        String sqlResult = "";
        
        String query = " select * from twitterdb.tweetHashtag where (hashtagID = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
         preparedStmt = connection.prepareStatement(query);
         preparedStmt.setString(1, hashtagID);
         ResultSet rs = preparedStmt.executeQuery();
         
         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
             TweetHashtags tweetHashtag = new TweetHashtags();
             tweetHashtag.setTweetID(rs.getString("tweetID"));
             tweetHashtag.setHashtagID(rs.getString("hashtagID"));
             tweetHashtags.add(tweetHashtag);
             
         }
         if(tweetHashtags.size() != 0)
         {
             return tweetHashtags;
         }
         else
         {
             return null;
         }
         
         }
        catch (SQLException e) {
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
    public static void delete(String tweetID)
    {
        
       int result = 0;
       String sqlResult = "";
       // load the driver, get connection
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement preparedStmt = null;
       
       
       
       try{
            String query2 = " delete from twitterdb.tweetHashtag  where (tweetID = ?) ";
            preparedStmt = connection.prepareStatement(query2);
            preparedStmt.setString(1, tweetID);
            preparedStmt.executeUpdate();
       }
       catch(SQLException e)
       {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";

       }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
    
    }
    public static void addOne(Hashtag hashtag)
    {
          
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
            String query = " update twitterdb.hashtag set hashtagCount = ? where (hashtagID = ?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, hashtag.getHashtagCount());
            preparedStmt.setString(2, hashtag.getHashtagID());
            preparedStmt.executeUpdate();
        }
        catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
               //return false if failed to add
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
        }
    }
    public static void minusOne(Hashtag hashtag)
    {
          
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
            String query = " update twitterdb.hashtag set hashtagCount = ? where (hashtagID = ?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, hashtag.getHashtagCount());
            preparedStmt.setString(2, hashtag.getHashtagID());
            preparedStmt.executeUpdate();
        }
        catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
               //return false if failed to add
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
        }
    }
}
