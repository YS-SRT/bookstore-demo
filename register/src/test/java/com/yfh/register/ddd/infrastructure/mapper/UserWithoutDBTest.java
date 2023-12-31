package com.yfh.register.ddd.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yfh.register.ddd.infrastructure.model.UserInfoDO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@SpringBootTest
public class UserWithoutDBTest {

    @MockBean
    private UserInfoMapper userInfoMapper;

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setup() {

        podamFactory = new PodamFactoryImpl();
    }

    @Test 
    @DisplayName("用户数据插入测试 - Mock")
    public void insertUserTest(){
        Mockito.when(userInfoMapper.insert(Mockito.any(UserInfoDO.class))).thenReturn(1);
        
        UserInfoDO ui = podamFactory.manufacturePojo(UserInfoDO.class);
        int result = userInfoMapper.insert(ui);
        assertEquals(result, 1, "Wrong result in InsertUserTest");
    }
    
}
