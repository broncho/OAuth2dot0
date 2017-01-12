package com.github.broncho.npoauth2.server.handler.auth;

import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.realm.AuthCode;
import com.github.broncho.npoauth2.data.realm.AccessToken;
import com.github.broncho.npoauth2.server.handler.ServerBaseHandler;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class AccessTokenHandler extends ServerBaseHandler {
    
    private Logger logger = LoggerFactory.getLogger(AccessTokenHandler.class);
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        
        logger.info("Request ==> {}.", request.queryString());
        
        OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request.raw());
        Optional<App> appOptional = oAuthService.checkClientId(tokenRequest.getClientId());
        if (appOptional.isPresent()) {
            final App app = appOptional.get();
            if (app.clientSecret.equals(tokenRequest.getClientSecret())) {
                String authCode = tokenRequest.getParam(OAuth.OAUTH_CODE);
                
                if (tokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                    
                    if (oAuthService.checkAuthCode(new AuthCode(authCode, tokenRequest.getRedirectURI(), app))) {
                        
                        final String accessToken = oAuthIssuer.accessToken();
                        
                        AccessToken tokenBody = new AccessToken(accessToken, oAuthService.getOpenIdByAuthCode(authCode));
                        
                        oAuthService.addAccessToken(tokenBody);
                        
                        OAuthResponse oAuthResponse = OAuthASResponse
                                .tokenResponse(HttpServletResponse.SC_OK)
                                .setAccessToken(tokenBody.accessToken)
                                .setExpiresIn(String.valueOf(tokenBody.expireIn))
                                .buildJSONMessage();
                        return oAuthResponse.getBody();
                    } else {
                        OAuthResponse oAuthResponse = OAuthASResponse
                                .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_GRANT)
                                .setErrorDescription("Bad Request + Invalid Grant")
                                .buildJSONMessage();
                        return oAuthResponse.getBody();
                    }
                } else {
                    OAuthResponse oAuthResponse = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.UNSUPPORTED_GRANT_TYPE)
                            .setErrorDescription("Bad Request + Unsupported Grant Type")
                            .buildJSONMessage();
                    return oAuthResponse.getBody();
                }
            } else {
                OAuthResponse oAuthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                        .setErrorDescription("Unauthorized + Unauthorized Client")
                        .buildJSONMessage();
                return oAuthResponse.getBody();
            }
        } else {
            OAuthResponse oAuthResponse = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                    .setErrorDescription("Bad Request + Invalid Client")
                    .buildJSONMessage();
            return oAuthResponse.getBody();
        }
    }
}
