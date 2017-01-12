package com.github.broncho.npoauth2.data;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class UserInfo {
    
    public final String id;
    
    public final String name;
    
    public UserInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
