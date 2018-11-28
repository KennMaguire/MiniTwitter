/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author kennethmaguire
 */
public class HashPasswordUtil {
    
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {        
        MessageDigest md = MessageDigest.getInstance("SHA-256");            //get hash function
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();                   //use SHA-256 on password and store as array of bytes
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray) {            //for each byte in mdArray, add 255 and store in sb as Hex
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));  //change Hex value of string to regular chars and return
        }        
        return sb.toString();        
    }
    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    
}
