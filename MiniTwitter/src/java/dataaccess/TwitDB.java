/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Twit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;

/**
 *
 * @author kennethmaguire
 */
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
            
            /*
            java.util.Date dt = new java.util.Date();   //found at https://stackoverflow.com/questions/2400955/how-to-store-java-date-to-mysql-datetime

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            */
            
            
        }
        catch(SQLException e){
           sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
           return false; 
        }
        return true;
    }
    
}
