package com.geek.redis.utils;

import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Proxy;

/**
 * @Author: siyan.liu
 * @Date: 2021/12/5
 */
public class RedisClientFactory {
    private ShardedJedisPool shardedJedisPool;

    public RedisClientFactory(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public IRedisClient createdRedisClient() {
        return (IRedisClient) Proxy.newProxyInstance(IRedisClient.class.getClassLoader(),
                new Class[]{IRedisClient.class}, new RedisProxy(shardedJedisPool, new RedisService()));
    }
}
