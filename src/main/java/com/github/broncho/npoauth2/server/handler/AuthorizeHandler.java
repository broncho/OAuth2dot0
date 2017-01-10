package com.github.broncho.npoauth2.server.handler;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class AuthorizeHandler extends ServerBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---AuthorizeHandler---");
        OAuthAuthzRequest authAuthzRequest = new OAuthAuthzRequest(request.raw());
        response.header("Content-Type","application/x-www-form-urlencoded");
        if (auth2Service.checkClientId(authAuthzRequest.getClientId())) {
            String authorizationCode = null;
            String responseType = authAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                authorizationCode = oAuthIssuer.authorizationCode();
                auth2Service.addAuthCode(authorizationCode, "admin");
            }
            final OAuthResponse authResponse = new OAuthASResponse
                    .OAuthAuthorizationResponseBuilder(request.raw(), HttpServletResponse.SC_FOUND)
                    .setCode(authorizationCode)
                    .location(authAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI))
                    .buildQueryMessage();
            response.redirect(authResponse.getLocationUri());
        } else {
            OAuthResponse oAuthResponse = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                    .setErrorDescription("Bad Request + Invalid Client")
                    .buildJSONMessage();
            response.redirect(oAuthResponse.getLocationUri());
        }
        return "";
    }
}