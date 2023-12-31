package com.yfh.register.ddd.adapter.vo.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.yfh.register.ddd.adapter.dto.UserRegisterDTO;
import com.yfh.register.ddd.adapter.searchvo.UserSearchVO;
import com.yfh.register.ddd.adapter.vo.UserPaidInfoVO;
import com.yfh.register.ddd.adapter.vo.UserVO;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;
import com.yfh.register.ddd.infrastructure.model.UserPaidInfoDO;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

public class UserVOConverter {

    public static UserVO toUserVO(UserInfoDO userInfoDO){
        if(!ObjectUtils.isEmpty(userInfoDO)){
		   UserVO userVO = BeanUtils.instantiateClass(UserVO.class);
           BeanUtils.copyProperties(userInfoDO, userVO);
           return userVO;
        }
        return null;
    }

    public static UserSearchVO toUserSearchVO(UserInfoDO userInfoDO) {
        if(!ObjectUtils.isEmpty(userInfoDO)){

           UserSearchVO userSearchVO = BeanUtils.instantiateClass(UserSearchVO.class);
           BeanUtils.copyProperties(userInfoDO, userSearchVO);
           return userSearchVO;
        }
        return null;
    }

    public static UserPaidInfoVO toUserPaidInfoVO(UserPaidInfoDO userPaidInfoDO){
        if(!ObjectUtils.isEmpty(userPaidInfoDO)){
             UserPaidInfoVO userPaidInfoVO = BeanUtils.instantiateClass(UserPaidInfoVO.class);
             BeanUtils.copyProperties(userPaidInfoDO, userPaidInfoVO);
        }
        return null;
    }

    public static UserSearchVO addIntoUserSearchVO(UserSearchVO originalVO, List<UserPaidInfoDO> doList){
       if(!CollectionUtils.isEmpty(doList)){
          originalVO.setUserPaidInfo(doList.stream().map(UserVOConverter::toUserPaidInfoVO).collect(Collectors.toList()));
        }
        return originalVO;
    }

    public static UserInfoDO toUserInfoDO(UserRegisterDTO registerDTO){

         if(!ObjectUtils.isEmpty(registerDTO)){
		     UserInfoDO userInfoDO = BeanUtils.instantiateClass(UserInfoDO.class);
             BeanUtils.copyProperties(registerDTO, userInfoDO);
             return userInfoDO;
        }
        return null;
    }
    
}
