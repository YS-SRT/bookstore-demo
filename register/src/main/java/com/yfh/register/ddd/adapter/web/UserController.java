package com.yfh.register.ddd.adapter.web;

import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.cola.exception.SysException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.util.MapUtils;
import com.yfh.bsecurity.security.AuthorityGettingHelper;
import com.yfh.common.resp.SingleResponse;
import com.yfh.register.ddd.adapter.searchvo.UserSearchVO;
import com.yfh.register.ddd.adapter.vo.converter.UserVOConverter;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.infrastructure.mapper.UserPaidInfoMapper;
import com.yfh.register.ddd.infrastructure.model.UserPaidInfoDO;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserController", description = "保护的用户信息页面")
@RestController
@RequestMapping("/auth")
public class UserController implements AuthorityGettingHelper{

    @Resource
    private HttpServletRequest request;
    
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserPaidInfoMapper userPaidInfoMapper;

    @Resource
    private BeanSearcher searcher;

    private Consumer<String> action = s -> {
        throw new SysException(s, "只能查看自己的信息");
    };

    @Operation(summary = "根据UserID, 查询用户信息", description = "分步查询")
    @Parameter(name = "userId", description = "用户ID")
    @GetMapping("/getUserById")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<UserSearchVO> getByUserId1(@RequestParam int userId) {

        actOutOfSelfOrAdminPermission(request, userId, action);

        UserSearchVO userSearchVO = UserVOConverter.toUserSearchVO(userInfoMapper.selectById(userId));
        if(!ObjectUtils.isEmpty(userSearchVO)){
            QueryWrapper<UserPaidInfoDO> query = new QueryWrapper<UserPaidInfoDO>();
            return SingleResponse.of(
            UserVOConverter.addIntoUserSearchVO(userSearchVO, userPaidInfoMapper.selectList(query.eq("user_id", userId))));
        }

        return SingleResponse.of(null);

    }
    
    @Operation(summary = "根据UserID, 查询用户信息", description = "嵌套查询")
    @Parameter(name="userId", description = "用户ID")
    @GetMapping("/search/getUserById")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<UserSearchVO> getByUserId2(@RequestParam int userId){
        actOutOfSelfOrAdminPermission(request, userId, action);
        
		Map<String, Object> params = MapUtils.builder()
                                             .field(UserSearchVO::getId, userId)
                                             .build();
        UserSearchVO userSearchVO = searcher.searchFirst(UserSearchVO.class, params);
        return SingleResponse.of(userSearchVO);

    }

    
    
}
