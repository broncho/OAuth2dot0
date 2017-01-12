package com.github.broncho.npoauth2.server.handler.rs;

import com.github.broncho.npoauth2.core.UserInfoService;
import com.github.broncho.npoauth2.core.impl.UserInfoServiceImpl;
import com.github.broncho.npoauth2.data.Defined;
import com.github.broncho.npoauth2.server.handler.ServerBaseHandler;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class UserInfoServerHandler extends ServerBaseHandler {
    
    private Logger logger = LoggerFactory.getLogger(OpenIdServerHandler.class);
    
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        logger.info("Request ==> {}.", request.queryString());
        
        String openId = request.queryParams(Defined.Param.OPEN_ID);
        String accessToken = request.queryParams(OAuth.OAUTH_ACCESS_TOKEN);
        if (oAuthService.checkAccessToken(accessToken)) {
            return userInfoService.userInfo(openId);
        } else {
            OAuthResponse oauthResponse = OAuthRSResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                    .buildHeaderMessage();
            return oauthResponse.getBody();
        }
    }
}
