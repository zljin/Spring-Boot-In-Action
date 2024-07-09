package com.zljin.controller;

import com.zljin.model.Account;
import com.zljin.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
public class HealthController {

    @Resource
    AccountService accountService;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(200).body("success");
    }

    @GetMapping("/user/{userId}")
    public Mono<Account> queryUserById(@PathVariable Long userId) {
        return Mono.just(accountService.queryUserById(userId));
    }
}
