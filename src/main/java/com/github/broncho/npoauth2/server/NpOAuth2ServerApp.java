package com.github.broncho.npoauth2.server;

import com.github.broncho.npoauth2.data.Defined;
import com.github.broncho.npoauth2.server.handler.auth.AccessTokenHandler;
import com.github.broncho.npoauth2.server.handler.auth.AuthorizeHandler;
import com.github.broncho.npoauth2.server.handler.rs.OpenIdServerHandler;
import com.github.broncho.npoauth2.server.handler.auth.LoginServerHandler;
import com.github.broncho.npoauth2.server.handler.rs.UserInfoServerHandler;
import freemarker.template.Configuration;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * OAuth2协议实现的服务端
 * Author: ZhangXiao
 * Created: 2017/1/7
 */
public class NpOAuth2ServerApp {
    
    
    public static void main(String[] args) {
        
        /**
         * 服务信息
         */
        Spark.threadPool(Runtime.getRuntime().availableProcessors() * 2, Runtime.getRuntime().availableProcessors(), 30000);
        Spark.port(Defined.Server.PORT);
        
        /**
         *启用Debug界面
         */
        DebugScreen.enableDebugScreen();
        
        /*
         * 静态文件
         */
        Spark.staticFileLocation(Defined.Server.SITE);
        
        /**
         * 视图模版配置
         */
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(NpOAuth2ServerApp.class, "/template");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        
        /**
         * 客户端认证
         *
         * client_id
         * client_secret
         * redirect_uri
         */
        Spark.get(Defined.Server.LOGIN, new LoginServerHandler(), freeMarkerEngine);
        
        /**
         * 授权
         * username
         * password
         * client_id
         * client_secret
         * response_type
         * redirect_uri
         */
        Spark.post(Defined.Server.AUTH, new AuthorizeHandler());
        
        /**
         * 获取访问access_token
         */
        Spark.post(Defined.Server.TOKEN, new AccessTokenHandler());
        
        /**
         * 用户OpenId资源
         */
        Spark.get(Defined.Server.OPENID, new OpenIdServerHandler());
        
        /**
         * 用户信息资源
         */
        Spark.get(Defined.Server.INFO, new UserInfoServerHandler());
        
        /**
         *初始化
         */
        Spark.awaitInitialization();
    }
}