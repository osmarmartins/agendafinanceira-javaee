package br.com.futura.agendafinanceira.utils;

import java.math.BigInteger;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
  
public class HashMD5Util {  
      
    public static String getMD5(String senha) throws NoSuchAlgorithmException  {

        String senhaMD5 = "";
        MessageDigest messageDigest;

        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(senha.getBytes(), 0, senha.length());
        BigInteger i = new BigInteger(1, messageDigest.digest());
        senhaMD5 = String.format("%1$032X", i);
        return senhaMD5;
        
    }
}

