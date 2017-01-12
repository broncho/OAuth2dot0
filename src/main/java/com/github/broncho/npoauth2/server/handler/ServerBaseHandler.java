package com.github.broncho.npoauth2.server.handler;

import com.github.broncho.npoauth2.core.OAuthService;
import com.github.broncho.npoauth2.core.impl.OAuthServiceImpl;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public abstract class ServerBaseHandler implements Route {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected OAuthService oAuthService = new OAuthServiceImpl();
    
    protected OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
    
}
