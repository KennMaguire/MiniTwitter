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
public class Notifications {
    private Follow follow;
    private UserTwit userTwit;
    private String username;
    private String notifyDate;
    
    
    public void setFollow(Follow follow)
    {
        this.follow = follow;
    }
    public Follow getFollow()
    {
        return this.follow;
    }
    public void setUserTwit(UserTwit userTwit)
    {
        this.userTwit = userTwit;
    }
    public UserTwit getUserTwit()
    {
        return this.userTwit;
    }
    public void setUserName(String username)
    {
        this.username = username;
    }
    public String getUserName()
    {
        return this.username;
    }
    public void setNotifyDate(String notifyDate)
    {
        this.notifyDate = notifyDate;
    }
    public String getNotifyDate()
    {
        return this.notifyDate;
    }
    
    
    
}
