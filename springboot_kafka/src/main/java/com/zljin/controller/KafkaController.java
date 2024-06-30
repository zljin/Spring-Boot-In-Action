package com.zljin.controller;

import com.google.gson.Gson;
import com.zljin.config.KafkaSendUtil;
import com.zljin.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    KafkaSendUtil kafkaSendUtil;

    @Value("${kafka.topic.one}")
    String myTopic;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody Account account) {
        kafkaSendUtil.send(myTopic, new Gson().toJson(account));
        return ResponseEntity.status(201).body("ok");
    }
}
