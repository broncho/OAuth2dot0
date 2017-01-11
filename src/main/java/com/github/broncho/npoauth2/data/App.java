package com.github.broncho.npoauth2.data;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public enum App {
    
    VDT("vdt-id", "vdt-secret", "user_info"), PVD("pvd-id", "pvd-secret", "user_info");
    
    public final String clientId;
    
    public final String clientSecret;
    
    public final String scope;
    
    App(String clientId, String clientSecret, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }
    
    public static App appByClientId(String clientId) {
        for (App app : App.values()) {
            if (app.clientId.equals(clientId)) {
                return app;
            }
        }
        return null;
    }
}