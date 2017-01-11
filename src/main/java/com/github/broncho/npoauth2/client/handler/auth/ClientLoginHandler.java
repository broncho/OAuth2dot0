package com.github.broncho.npoauth2.client.handler.auth;

import com.github.broncho.npoauth2.client.handler.ClientBaseHandler;
import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.Defined;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import spark.Request;
import spark.Response;

import java.util.UUID;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class ClientLoginHandler extends ClientBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("---ClientLoginHandler---");
        try {
            OAuthClientRequest authClientRequest = OAuthClientRequest
                    .authorizationLocation(Defined.Server.LOGIN_ADDRESS)
                    .setClientId(App.VDT.clientId)
                    .setState(UUID.randomUUID().toString())
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