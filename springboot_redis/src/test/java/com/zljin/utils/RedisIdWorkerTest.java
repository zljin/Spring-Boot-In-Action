package com.zljin.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class RedisIdWorkerTest {

    @Resource
    private RedisIdWorker redisIdWorker;

    private ExecutorService es = Executors.newFixedThreadPool(500);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "customRedisTemplate")
    private RedisTemplate redisTemplate;


    //压测RedisIdWorker能够生成唯一id
    @Test
    void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);

        Set<String> countSet = Collections.synchronizedSet(new HashSet<>());

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
                countSet.add("" + id);
            }
            latch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
        System.out.println(countSet.size());//expected 30000
    }

    //适用于快速合并数据去重，允许一定误差值，效率很高
    @Test
    void testHyperLogLog() {
        String[] values = new String[1000];
        int j = 0;
        for (int i = 0; i < 1000000; i++) {
            j = i % 1000;
            values[j] = "user_" + i;
            if(j == 999){
                // 发送到Redis
                stringRedisTemplate.opsForHyperLogLog().add("hl2", values);
            }
        }
        // 统计数量
        Long count = stringRedisTemplate.opsForHyperLogLog().size("hl2");
        System.out.println("count = " + count);
    }

    @Test
    void testExecuteBatch(){
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (int i = 0; i < 1000; i++) {
                String key = "key_" + i;
                String value = "value_" + i;
                connection.set(key.getBytes(), value.getBytes());
            }
            return null;
        });
    }

    @Test
    void testRetrieveBatch(){
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String key = "key_" + i;
            keys.add(key);
        }
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (String key : keys) {
                connection.get(key.getBytes());
            }
            return null;
        });
    }

}