package com.github.broncho.npoauth2.core.impl;

import com.github.broncho.npoauth2.core.OAuthService;
import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.realm.AuthCode;
import com.github.broncho.npoauth2.data.realm.AccessToken;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public class OAuthServiceImpl implements OAuthService {
    
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
                    item.app.clientId.equals(authCode.app.clientId)
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
    public Optional<App> checkClientId(String clientId) {
        App target = null;
        for (App app : App.values()) {
            if (app.clientId.equals(clientId)) {
                target = app;
            }
        }
        return Optional.ofNullable(target);
    }
}
