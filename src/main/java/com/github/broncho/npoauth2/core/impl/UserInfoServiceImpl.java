package com.github.broncho.npoauth2.core.impl;

import com.github.broncho.npoauth2.core.UserInfoService;
import com.github.broncho.npoauth2.data.User;
import com.github.broncho.npoauth2.data.UserInfo;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class UserInfoServiceImpl implements UserInfoService {
    
    
    @Override
    public UserInfo userInfo(String openId) {
        for (User user : User.USER_SET) {
            if (user.getId().equals(openId)) {
                return new UserInfo(user.getId(), user.getName());
            }
        }
        return null;
    }
}
