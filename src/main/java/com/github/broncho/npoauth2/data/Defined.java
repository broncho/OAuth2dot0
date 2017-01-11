package com.github.broncho.npoauth2.data;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public interface Defined {
    
    String HOST = "http://192.168.60.9";
    
    
    interface Param{
        
        String OPEN_ID="open_id";
    }
    
    
    
    interface Server {
        
        Integer PORT = 8090;
        
        String SITE = "/server";
        
        String ADDRESS = HOST + ":" + PORT;
        
        /**
         * 登录
         */
        String LOGIN = "/login";
        
        String LOGIN_ADDRESS = ADDRESS + LOGIN;
        
        /**
         * 授权
         */
        String AUTH = "/authorize";
        
        String AUTH_ADDRESS = ADDRESS + AUTH;
        
        /**
         * 令牌
         */
        String TOKEN = "/access_token";
        
        String TOKEN_ADDRESS = ADDRESS + TOKEN;
        
        /**
         * 用户OpenId
         */
        String OPENID = "/open_id";
        
        String OPENID_ADDRESS = ADDRESS + OPENID;
        
        /**
         * 用户资源
         */
        String INFO = "/user_info";
        
        String INFO_ADDRESS = ADDRESS + INFO;
        
        
    }
    
    interface Client {
        
        Integer PORT = 8091;
        
        String SITE = "/client";
        
        String ADDRESS = HOST + ":" + PORT;
        
        /**
         * 登录
         */
        String LOGIN = "/login";
        
        /**
         * 回调
         */
        String REDIRECT = "/redirect";
        
        String REDIRECT_ADDRESS = ADDRESS + REDIRECT;
        
        /**
         * 用户OpenId
         */
        String OPENID = "/open_id";
        
        /**
         * 用户信息
         */
        String INFO = "/info";
        
    }
}
