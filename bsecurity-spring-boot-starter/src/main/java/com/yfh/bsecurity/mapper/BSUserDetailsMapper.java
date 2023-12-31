package com.yfh.bsecurity.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yfh.bsecurity.entity.UserDO;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BSUserDetailsMapper extends Mapper<UserDO> {


    @Select("select id, login_name, password, user_type from user_info ${ew.customSqlSegment}")
    List<UserDO> selectList(@Param(Constants.WRAPPER) QueryWrapper<UserDO> wrapper);

    @Update("update user_info set login_ip=#{loginIp}, login_time=#{loginTime} where id=#{id}")
    int updateLoginInfoById(@Param("loginIp") String loginIp, @Param("loginTime") LocalDateTime loginTime, @Param("id")int id);
}
