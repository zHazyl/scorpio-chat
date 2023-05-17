package com.tnh.authservice.repository;

import com.tnh.authservice.domain.User;
import com.tnh.authservice.domain.UserRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRedisRepository {
    private final RedisTemplate template;

    public UserRedisRepository(RedisTemplate template) {
        this.template = template;
    }

    public static final String HASH_KEY = "UserRedis";

    public User save(User user) {
        var userRedis = new UserRedis(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirst_name(),
                user.getLast_name());
        template.opsForHash().put(HASH_KEY, user.getId(), userRedis);
        return user;
    }

    public User findUserById(String id) {
        UserRedis userRedis = (UserRedis) template.opsForHash().get(HASH_KEY, id);
        User user = new User();
        user.setUsername(userRedis.getUsername());
        user.setEmail(userRedis.getEmail());
        user.setFirst_name(userRedis.getFirstName());
        user.setLast_name(userRedis.getLastName());
        return user;
    }

    public void deleteUser(String id) {
        template.opsForHash().delete(HASH_KEY, id);
    }

}
