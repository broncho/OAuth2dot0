package com.github.broncho.npoauth2.client.handler.rs;

import com.github.broncho.npoauth2.client.handler.ClientBaseHandler;
import com.github.broncho.npoauth2.data.Defined;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import spark.Request;
import spark.Response;

import java.util.HashMap;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class UserResourceHandler extends ClientBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        logger.info("Request ==> {}.", request.queryString());
        
        OAuthClientRequest authClientRequest = new OAuthClientRequest
                .AuthenticationRequestBuilder(
                String.format("%s?%s=%s&%s=%s",
                        Defined.Server.INFO_ADDRESS,
                        Defined.Param.OPEN_ID, request.queryParams(Defined.Param.OPEN_ID),
                        OAuth.OAUTH_ACCESS_TOKEN, request.queryParams(OAuth.OAUTH_ACCESS_TOKEN)
                ))
                .buildQueryMessage();
        URLConnectionClient connectionClient = new URLConnectionClient();
        OAuthResourceResponse resourceResponse = connectionClient.execute(
                authClientRequest,
                new HashMap<>(),
                "GET",
                OAuthResourceResponse.class);
        return resourceResponse.getBody();
    }
}
