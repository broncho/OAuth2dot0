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
public class OpenIdResourceHandler extends ClientBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        logger.info("Request ==> {}.", request.queryString());
        String accessToken = request.queryParams(OAuth.OAUTH_ACCESS_TOKEN);
        //获取openId
        OAuthClientRequest authClientRequest = new OAuthClientRequest
                .AuthenticationRequestBuilder(String.format("%s?%s=%s", Defined.Server.OPENID_ADDRESS, OAuth.OAUTH_ACCESS_TOKEN, accessToken))
                .buildQueryMessage();
        URLConnectionClient connectionClient = new URLConnectionClient();
        OAuthResourceResponse resourceResponse = connectionClient.execute(
                authClientRequest,
                new HashMap<>(),
                "GET",
                OAuthResourceResponse.class);
        String openId = resourceResponse.getBody();
        response.redirect(String.format("%s?%s=%s&%s=%s", Defined.Client.INFO, Defined.Param.OPEN_ID, openId, OAuth.OAUTH_ACCESS_TOKEN, accessToken));
        return "";
    }
}