package com.yfh.register.ddd.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;


import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
public class UserMapperTest {
    
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource 
    private BeanSearcher beanSearcher;

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setup(){

        podamFactory = new PodamFactoryImpl();
    }


    @ParameterizedTest
    @DisplayName("用户数据插入测试")
    @CsvSource({ "1,0,2,0", "0,1,1,0", "2,2,1,0" })
    public  void insertUserTest(int userType, int userSex, int userStatus, int delFlag){
        UserInfoDO ui = podamFactory.manufacturePojo(UserInfoDO.class);
        ui.setId(0)
          .setUserType(userType)
          .setSex(userSex)
          .setStatus(userStatus)
          .setEmail(ui.getEmail() + "@qq.com")
          .setDelFlag(delFlag)
          .setVersion(0)
          .setCreateTime(null)
          .setUpdateTime(null)
          .setLoginTime(null);
        int result = userInfoMapper.insert(ui);
        assertEquals(result, 1, "Wrong in InsertUserTest");
    }

    @ParameterizedTest
    @ValueSource(strings = { "tom", "jeff", "tony","ivy","ben","moly" })
    public void updateUserTest(String name){
        
    }
}
