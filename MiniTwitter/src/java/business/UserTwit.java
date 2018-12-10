/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author kennethmaguire
 */


//This class is used for the hashtags.jsp page, to associate proper user with twit
public class UserTwit {
   
    private String userID;
    private String fullName;
    private String userName;
    private String twitID;
    private String twit;
    private String twitDate;
    
    
    public UserTwit()
    {
        //intentionally empty
    }
    public void setTwitID(String twitID)
    {
        this.twitID = twitID;
    }
    public String getTwitID()
    {
        return this.twitID;
    }
    public void setTwit(String twit)
    {
        this.twit = twit;
    }
    public String getTwit()
    {
        return this.twit;
    }
     public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getUserID()
    {
        return this.userID;
    }
    public void setTwitDate(String twitDate)
    {
        this.twitDate = twitDate;
    }
    public String getTwitDate()
    {
        return this.twitDate;
    }
    public String getFullName()
    {
        return this.fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    public String getUserName()
    {
        return this.userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

}
