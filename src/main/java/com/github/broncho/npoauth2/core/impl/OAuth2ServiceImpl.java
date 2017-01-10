package com.github.broncho.npoauth2.core.impl;

import com.github.broncho.npoauth2.core.OAuth2Service;
import com.github.broncho.npoauth2.data.App;

import java.util.HashMap;

/**
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public class OAuth2ServiceImpl implements OAuth2Service {
    
    
    public static HashMap<String, String> userMap = new HashMap<>();
    
    @Override
    public void addAuthCode(String authCode, String username) {
        
    }
    
    @Override
    public void addAccessToken(String accessToken, String username) {
        userMap.put(accessToken, username);
    }
    
    @Override
    public boolean checkAuthCode(String authCode) {
        return true;
    }
    
    @Override
    public boolean checkAccessToken(String accessToken) {
        return true;
    }
    
    @Override
    public boolean checkClientId(String clientId) {
        for (App app : App.values()) {
            if (app.clientId.equals(clientId)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean checkClientSecret(String clientSecret) {
        for (App app : App.values()) {
            if (app.clientSecret.equals(clientSecret)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return userMap.get(accessToken);
    }
}
