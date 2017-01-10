package com.github.broncho.npoauth2.server;

import com.github.broncho.npoauth2.data.Defined;
import com.github.broncho.npoauth2.server.handler.AccessTokenHandler;
import com.github.broncho.npoauth2.server.handler.AuthorizeHandler;
import com.github.broncho.npoauth2.server.handler.UserInfoHandler;
import spark.Spark;

/**
 * OAuth2协议实现的服务端
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public class NpOAuth2ServerApp {
    
    
    public static void main(String[] args) {
        
        Spark.port(Defined.Server.PORT);
        
        Spark.staticFileLocation(Defined.Server.SITE);
        
        /**
         * 客户端认证
         *
         * client_id
         * client_secret
         * redirect_uri
         */
        
        /**
         * 授权
         * username
         * password
         * client_id
         * client_secret
         * response_type
         * redirect_uri
         */
        Spark.get(Defined.Server.AUTH, new AuthorizeHandler());
        
        /**
         * 获取访问key
         */
        Spark.post(Defined.Server.TOKEN, new AccessTokenHandler());
        
        /**
         * 获取用户信息
         */
        Spark.get(Defined.Server.INFO, new UserInfoHandler());
        
        Spark.awaitInitialization();
    }
}
