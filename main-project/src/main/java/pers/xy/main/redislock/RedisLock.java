package pers.xy.main.redislock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLock {

    /**
     * REDIS_LOCK_
     */
    private final static String REDIS_LOCK_PREFIX = "REDIS";

    private final static long ACQUIRE_LOCK_INTERVAL_TIME = 50; // millisecond
    private final static long DEFAULT_LOCK_EXPIRE_TIME = 60 * 1000; // millisecond
    private final static long DEFAULT_ACQUIRE_LOCK_TIMEOUT = 11 * 1000; // millisecond

    // Lock context , save the lock owner id, when unlock need to check the owner id
    private ThreadLocal<String> lockContext = new ThreadLocal<String>();

    // If current thread had held the lock, it can get the lock again
    private ThreadLocal<Thread> ownerThread = new ThreadLocal<Thread>();

    // If current thread had held the lock, increase lock count
    private ThreadLocal<Integer> lockCount = new ThreadLocal<Integer>();

    public RedisLock() {
    }

    /**
     * Not block and not retry, will return immediately<p>
     * Note: can not use lock(key1) and lock(key2) at same time and same methods;
     * @param key
     */
    public boolean lock(String key) throws Exception {
        return acquire(key, DEFAULT_LOCK_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * Will blocking and retry to get lock<p>
     * Note: can not use lock(key1) and lock(key2) at same time and same methods;
     * @param key
     * @return
     * @throws Exception
     */
    public boolean tryLock(String key) throws Exception {
        return tryLock(key, DEFAULT_LOCK_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * Will blocking and retry to get lock , and the lock will expire after "expireTime"<p>
     * Note: can not use lock(key1) and lock(key2) at same time and same methods;
     * @param key
     * @param expireTime lock expire time
     * @param unit
     * @return
     * @throws Exception
     */
    public boolean tryLock(String key, long expireTime, TimeUnit unit) throws Exception {

        long acquireTime = System.currentTimeMillis();
        long acquireTimeout = acquireTime + DEFAULT_ACQUIRE_LOCK_TIMEOUT;

        while (acquireTimeout >= System.currentTimeMillis()) {
            boolean result = acquire(key, expireTime, unit);
            if(result) {
                return true;
            }
            Thread.sleep(ACQUIRE_LOCK_INTERVAL_TIME);
        }
        throw new Exception(String.format("Try to get distributed lock time out, key=[%s]", key));
    }

    private boolean acquire(String key, long expireTime, TimeUnit unit) throws Exception {
        String owerId = this.getOwerId();
        Thread t = Thread.currentThread();
        //if ("OK".equalsIgnoreCase(RedisUtils.set(this.getLockKey(key), owerId, unit.toSeconds(expireTime)))) {
        if(true){
            lockContext.set(owerId);
            lockCount.set(1);
            ownerThread.set(t);
            return true;
        } else if (ownerThread.get() != null && ownerThread.get() == t) {
            lockCount.set(lockCount.get() + 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method must invoke in finally statement
     * @param key
     * @return
     * @throws Exception
     */
    public boolean unlock(String key) throws Exception {
        String lockKey = this.getLockKey(key);

        if (lockContext.get() == null || lockCount.get() == null) {
           // log.info("No lock held for key=[{}].", lockKey);
            return false;
        }
        if (lockCount.get() - 1 == 0) {
            try {
                // if not exist the lockKey in redis, the lockKey may has been expired
//                if (!RedisUtils.exists(lockKey)) {
//                    throw new Exception(String.format("Distributed lock unlock exception, key=[%s] has been expired", lockKey));
//                }
                long releaseTime = System.currentTimeMillis();
                long releaseTimeout = releaseTime + DEFAULT_ACQUIRE_LOCK_TIMEOUT;

                String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

                while(releaseTimeout >= System.currentTimeMillis()) {

                    // Delete lock by lua script to make the operation atomicity
                    //Object result = RedisUtils.eval(luaScript, Arrays.asList(lockKey), Arrays.asList(lockContext.get()));
                    Object result = null;
                    if (((Long) result) == 1L) {
                        //log.debug("Unlock key[{}] by thread[{}] ", lockKey, Thread.currentThread().getName());
                        return true;
                    }
                }
                throw new Exception(String.format("Distributed lock unlock time out, key=[%s]", lockKey));
            } finally {
                lockContext.remove();
                ownerThread.remove();
                lockCount.remove();
            }
        } else {
            lockCount.set(lockCount.get() - 1);
            return true;
        }
    }

    private String getLockKey(String key) {
        if (key == "" || key == null) {
            return null;
        }
        return REDIS_LOCK_PREFIX + key;
    }

    private String getOwerId() {
        StringBuilder builder = new StringBuilder();
        builder.append(Thread.currentThread().getName());
        builder.append("_");
        builder.append(UUID.randomUUID().toString());
        return builder.toString();
    }
}
