package com.github.broncho.npoauth2.data.realm;

import com.github.broncho.npoauth2.data.User;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class AuthCode {
    
    public final String authCode;
    
    public final User user;
    
    public final String redirectUrl;
    
    public final String clientId;
    
    public final String clientSecret;
    
    public final Long expireIn = System.currentTimeMillis() + 2 * 60_000;
    
    public AuthCode(String authCode, User user, String redirectUrl, String clientId, String clientSecret) {
        this.authCode = authCode;
        this.user = user;
        this.redirectUrl = redirectUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    
    public AuthCode(String authCode, String redirectUrl, String clientId, String clientSecret) {
        this(authCode, null, redirectUrl, clientId, clientSecret);
    }
}
