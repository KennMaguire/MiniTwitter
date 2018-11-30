/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import util.DBUtil;
import business.User;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import java.util.ArrayList;



public class UserDB {
    public static boolean insert(User user) throws IOException,
            ServletException 
    {
       String sqlResult = "";
       
       // load the driver, get connection
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement preparedStmt = null;
        try {
            
            //create query
            String query = " insert into twitterdb.user (fullname, username, emailAddress, password, birthdate, questionNo, answer, salt)" 
                    + " value (?, ?, ?, ?, ?, ?, ?, ?)";
                             //1, 2, 3, 4, 5, 6, 7  
            //Create a prepared statement
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getFullName());
            preparedStmt.setString(2, user.getUserName());
            preparedStmt.setString(3, user.getEmail());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, user.getBirthDate());
            preparedStmt.setString(6, user.getQuestionNo());
            preparedStmt.setString(7, user.getAnswer());
            preparedStmt.setString(8, user.getSalt());
            preparedStmt.execute();
            
            
            connection.close();
       
    }
    catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return false;   //return false if failed to add
     }
     finally
     {
        DBUtil.closePreparedStatement(preparedStmt);
        pool.freeConnection(connection);
            
            
     }   
     return true;
    }


    public static User search(String emailAddress) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.user where (emailAddress = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
        preparedStmt = connection.prepareStatement(query);
       
  
         preparedStmt.setString(1, emailAddress);
         
         ResultSet rs = preparedStmt.executeQuery();
         
         User user = new User();
        
         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
         user.setUserID(rs.getString("userID"));
         user.setFullName(rs.getString("fullname"));
         user.setUserName(rs.getString("username"));
         user.setEmail(rs.getString("emailAddress"));
         user.setPassword(rs.getString("password"));
        // user.setConfirmPassword(rs.getString(""));
         user.setBirthDate(rs.getString("birthDate"));
         user.setQuestionNo(rs.getString("questionNo"));
         user.setAnswer(rs.getString("answer"));
         user.setSalt(rs.getString("salt"));
       
            
         return user;
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
 
    
     public static User searchByUN(String username) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.user where (username = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
        preparedStmt = connection.prepareStatement(query);
       
  
         preparedStmt.setString(1, username);
         
         ResultSet rs = preparedStmt.executeQuery();
         
         User user = new User();
        
         while(rs.next()){      //need while for rs.next(), but returns after first user found so only returns one user
         user.setUserID(rs.getString("userID"));
         user.setFullName(rs.getString("fullname"));
         user.setUserName(rs.getString("username"));
         user.setEmail(rs.getString("emailAddress"));
         user.setPassword(rs.getString("password"));
        // user.setConfirmPassword(rs.getString(""));
         user.setBirthDate(rs.getString("birthDate"));
         user.setQuestionNo(rs.getString("questionNo"));
         user.setAnswer(rs.getString("answer"));
         user.setSalt(rs.getString("salt"));
       
            
         return user;
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
    public static User searchByID(String userID) 
    {
         
        
        String sqlResult = "";
        
        String query = " select * from twitterdb.user where (userID = ?) ";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();          //exception for driver not found happens in connection pool
         //get driver and connections
        PreparedStatement preparedStmt = null;
        try{
       
        preparedStmt = connection.prepareStatement(query);
       
  
         preparedStmt.setString(1, userID);
         
         ResultSet rs = preparedStmt.executeQuery();
         
         User user = new User();
        
         while(rs.next())
         {      //need while for rs.next(), but returns after first user found so only returns one user
         user.setUserID(rs.getString("userID"));
         user.setFullName(rs.getString("fullname"));
         user.setUserName(rs.getString("username"));
         user.setEmail(rs.getString("emailAddress"));
         user.setPassword(rs.getString("password"));
        // user.setConfirmPassword(rs.getString(""));
         user.setBirthDate(rs.getString("birthDate"));
         user.setQuestionNo(rs.getString("questionNo"));
         user.setAnswer(rs.getString("answer"));
         user.setSalt(rs.getString("salt"));
       
            
         return user;
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
      
    public static int update(User user)         //need to include salt when updating
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        String sqlResult = "";
        String query = " update twitterdb.user set fullname = ?, username = ?, "
                + "password = ?, birthdate = ?, questionNo = ?, answer = ?, salt = ? where emailAddress = ?";     //need salt here
        int result;
        try{
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getFullName());          //set all values before updating
            preparedStmt.setString(2, user.getUserName());
            preparedStmt.setString(3, user.getPassword());
            preparedStmt.setString(4, user.getBirthDate());
            preparedStmt.setString(5, user.getQuestionNo());
            preparedStmt.setString(6, user.getAnswer());
            preparedStmt.setString(7, user.getSalt());
            preparedStmt.setString(8, user.getEmail());
            result = preparedStmt.executeUpdate();
            return result;
        }
        catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return -1;   //return false if failed to add
        }
        finally
        {
            DBUtil.closePreparedStatement(preparedStmt);
            pool.freeConnection(connection);
            
            
        }
        
       
    }
    public static ArrayList<User> getAllUsers()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStmt = null;
        String sqlResult = "";
        String query = " select * from twitterdb.user ";
        String fullname;
        ArrayList<User> users = new ArrayList<User>();
        try{
         preparedStmt = connection.prepareStatement(query);
         ResultSet rs = preparedStmt.executeQuery();
         
         while(rs.next())
         {
             User user = new User();
             user.setUserID(rs.getString("userID"));
             user.setFullName(rs.getString("fullname"));
             user.setUserName(rs.getString("username"));
             
             user.setEmail(rs.getString("emailAddress"));
             user.setPassword(rs.getString("password"));
             // user.setConfirmPassword(rs.getString(""));
             user.setBirthDate(rs.getString("birthDate"));
             user.setQuestionNo(rs.getString("questionNo"));
             user.setAnswer(rs.getString("answer"));
             user.setSalt(rs.getString("salt"));
             users.add(user);
         }
         return users;
        }
        catch(SQLException e)
        {
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
}
