package com.github.broncho.npoauth2.server.handler.auth;

import com.github.broncho.npoauth2.data.App;
import com.github.broncho.npoauth2.data.Defined;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.OAuth;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class LoginServerHandler implements TemplateViewRoute {
    
    
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        OAuthAuthzRequest authAuthzRequest = new OAuthAuthzRequest(request.raw());
        String clientId = authAuthzRequest.getParam(OAuth.OAUTH_CLIENT_ID);
        App app = App.appByClientId(clientId);
        if (app == null) {
            return new ModelAndView("", "");
        } else {
            String query = request.queryString();
            Map<String, Object> attribute = new HashMap<>();
            attribute.put("authorize_url", Defined.Server.AUTH + "?" + query);
            return new ModelAndView(attribute, "/login.ftl");
        }
    }
}
