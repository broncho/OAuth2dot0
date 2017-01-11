package com.github.broncho.npoauth2.server.handler.auth;

import com.github.broncho.npoauth2.data.User;
import com.github.broncho.npoauth2.data.realm.AuthCode;
import com.github.broncho.npoauth2.server.handler.ServerBaseHandler;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class AuthorizeHandler extends ServerBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---AuthorizeHandler---");
        OAuthAuthzRequest authAuthzRequest = new OAuthAuthzRequest(request.raw());
        if (auth2Service.checkClientId(authAuthzRequest.getClientId())) {
            Optional<User> userOptional = User.validUser(
                    request.queryParams("username"),
                    request.queryParams("password")
            );
            
            if (userOptional.isPresent()) {
                String authorizationCode = null;
                String responseType = authAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
                if (responseType.equals(ResponseType.CODE.toString())) {
                    
                    authorizationCode = oAuthIssuer.authorizationCode();
                    
                    //添加授权码
                    auth2Service.addAuthCode(new AuthCode(
                            authorizationCode,
                            userOptional.get(),
                            authAuthzRequest.getRedirectURI(),
                            authAuthzRequest.getClientId(),
                            authAuthzRequest.getClientSecret()
                    ));
                }
                final OAuthResponse authResponse = new OAuthASResponse
                        .OAuthAuthorizationResponseBuilder(request.raw(), HttpServletResponse.SC_FOUND)
                        .setCode(authorizationCode)
                        .location(authAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI))
                        .buildQueryMessage();
                response.redirect(authResponse.getLocationUri());
            } else {
                response.body("Username or Password Error.");
            }
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