package com.github.broncho.npoauth2.data;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public interface Defined {
    
    String HOST = "http://192.168.60.9";
    
    interface Server {
        
        Integer PORT = 8090;
        
        String SITE = "/server";
        
        String ADDRESS = HOST + ":" + PORT;
        
        String AUTH = "/authorize";
        
        String AUTH_ADDRESS = ADDRESS + AUTH;
        
        String TOKEN = "/access_token";
        
        String TOKEN_ADDRESS = ADDRESS + TOKEN;
        
        String INFO = "/user_info";
        
        String INFO_ADDRESS = ADDRESS + INFO;
        
    }
    
    interface Client {
        
        Integer PORT = 8091;
        
        String SITE = "/client";
        
        String ADDRESS = HOST + ":" + PORT;
  
        String LOGIN= "/login";
        
        String REDIRECT = "/redirect";
        
        String REDIRECT_ADDRESS = ADDRESS + REDIRECT;
    }
}
