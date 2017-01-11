package com.github.broncho.npoauth2.data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ZhangXiao
 * Created: 2017/1/11
 */
public class User {
    
    public static final Set<User> USER_SET = new HashSet<>();
    
    static {
        USER_SET.add(new User(UUID.randomUUID().toString(), "LiBai"));
        USER_SET.add(new User(UUID.randomUUID().toString(), "DuFu"));
        USER_SET.add(new User(UUID.randomUUID().toString(), "WangWei"));
        USER_SET.add(new User(UUID.randomUUID().toString(), "LiQingZhao"));
    }
    
    private String id;
    
    private String name;
    
    private String password;
    
    private User(String id, String name) {
        this.id = id;
        this.name = name;
        this.password = name;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        User user = (User) o;
        
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }
    
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
    
    /**
     * 验证用户
     *
     * @param username
     * @param password
     * @return
     */
    public static Optional<User> validUser(String username, String password) {
        User target = null;
        for (User user : USER_SET) {
            if (user.getName().equals(username) && user.password.equals(password)) {
                target = user;
                break;
            }
        }
        return Optional.ofNullable(target);
    }
}