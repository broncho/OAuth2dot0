package com.github.broncho.npoauth2.server.handler;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class AccessTokenHandler extends ServerBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---AccessTokenHandler---");
        OAuthTokenRequest oAuthTokenRequest = new OAuthTokenRequest(request.raw());
        if (auth2Service.checkClientId(oAuthTokenRequest.getClientId())) {
            if (auth2Service.checkClientSecret(oAuthTokenRequest.getClientSecret())) {
                String authCode = oAuthTokenRequest.getParam(OAuth.OAUTH_CODE);
                if (oAuthTokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                    if (auth2Service.checkAuthCode(authCode)) {
                        final String accessToken = oAuthIssuer.accessToken();
                        auth2Service.addAccessToken(accessToken, "admin");
                        OAuthResponse oAuthResponse = OAuthASResponse
                                .tokenResponse(HttpServletResponse.SC_OK)
                                .setAccessToken(accessToken)
                                .setExpiresIn(String.valueOf(System.currentTimeMillis() + 30_000))
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
