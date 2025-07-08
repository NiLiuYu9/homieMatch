package com.yupi.yupao.service;

import com.yupi.yupao.model.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    ExecutorService executorService = new ThreadPoolExecutor(40,60,10000, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10000));

    @Test
    public void searchUserByTagsBysqlTest(){
        List<String> selectTagNameList = Arrays.asList("java");
        List<User> userList = userService.searchUsersByTagsBySQL(selectTagNameList);
        for (User user :
                userList) {
            System.out.println("the user = "+user);
        }
        Assert.assertNotNull(userList);

    }

    @Test
    public void searchUserByTagsByMemoryTest(){
        List<String> selectTagNameList = Arrays.asList("java");
        List<User> userList = userService.searchUserByTagByMemory(selectTagNameList);
        for (User user :
                userList) {
            System.out.println("the user = "+user);
        }
        Assert.assertNotNull(userList);

    }

    /**
     * 并发批量插入用户
     */
    @Test
    public void doConcurrencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 分十组
        int batchSize = 5000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("nly");
                user.setUserAccount("nly12");
                user.setAvatarUrl("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
                user.setGender(0);
                user.setUserPassword("12345678");
                user.setPhone("123");
                user.setEmail("123@qq.com");
                if (i%2==0) {
                    user.setTags("[\"java\"]");
                } else user.setTags("[\"python\"]");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("11111");
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            // 异步执行,把任务提交到任务队列
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName: " + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        // 20 秒 10 万条
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
