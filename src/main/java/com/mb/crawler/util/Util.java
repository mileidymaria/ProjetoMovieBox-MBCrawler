package com.mb.crawler.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static int generateStringHash(String str){
        int hash = 0;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            hash = new BigInteger(1,byteData).intValue();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String capitalize(String str){
        if(str == null || !isAlphabetic(str)){
            return null;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static boolean isAlphabetic(String str){
        if(str == null){
            return false;
        }
        if(str.matches("[a-zA-Z]+")){
            return true;
        }
        return false;
    }
}
