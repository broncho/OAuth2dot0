package com.github.broncho.npoauth2.core.impl;

import com.github.broncho.npoauth2.core.OAuth2Service;
import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.realm.AuthCode;
import com.github.broncho.npoauth2.data.realm.AccessToken;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public class OAuth2ServiceImpl implements OAuth2Service {
    
    private static final Set<AuthCode> AUTH_BODY_SET = new HashSet<>();
    
    private static final Set<AccessToken> TOKEN_BODY_SET = new HashSet<>();
    
    @Override
    public synchronized void addAuthCode(AuthCode authCode) {
        AUTH_BODY_SET.add(authCode);
    }
    
    @Override
    public synchronized void addAccessToken(AccessToken accessToken) {
        TOKEN_BODY_SET.add(accessToken);
    }

    
    @Override
    public synchronized boolean checkAuthCode(AuthCode authCode) {
        boolean isOk = false;
        Iterator<AuthCode> iterator = AUTH_BODY_SET.iterator();
        while (iterator.hasNext()) {
            AuthCode item = iterator.next();
            if (item.authCode.equals(authCode.authCode) &&
                    item.redirectUrl.equals(authCode.redirectUrl) &&
                    item.clientId.equals(authCode.clientId) &&
                    item.clientSecret.equals(authCode.clientSecret)
                    ) {
                if (item.expireIn < System.currentTimeMillis()) {
                    iterator.remove();
                } else {
                    isOk = true;
                }
                break;
            }
        }
        return isOk;
    }
    
    
    @Override
    public synchronized String getOpenIdByAuthCode(String authCode) {
        for (AuthCode authBody : AUTH_BODY_SET) {
            if (authBody.authCode.equals(authCode)) {
                return authBody.user.getId();
            }
        }
        return "";
    }
    
    @Override
    public synchronized boolean checkAccessToken(String accessToken) {
        boolean isOk = false;
        Iterator<AccessToken> iterator = TOKEN_BODY_SET.iterator();
        while (iterator.hasNext()) {
            AccessToken tokenBody = iterator.next();
            if (tokenBody.accessToken.equals(accessToken)) {
                if (tokenBody.expireIn < System.currentTimeMillis()) {
                    iterator.remove();
                } else {
                    isOk = true;
                }
                break;
            }
        }
        return isOk;
    }
    
    @Override
    public String getOpenIdByAccessToken(String accessToken) {
        for (AccessToken tokenBody : TOKEN_BODY_SET) {
            if (tokenBody.accessToken.equals(accessToken)) {
                return tokenBody.openId;
            }
        }
        return "";
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
}
