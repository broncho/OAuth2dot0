package com.github.broncho.npoauth2.data;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public enum App {
    
    VDT("vdt-id", "vdt-secret"), PVD("pvd-id", "pvd-secret");
    
    public final String clientId;
    
    public final String clientSecret;
    
    App(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}