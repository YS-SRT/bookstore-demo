package com.yfh.bsecurity.security;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yfh.bsecurity.entity.UserDO;
import com.yfh.bsecurity.mapper.BSUserDetailsMapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BSUserDetailsServiceImpl implements UserDetailsService {
  
    @Resource
    private BSUserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDO> qm = new QueryWrapper<>();
        List<UserDO> userList = userDetailsMapper.selectList(qm.eq("login_name", username));
        if(!CollectionUtils.isEmpty(userList) && userList.size() == 1){
                
            return new BSUserDetails(userList.get(0).getLoginName(), 
                                     userList.get(0).getPassword(), 
                                     userList.get(0).getId(),
                                     userList.get(0).getUserType());
            
        }
        
        log.info("Not Existed User with LoginName: " + username);
        return null;

    }

    
}
