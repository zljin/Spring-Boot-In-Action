package com.zljin.controller;

import com.zljin.model.Account;
import com.zljin.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/es")
public class ESController {

    @Resource
    AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody List<Account> accountList) {
        if (accountList != null && !accountList.isEmpty()) {
            accountList.forEach(account -> accountRepository.save(account));
        }
        return ResponseEntity.status(200).body("success");
    }

    @GetMapping
    public ResponseEntity<List<Account>> search(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> all = accountRepository.findAll(pageable);
        List<Account> collect = all.stream().collect(Collectors.toList());
        return ResponseEntity.status(200).body(collect);
    }

}
