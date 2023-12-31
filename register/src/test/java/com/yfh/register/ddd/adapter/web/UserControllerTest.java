package com.yfh.register.ddd.adapter.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.yfh.bsecurity.token.JwtTokenService;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.infrastructure.mapper.UserPaidInfoMapper;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;
import com.yfh.register.ddd.infrastructure.model.UserPaidInfoDO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Slf4j
@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = SpringSecurityTestConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenService jwtTokenService;

    @MockBean
    private UserDetailsService userDetailsService;
    
    @Autowired
    UserController userController;
    
    @MockBean
    UserInfoMapper userInfoMapper;

    @MockBean
    UserPaidInfoMapper userPaidInfoMapper;

    @Autowired
    HttpServletRequest request;

    @MockBean
    BeanSearcher beanSearcher;

    @MockBean
    QueryWrapper<UserPaidInfoDO> queryWrapperUPID; 

    private static PodamFactory podamFactory;

    @BeforeAll
    public static void setup() {

        podamFactory = new PodamFactoryImpl();
    }
    
    
    private String generateToken(int userId, String loginName, String pwd,int userType){
        List<GrantedAuthority> authList = null;
        switch (userType) {
                case 99:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,ROLE_ADMIN");
                    break;
                case 1:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("VIP,ROLE_VIP");
                    break;
                case 2:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("LTS,ROLE_LTS");
                    break;
                case  0:
                default:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("USER,ROLE_USER");
                    break;

        }


        request.setAttribute("bsecurity.token.userId", userId);
        request.setAttribute("bsecurity.token.loginName", loginName);
        
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(new User(loginName, pwd, authList));
        return jwtTokenService.createToken(userId, userType, loginName, 60*60*2);
        
    }


    @Test
    //@WithUserDetails("admin@mail.com")
    public void adminGetUserById() throws Exception{
        String token = generateToken(1,"admin0","123456", 99);
        Mockito.when(userInfoMapper.selectById(1)).thenReturn(podamFactory.manufacturePojo(UserInfoDO.class));
        Mockito.when(userPaidInfoMapper.selectList(queryWrapperUPID)).thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/auth//getUserById?userId=1")
                                                   .header("AUTHORIZATION", "Bearer " + token)
                                                   .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());

    }




   
    
}
