/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.HashtagDB;

/**
 *
 * @author kennethmaguire
 */
public class Hashtag {
    String hashtagID;
    String hashtagText;
    String hashtagCount;
    public Hashtag()
    {
        //intentionally empty
    }
    
    public void setHashtagID(String hashtagID)
    {
        this.hashtagID = hashtagID;
    }
    public String getHashtagID()
    {
        return this.hashtagID;
    }
    public void setHashtagText(String hashtagText)
    {
        this.hashtagText = hashtagText;
    }
    public String getHashtagText()
    {
        return this.hashtagText;
    }
    public void setHashtagCount(String hashtagCount)
    {
        this.hashtagCount = hashtagCount;
    }
    public String getHashtagCount()
    {
        return this.hashtagCount;
    }
            
}
