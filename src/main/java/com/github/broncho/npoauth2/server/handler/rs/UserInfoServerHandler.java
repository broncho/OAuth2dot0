package com.github.broncho.npoauth2.server.handler.rs;

import com.github.broncho.npoauth2.core.UserInfoService;
import com.github.broncho.npoauth2.core.impl.UserInfoServiceImpl;
import com.github.broncho.npoauth2.data.Defined;
import com.github.broncho.npoauth2.server.handler.ServerBaseHandler;
import spark.Request;
import spark.Response;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class UserInfoServerHandler extends ServerBaseHandler {
    
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String openId = request.queryParams(Defined.Param.OPEN_ID);
        return userInfoService.userInfo(openId);
    }
}
