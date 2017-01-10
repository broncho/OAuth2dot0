package com.github.broncho.npoauth2.core;

/**
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public interface OAuth2Service {
    
    
    void addAuthCode(String authCode, String username);
    
    void addAccessToken(String accessToken, String username);
    
    boolean checkAuthCode(String authCode);
    
    boolean checkAccessToken(String accessToken);
    
    boolean checkClientId(String clientId);
    
    boolean checkClientSecret(String clientSecret);
    
    String getUsernameByAccessToken(String accessToken);
}
