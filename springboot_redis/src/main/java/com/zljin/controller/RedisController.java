package com.zljin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zljin.config.UserHolder;
import com.zljin.model.Account;
import com.zljin.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final String ACCOUNT_CACHE_DATA = "account_cache_data:";
    private static final String TOKEN_PREFIX = "token:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "customRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/gen-code")
    public ResponseEntity<String> genCode(@RequestParam String phone) {
        String code = RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue().set("login:code:" + phone, code, 2L, TimeUnit.MINUTES);
        return ResponseEntity.ok(code);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String phone, @RequestParam String genCode) {
        String s = stringRedisTemplate.opsForValue().get("login:code:" + phone);
        User user = new User(phone, genCode.equals(s) ? "1" : "0");
        Map<String, Object> userMap = BeanUtil.beanToMap(user, false, false);
        String token = UUID.randomUUID().toString(true);
        stringRedisTemplate.opsForHash().putAll(TOKEN_PREFIX + token, userMap);
        stringRedisTemplate.expire(TOKEN_PREFIX + token, 36000L, TimeUnit.MINUTES);
        return ResponseEntity.ok(genCode.equals(s) ? token : "");
    }

    @GetMapping("/account")
    public ResponseEntity<Account> meAccount(@RequestParam String phone, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!isValidToken(token) || !UserHolder.getUser().getPhone().equals(phone)) {
            return ResponseEntity.ok(new Account());
        }
        Account account = (Account) redisTemplate.opsForValue().get(ACCOUNT_CACHE_DATA + phone);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        account = dbSearchSample(phone);

        if (account == null) {
            redisTemplate.opsForValue().set(ACCOUNT_CACHE_DATA + phone, "", 2L, TimeUnit.MINUTES);
            return ResponseEntity.ok(new Account());
        }
        redisTemplate.opsForValue().set(ACCOUNT_CACHE_DATA + phone, account, 20L, TimeUnit.MINUTES);
        return ResponseEntity.ok(account);
    }

    private boolean isValidToken(String token) {
        if(StrUtil.isEmpty(token)){
            return false;
        }
        Long expire = stringRedisTemplate.getExpire(TOKEN_PREFIX + token, TimeUnit.SECONDS);
        return expire != null && expire > 0;
    }


    private Account dbSearchSample(String phone) {
        Account account = new Account();
        account.setPhone(phone);
        account.setAccountNumber("12433433");
        account.setBalance(1000);
        return account;
    }

}
