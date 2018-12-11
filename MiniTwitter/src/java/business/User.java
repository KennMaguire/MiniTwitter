/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String userID;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;
    private String birthDate;
    private String questionNo;
    private String answer;
    private String salt;
    private String lastLogin;
    private boolean followed;
    public User()
    {
        fullName = "";
        email = "";
        followed = false;
    }
    public User(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.setFullName(data[0]);
        this.setUserName(data[1]);
        this.setEmail(data[2]);
        this.setPassword(data[3]);
        this.setBirthDate(data[4]);
        this.setQuestionNo(data[5]);
        this.setAnswer(data[6]);
        this.setConfirmPassword(data[7]);
        this.setUserID(data[8]);
        this.setSalt(data[9]);
    
        
    }
   
    @Override
    public boolean equals(Object user) {  //https://stackoverflow.com/questions/12697407/arraylist-remove-is-not-removing-an-object
        if (user == null) return false;  
        if(!(user instanceof User)) return false;
        User use = (User) user;
        return use.userName.equals(this.userName);    
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.userName);
        return hash;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    
    public String getUserID()
    {
        return this.userID;
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
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getBirthDate()
    {
        return this.birthDate;
    }
    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
    }
    public String getQuestionNo()
    {
        return this.questionNo;
    }
    public void setQuestionNo(String questionNo)
    {
        this.questionNo = questionNo;
    }
    public String getAnswer()
    {
        return this.answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
        
    }
    public String getConfirmPassword()
    {
        return this.confirmPassword;
    }
    public void setSalt(String salt)
    {
        this.salt = salt;
    }
    public String getSalt()
    {
        return this.salt;
    }
    public void setLastLogin(String lastLogin)
    {
        this.lastLogin = lastLogin;
    }
    public String getLastLogin()
    {
        return this.lastLogin;
    }
    public void setFollowed(boolean followed)
    {
        this.followed = followed;
    }
    public boolean getFollowed()
    {
        return this.followed;
    }
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s]", this.getFullName(), this.getEmail()));
      return sb.toString();
    }
    
    
}
