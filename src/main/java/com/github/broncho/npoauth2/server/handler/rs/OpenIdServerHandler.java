package com.github.broncho.npoauth2.server.handler.rs;

import com.github.broncho.npoauth2.server.handler.ServerBaseHandler;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class OpenIdServerHandler extends ServerBaseHandler {
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        
        logger.info("Request ==> {}.", request.queryString());
        
        //资源请求
        OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request.raw(), ParameterStyle.QUERY);
        String accessToken = oauthRequest.getAccessToken();
        //验证Access Token
        if (oAuthService.checkAccessToken(accessToken)) {
            return oAuthService.getOpenIdByAccessToken(accessToken);
        } else {
            // 如果不存在/过期了，返回未验证错误，需重新验证
            OAuthResponse oauthResponse = OAuthRSResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                    .buildHeaderMessage();
            return oauthResponse.getBody();
        }
    }
}