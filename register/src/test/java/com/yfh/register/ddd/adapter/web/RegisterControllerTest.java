package com.yfh.register.ddd.adapter.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yfh.register.ddd.adapter.dto.UserRegisterDTO;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {  //整体测试
    
    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    RegisterController registerController;

    @MockBean
    UserInfoMapper userInfoMapper;

    @MockBean
    QueryWrapper<UserInfoDO> queryWrapper;

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setup() {

        podamFactory = new PodamFactoryImpl();
    }

    @Test
    public void testNotNull(){
        Assertions.assertThat(registerController).isNotNull();
    }
    
    @Test
    public void getCodeImgTest() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/oauth/getCodeImg")
                                                   .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testRegister() throws Exception{
        Mockito.when(userInfoMapper.selectCount(queryWrapper)).thenReturn(0L);
        Mockito.when(userInfoMapper.insert(Mockito.any(UserInfoDO.class))).thenReturn(1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/oauth/register")
                                                   .contentType(MediaType.APPLICATION_JSON)
                                                   .content(JSONUtil.toJsonStr(podamFactory.manufacturePojo(UserRegisterDTO.class))))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }

   

    
}
