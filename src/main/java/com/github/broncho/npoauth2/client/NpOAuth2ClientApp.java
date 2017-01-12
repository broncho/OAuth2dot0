package com.github.broncho.npoauth2.client;

import com.github.broncho.npoauth2.client.handler.auth.ClientLoginHandler;
import com.github.broncho.npoauth2.client.handler.auth.RedirectHandler;
import com.github.broncho.npoauth2.client.handler.rs.OpenIdResourceHandler;
import com.github.broncho.npoauth2.client.handler.rs.UserResourceHandler;
import com.github.broncho.npoauth2.data.Defined;
import spark.Spark;

/**
 * OAuth2协议实现的客户端
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public class NpOAuth2ClientApp {
    
    
    public static void main(String[] args) {
        
        /**
         * 服务信息
         */
        Spark.port(Defined.Client.PORT);
        
        Spark.staticFileLocation(Defined.Client.SITE);
        
        
        /**
         * 请求授权 ->
         * 授权许可 <-
         */
        Spark.get(Defined.Client.LOGIN, new ClientLoginHandler());
        
        /**
         * 授权许可 ->
         * 访问令牌 <-
         */
        Spark.get(Defined.Client.REDIRECT, new RedirectHandler());
        
        /**
         * 获取用户OpenId
         */
        Spark.get(Defined.Client.OPENID, new OpenIdResourceHandler());
        
        
        /**
         * 访问临牌 ->
         * 资源信息 <-
         */
        Spark.get(Defined.Client.INFO, new UserResourceHandler());
        
        
        /**
         *初始化
         */
        Spark.awaitInitialization();
    }
}
