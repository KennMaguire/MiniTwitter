/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author kennethmaguire
 */
public class Twit implements Serializable{
    private String twitID;
    private String twit;
    private String userID;
    private String twitDate;
    
    public Twit()
    {
        
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
}
