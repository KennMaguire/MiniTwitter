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
public class TweetHashtags {
    String tweetID;
    String hashtagID;
    
    public TweetHashtags()
    {
        //interntionally empty
    }
    public void setTweetID(String tweetID)
    {
        this.tweetID = tweetID;
    }
    public String getTweetID()
    {
        return this.tweetID;
        
    }
    public void setHashtagID(String hashtagID)
    {
        this.hashtagID = hashtagID;
    }
    public String getHashtagID()
    {
        return this.hashtagID;
    }
}
