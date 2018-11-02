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
            String query = " insert into twitterdb.user (fullname, username, emailAddress, password, birthdate, questionNo, answer)" 
                    + " value (?, ?, ?, ?, ?, ?, ?)";
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
            
            preparedStmt.execute();
            
            
            connection.close();
       
    }
    catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
            return false;   //return false if failed to add
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
         user.setFullName(rs.getString("fullname"));
         user.setUserName(rs.getString("username"));
         user.setEmail(rs.getString("emailAddress"));
         user.setPassword(rs.getString("password"));
        // user.setConfirmPassword(rs.getString(""));
         user.setBirthDate(rs.getString("birthDate"));
         user.setQuestionNo(rs.getString("questionNo"));
         user.setAnswer(rs.getString("answer"));
         
       
            
         return user;
         }
         
         /*
         //fill user with empty values if no user in DB
         user.setFullName(" ");
         user.setUserName(" ");
         user.setEmail(" ");
         user.setPassword(" ");
        // user.setConfirmPassword(rs.getString(""));
         user.setBirthDate(" ");
         user.setQuestionNo(" ");
         user.setAnswer(" ");
         */
         
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
    
}
