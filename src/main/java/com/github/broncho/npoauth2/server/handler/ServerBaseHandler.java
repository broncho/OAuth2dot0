package com.github.broncho.npoauth2.server.handler;

import com.github.broncho.npoauth2.core.OAuth2Service;
import com.github.broncho.npoauth2.core.impl.OAuth2ServiceImpl;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import spark.Route;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
abstract class ServerBaseHandler implements Route {
    
    OAuth2Service auth2Service = new OAuth2ServiceImpl();
    
    OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
    
}
