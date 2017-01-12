### OAuth2dot0

采用`Apache oltu`实现`OAuth2.0`的服务端示例

#### 案例使用流程

+ 启动服务端`com.github.broncho.npoauth2.server.NpOAuth2ServerApp` 端口`8090`

+ 启动客户端`com.github.broncho.npoauth2.client.NpOAuth2ClientApp` 端口`8091`

+ 访问客户端`http://127.0.0.1:8091/index.html`

+ 点击`第三方服务认证`超链接，跳转到服务端的用户登录页面

+ 输入`用户名：LiBai 密码:LiBai`，点击提交

+ 跳转到客户端`用户信息`接口，返回用户信息


#### OAuth2.0的资料

+ [OAuth2.0协议](https://oauth.net/2/)

+ [OAuth2.0简介](http://wiki.open.qq.com/wiki/mobile/OAuth2.0%E7%AE%80%E4%BB%8B)

+ [理解OAuth2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)

+ [Github Developer](https://developer.github.com/v3/)