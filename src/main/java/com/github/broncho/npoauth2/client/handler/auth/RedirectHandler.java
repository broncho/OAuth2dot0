package com.github.broncho.npoauth2.client.handler.auth;

import com.github.broncho.npoauth2.client.handler.ClientBaseHandler;
import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.Defined;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import spark.Request;
import spark.Response;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class RedirectHandler extends ClientBaseHandler {
    
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---RedirectHandler---");
        
        OAuthAuthzResponse authAuthzResponse = OAuthAuthzResponse.oauthCodeAuthzResponse(request.raw());
        OAuthClientRequest authClientRequest = OAuthClientRequest
                .tokenLocation(Defined.Server.TOKEN_ADDRESS)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(App.VDT.clientId)
                .setClientSecret(App.VDT.clientSecret)
                .setRedirectURI(Defined.Client.REDIRECT_ADDRESS)
                .setCode(authAuthzResponse.getCode())
                .buildQueryMessage();
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthJSONAccessTokenResponse tokenResponse = oAuthClient.accessToken(authClientRequest);
        response.redirect(String.format("%s?%s=%s", Defined.Client.OPENID, OAuth.OAUTH_ACCESS_TOKEN, tokenResponse.getAccessToken()));
        return "";
    }
}