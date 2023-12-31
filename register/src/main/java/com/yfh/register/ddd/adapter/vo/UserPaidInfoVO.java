package com.yfh.register.ddd.adapter.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserPaidInfoVO {
    
    private Integer Id;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;
}
