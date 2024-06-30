# spring-boot-redis

> https://zljin.github.io/2023/03/11/redis/

## preview

![](https://github.com/zljin/blogImage/blob/master/technical/redis_action_list.png?raw=true)


## implement

1. Redis的共享session

2. 缓存穿透，缓存雪崩

缓存穿透：查询的key在redis不存在,对应在数据库为空,此时若大量请求,则不经过缓存,直接访问db,操作多次IO,影响数据库性能。

如何解决？ 1.若查询结果为空,把空的查询结果的数据缓存起来,下次直接return 2. 布隆过滤器

缓存雪崩：Redis里面key值大面积失效,此时有大量的流量访问系统,大量流量直接访问数据库导致数据库宕机

如何解决？1. 永不过期,手动失效 2. 过期时间错开 3. 多缓存结合（Redis->memcache->database）

3. 分布式锁

```
原理:
setnx lockkey lockvalue  #执行成功,获得该锁,往下执行
expire lockkey 30 # 防死锁
{
    #业务代码段
}
del lockkey #执行结束,删除锁
# 其他进程继续竞争抢锁

如果临界值执行时间过长,在超时之内没有执行完也会有并发问题
1. 延长超时时间 2. 定时判断该线程是否执行结束,否则续上时间
```

4. 压测RedisIdWorker能够生成唯一id

5. 查询附近的商户 Geospatial todo

6. 网站访问量 HyperLogLog

7. 达人探店，点赞排行榜 ZSet todo

8. 用户共同关注，用Set数据结构，进行集合计算 todo

9. 批处理方案 jedis