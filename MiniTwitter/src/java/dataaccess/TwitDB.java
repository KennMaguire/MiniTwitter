/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

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

/**
 *
 * @author kennethmaguire
 */
class SortByDate implements Comparator<Twit> 
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
            
            
        }
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
        }
        return null;
        
                
    }
    public static ArrayList<Twit> getUserTwits(User user)
    {
        ArrayList<Twit> twits = new ArrayList<Twit>();
        String sqlResult = "";
        String query = " select * from twitterdb.twits where (userID = ?) ";
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        
        try
        {
            preparedStmt = connection.prepareStatement(query);
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
            Collections.sort(twits, new SortByDate());
            return twits;
            
            
        }
        
        catch(SQLException e)
        {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return null;   //return false if failed to add  
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
        
    }
}
