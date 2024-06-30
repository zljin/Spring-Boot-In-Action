package com.zljin.config;

import cn.hutool.core.bean.BeanUtil;
import com.zljin.model.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("token:" + token);
        if (entries == null) {
            return false;
        }
        User user = BeanUtil.fillBeanWithMap(entries, new User(), false);
        UserHolder.saveUser(user);
        if (UserHolder.getUser() == null || UserHolder.getUser().getIsLogin() == "0") {
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
