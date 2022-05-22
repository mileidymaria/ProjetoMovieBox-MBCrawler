package com.mb.crawler.util;

import com.mb.crawler.CrawlerApplication;
import org.springframework.boot.SpringApplication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static void main(String[] args) {
        generateStringHash("Vingadores ultimato e etc etc etcrreer ertettr htyty NSAJISAIBASB");
    }
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
}
