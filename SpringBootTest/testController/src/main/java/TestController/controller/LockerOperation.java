package TestController.controller;

import java.util.Arrays;

@Component
public class LockerOperation {
    private static RedisScript<Long> MUTEX_RELEASE_SCRIPT =
            new DefaultRedisScript<>("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Long.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("locking")
    private String prefix;

    public boolean tryLock(String name, String owner, int second){
        String key = mapKey(name);
        Boolean ok = redisTemplate.opsForValue().setIfAbsent(key, owner);
        if(ok){
            return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        }
        return false;
    }

    public boolean unlock(String name, String owner){
        String key = mapKey(name);
        Long ret = redisTemplate.execute(MUTEX_RELEASE_SCRIPT, Collections.singletonList(key), owner);
        return ret != null && ret == 1;
    }
    private String mapKey(String name){
        return StringUtils.join(Arrays.asList(prefix, "mutex", name), ":");
    }
}
