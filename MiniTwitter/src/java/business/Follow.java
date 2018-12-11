/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.FollowDB;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kennethmaguire
 */
public class Follow {
 
    private String userID;
    private String followUserID;
    private String dateFollowed;
    
    public Follow()
    {
       //intentionally empty 
    }
    //This function an array of all the users followed by the logged in user to display "follow" and "unfollow" on the homepage
    public int whichUsersFollowed(ArrayList<User> users, User user)
    {
       
        int numFollowed = 0;
          ArrayList<Follow> follows = new ArrayList<Follow>();
          follows = FollowDB.getFollows(user);
          for(int i = 0; i < users.size(); i++)
          {
              users.get(i).setFollowed(false);      //initially set all to false again
          }
          
          for(int i = 0; i < follows.size(); i++)
          {
              for(int j=0; j < users.size(); j++)
              {
                  if(users.get(j).getUserID().equals(follows.get(i).getFollowUserID())){    //if the user in the list is followed by the logged in user, true is added to list of booleans 
                      users.get(j).setFollowed(true);
                      numFollowed += 1;
                  }
              }
              
          }
          
          return numFollowed;
    }
    public int whichUsersFollowing(ArrayList<User> users, User user)
    {
       
          int numFollowing = 0;
          ArrayList<Follow> follows = new ArrayList<Follow>();
          follows = FollowDB.getFollowedBy(user);
          for(int i = 0; i < users.size(); i++)
          {
              users.get(i).setFollowed(false);      //initially set all to false again
          }
          
          for(int i = 0; i < follows.size(); i++)
          {
              for(int j=0; j < users.size(); j++)
              {
                  if(users.get(j).getUserID().equals(follows.get(i).getUserID())){    //if the user in the list is followed by the logged in user, true is added to list of booleans 
                      users.get(j).setFollowed(true);
                      numFollowing += 1;
                  }
              }
              
          }
          
          return numFollowing;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getUserID()
    {
        return this.userID;
    }
    public void setFollowUserID(String followUserID)
    {
        this.followUserID = followUserID;
    }
    public String getFollowUserID()
    {
        return this.followUserID;
    }
    public void setDateFollowed(String dateFollowed)
    {
        this.dateFollowed = dateFollowed;
    }
    public String getDateFollowed()
    {
        return this.dateFollowed;
    }
         
    
}
