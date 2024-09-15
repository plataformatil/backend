package com.til.users.application.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RegisterAttemptService {
    private final int MAX_ATTEMPT = 5;
    private final long LOCK_TIME_DURATION = TimeUnit.MINUTES.toMillis(15);
    private ConcurrentHashMap<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Long> lockTimeCache = new ConcurrentHashMap<>();

    public void registrationSucceeded(String key) {
        attemptsCache.remove(key);
        lockTimeCache.remove(key);
    }

    public void registrationFailed(String key) {
        int attempts = attemptsCache.getOrDefault(key, 0);
        attempts++;
        attemptsCache.put(key, attempts);
        if (attempts >= MAX_ATTEMPT) {
            lockTimeCache.put(key, System.currentTimeMillis());
        }
    }

    public boolean isBlocked(String key) {
        if (!lockTimeCache.containsKey(key)) {
            return false;
        }
        long lockTime = lockTimeCache.get(key);
        if (System.currentTimeMillis() - lockTime > LOCK_TIME_DURATION) {
            lockTimeCache.remove(key);
            return false;
        }
        return true;
    }
}

