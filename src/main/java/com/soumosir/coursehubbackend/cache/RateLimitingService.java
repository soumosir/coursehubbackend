package com.soumosir.coursehubbackend.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimitingService {

    private final int MAX_ATTEMPT = 120;
    private final LoadingCache<String, Integer> apiCallCache;

    public RateLimitingService() {
        super();
        apiCallCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public void apiCalled(String key) {
        int attempts = 0;
        try {
            attempts = apiCallCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        if (attempts <= MAX_ATTEMPT) {
            attempts++;
            apiCallCache.put(key, attempts);
        }

    }

    public boolean isRateLimited(String key) {
        try {
            return apiCallCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
