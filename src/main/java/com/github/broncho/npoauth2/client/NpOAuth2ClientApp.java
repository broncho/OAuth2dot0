package com.github.broncho.npoauth2.client;

import com.github.broncho.npoauth2.client.handler.LoginHandler;
import com.github.broncho.npoauth2.client.handler.RedirectHandler;
import com.github.broncho.npoauth2.data.Defined;
import spark.Spark;

/**
 * OAuth2协议实现的客户端
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class NpOAuth2ClientApp {
    
    public static void main(String[] args) {
        
        Spark.port(Defined.Client.PORT);
        
        Spark.staticFileLocation(Defined.Client.SITE);
        
        /**
         * 请求授权 ->
         * 授权许可 <-
         */
        Spark.get(Defined.Client.LOGIN, new LoginHandler());
        
        /**
         * 授权许可 ->
         * 访问令牌 <-
         */
        Spark.get(Defined.Client.REDIRECT, new RedirectHandler());
        
        /**
         * 访问临牌 ->
         * 资源信息 <-
         */
        
        Spark.awaitInitialization();
    }
}
