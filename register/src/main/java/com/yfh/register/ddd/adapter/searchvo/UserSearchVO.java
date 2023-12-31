package com.yfh.register.ddd.adapter.searchvo;

import java.time.LocalDateTime;
import java.util.List;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.yfh.register.ddd.adapter.vo.UserPaidInfoVO;

import lombok.Data;

@SearchBean(tables="user_info u", autoMapTo = "u")
@Data
public class UserSearchVO {

    //@DbField("u.id")
    private Integer id;
    //@DbField("u.remark")
    private String remark;
    //@DbField("u.login_name")
    private String loginName;
    //@DbField("u.user_name")
    private String userName;
    //@DbField("u.user_type")
    private Integer userType;
    //@DbField("u.email")
    private String email;
    //@DbField("u.phone_number")
    private String phoneNumber;
    //@DbField("u.sex")
    private Integer sex;
    //@DbField("u.avatar")
    private String avatar;
    //@DbField("u.status")
    private Integer status;
    //@DbField("u.login_ip")
    private String loginIp;
    //@DbField("u.login_time")
    private LocalDateTime loginTime;
    //@DbField("u.update_date")
    private LocalDateTime updateTime;

    @DbField("select id, start_time, end_time from user_paid_info p where u.id = p.user_id and p.del_flag = 0")
    List<UserPaidInfoVO> userPaidInfo;
}
