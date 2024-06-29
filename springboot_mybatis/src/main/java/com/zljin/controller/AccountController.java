package com.zljin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zljin.config.BankCardNumberGenerator;
import com.zljin.mapper.AccountMapper;
import com.zljin.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<Object> select(@RequestParam(value = "accountNumber", required = false) String accountNumber,
                                         @RequestParam(value = "pageCurrent", required = false, defaultValue = "1") Integer pageCurrent,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "100") Integer pageSize) {

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(accountNumber)) {
            queryWrapper.eq("account_number", accountNumber);
        }
        IPage<Account> page = new Page<>(pageCurrent, pageSize);
        IPage<Account> accountIPage = accountMapper.selectPage(page, queryWrapper);
        return ResponseEntity.status(201).body(accountIPage);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody List<Account> accountList) {
        accountList.forEach(account -> {
            account.setAccountNumber(BankCardNumberGenerator.generateAccountNumber());
            accountMapper.insert(account);
        });
        return ResponseEntity.status(201).body("ok");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Account account) {
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account_number", account.getAccountNumber());
        accountMapper.update(account, updateWrapper);
        return ResponseEntity.status(201).body("ok");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String accountNumber) {
        accountMapper.deleteById(accountNumber);
        return ResponseEntity.status(201).body("ok");
    }


}
