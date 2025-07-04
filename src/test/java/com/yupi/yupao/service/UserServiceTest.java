package com.yupi.yupao.service;

import com.yupi.yupao.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

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
}
