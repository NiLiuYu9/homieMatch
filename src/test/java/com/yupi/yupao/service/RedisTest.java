package com.yupi.yupao.service;

import com.yupi.yupao.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author: shayu
 * @date: 2022/12/10
 * @ClassName: yupao-backend01
 * @Description:    Redis测试
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;


    @Test
    void test() {
        //初始化给未登录用户推荐的用户，永不过期
        //todo 修改成定时任务，定时更新给未登录用户的推荐
//        Page<User> page = new Page<>(1, 8);
//        Page<User> userPage = userService.page(page, new QueryWrapper<>());
//            redisTemplate.opsForValue().set("homieMatch:user:recommend:normal", userPage);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("shayuString", "fish");
        valueOperations.set("shayuInt", 1);
        valueOperations.set("shayuDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("shayu");
        valueOperations.set("shayuUser", user);

        // 查
        Object shayu = valueOperations.get("shayuString");
        Assertions.assertTrue("fish".equals((String) shayu));
        shayu = valueOperations.get("shayuInt");
        Assertions.assertTrue(1 == (Integer) shayu);
        shayu = valueOperations.get("shayuDouble");
        Assertions.assertTrue(2.0 == (Double) shayu);
        System.out.println(valueOperations.get("shayuUser"));
        valueOperations.set("shayuString", "fish");

        redisTemplate.delete("shayuString");
    }

}
