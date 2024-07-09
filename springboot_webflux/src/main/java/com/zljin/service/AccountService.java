package com.zljin.service;

import com.zljin.model.Account;
import com.zljin.util.Builder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    /** 模拟用户存储 */
    private static final Map<Long, Account> USER_MAP = new HashMap<>();

    static {
        Account leonard = Builder.of(Account::new)
                .with(Account::setAccountNumber, "1234455666454")
                .with(Account::setFirstname, "leonard").builder();
        USER_MAP.put(1L, leonard);
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public Account queryUserById(Long userId) {
        return USER_MAP.get(userId);
    }
}
