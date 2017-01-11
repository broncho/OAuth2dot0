package com.github.broncho.npoauth2.data.realm;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class AccessToken {
    
    public final String accessToken;
    
    public final Long expireIn = System.currentTimeMillis() + 30 * 60000;
    
    public final String openId;
    
    public AccessToken(String accessToken, String openId) {
        this.accessToken = accessToken;
        this.openId = openId;
    }
}