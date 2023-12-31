package com.yfh.register.ddd.adapter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class UserRegisterDTO {
    

    @Positive(groups=update.class)
    private Integer userId;

    @NotBlank(groups=create.class)
    @Length(min = 6, max = 20, groups = {create.class})
    private String loginName;

    @NotBlank(groups=create.class)
    @Length(min = 6, max = 20, groups = create.class)
    private String password;
    
    @Length(min = 6, max = 20)
    private String userName;

    @Range(min = 0, max = 2, groups = { create.class, update.class })
    private Integer sex;

    @Email
    private String email;

    @Pattern(regexp="1[3|4|5|6|7|8|9][0-9]{9}")
    private String phoneNumber;
    
    private String remark;

    private String avatar;

    private String loginIp;

    
    public interface create{};
    public interface update{};

}
