/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.User;
import business.Follow;
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
public class FollowDB {
    public static boolean insert(Follow follow) throws IOException,
            ServletException
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        try{
            String query = " insert into twitterdb.Follow (userID, followUserID, dateFollowed) value (?,?,?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, follow.getUserID());
            preparedStmt.setString(2, follow.getFollowUserID());
            preparedStmt.setString(3, follow.getDateFollowed());
            preparedStmt.executeUpdate();
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
    public static boolean delete(Follow follow) throws IOException,
            ServletException
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        try{
            String query = " delete from twitterdb.follow where (userID = ?) AND (followUserID = ?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, follow.getUserID());
            preparedStmt.setString(2, follow.getFollowUserID());
            preparedStmt.executeUpdate();
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
    public static ArrayList<Follow> getFollowNotifications(String lastLogin, String userID)
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        ArrayList<Follow> follows = new ArrayList<Follow>();
        try{
            String query = "Select * from twitterdb.Follow where (followUserID = ? AND dateFollowed > ?)";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, userID);
            preparedStmt.setString(2, lastLogin);
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
                Follow follow = new Follow();
                follow.setUserID(rs.getString("userID"));
                follow.setFollowUserID(rs.getString("followUserID"));
                follow.setDateFollowed(rs.getString("dateFollowed"));
                follows.add(follow);
            }
        return follows; 
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           return null; 
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
      
        
    }
    
    public static ArrayList<Follow> getFollows(User user)
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        ArrayList<Follow> follows = new ArrayList<Follow>();
        try{
            String query = "Select * from twitterdb.Follow where userID = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getUserID());
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
                Follow follow = new Follow();
                follow.setUserID(rs.getString("userID"));
                follow.setFollowUserID(rs.getString("followUserID"));
                follow.setDateFollowed(rs.getString("dateFollowed"));
                follows.add(follow);
            }
            
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           return null; 
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
      return follows;
        
    }
    public static ArrayList<Follow> getFollowedBy(User user)
    {
        String sqlResult = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        ArrayList<Follow> follows = new ArrayList<Follow>();
        try{
            String query = "Select * from twitterdb.Follow where followUserID = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getUserID());
            ResultSet rs = preparedStmt.executeQuery();
            
            while(rs.next())
            {
                Follow follow = new Follow();
                follow.setUserID(rs.getString("userID"));
                follow.setFollowUserID(rs.getString("followUserID"));
                follow.setDateFollowed(rs.getString("dateFollowed"));
                follows.add(follow);
            }
            
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           return null; 
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
      return follows;
        
    }
    
    
    
}
