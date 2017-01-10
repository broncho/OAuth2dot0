package com.github.broncho.npoauth2.client.handler;

import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.Defined;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import spark.Request;
import spark.Response;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class LoginHandler  extends ClientBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---LoginHandler---");
        try {
            OAuthClientRequest authClientRequest = OAuthClientRequest
                    .authorizationLocation(Defined.Server.AUTH_ADDRESS)
                    .setClientId(App.VDT.clientId)
                    .setResponseType(ResponseType.CODE.toString())
                    .setRedirectURI(Defined.Client.REDIRECT_ADDRESS)
                    .buildQueryMessage();
            response.redirect(authClientRequest.getLocationUri());
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return "";
    }
}