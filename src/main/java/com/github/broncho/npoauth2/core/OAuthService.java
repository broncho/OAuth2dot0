package com.github.broncho.npoauth2.core;

import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.realm.AuthCode;
import com.github.broncho.npoauth2.data.realm.AccessToken;

import java.util.Optional;

/**
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public interface OAuthService {
    
    void addAuthCode(AuthCode authCode);
    
    void addAccessToken(AccessToken accessToken);
    
    boolean checkAuthCode(AuthCode authCode);
    
    String getOpenIdByAuthCode(String authCode);
    
    boolean checkAccessToken(String accessToken);
    
    String getOpenIdByAccessToken(String accessToken);
    
    Optional<App> checkClientId(String clientId);
    
}