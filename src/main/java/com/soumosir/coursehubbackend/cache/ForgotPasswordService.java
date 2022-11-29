package com.soumosir.coursehubbackend.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class ForgotPasswordService {


    private final LoadingCache<String, String> passwordCodeCache;

    public ForgotPasswordService() {
        super();
        passwordCodeCache = CacheBuilder.newBuilder().
                expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
                    public String load(String key) {
                        return String.valueOf(0);
                    }
                });
    }

    private String generateCode() {
        String random = UUID.randomUUID().toString().replaceAll("-", "");
        return random;
    }

    public String setRandomCode(String email) {
        String random = generateCode();
        passwordCodeCache.put(email, random);
        return random;
    }

    public boolean verify(String email, String code) {
        try {
            if (passwordCodeCache.get(email).equals(code)) {
                return true;
            }
        } catch (ExecutionException e) {
            return false;
        }
        return false;
    }
}
