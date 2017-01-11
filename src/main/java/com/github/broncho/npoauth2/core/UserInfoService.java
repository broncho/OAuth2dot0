package com.github.broncho.npoauth2.core;

import com.github.broncho.npoauth2.data.UserInfo;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public interface UserInfoService {
    
    
    /**
     * 根据用户openId获取信息
     *
     * @param openId
     * @return
     */
    UserInfo userInfo(String openId);
    
}
