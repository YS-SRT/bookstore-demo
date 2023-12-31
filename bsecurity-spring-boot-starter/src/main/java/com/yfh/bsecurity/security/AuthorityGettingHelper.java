package com.yfh.bsecurity.security;

import java.util.Objects;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import com.yfh.common.error.ErrorCode;



public interface AuthorityGettingHelper {

    // put in Controller
    // @Autowired 
    // private HttpServletRequest request;

    default boolean isAuthOwnerUserId(HttpServletRequest request, int userId){
        
        Object tokenUserId = request.getAttribute("bsecurity.token.userId");
        return !Objects.isNull(tokenUserId) ? userId == Integer.parseInt(tokenUserId.toString()) : false ;
    }

    default boolean isAuthOwnerLoginName(HttpServletRequest request, String loginName){

        Object tokenLoginName = request.getAttribute("bsecurity.token.loginName");
        return Objects.isNull(tokenLoginName) ?  false : loginName.equals(tokenLoginName.toString());
    }
    
    
    default boolean isAdmin(HttpServletRequest request){
        //role default vale is ROLE_ADMIN    
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //return auth != null ? auth.getAuthorities().stream().anyMatch(p->p.getAuthority().contains("ADMIN")):false;
        
        return request.isUserInRole("ROLE_ADMIN");

    }

    default Object getVerifiedClaim(HttpServletRequest request){

        return request.getAttribute("bescurity.token.verifiedClaims");
    }

    default void actOutOfSelfOrAdminPermission(HttpServletRequest request, int userId, Consumer<String> action) {
        
        if(!isAdmin(request) && !isAuthOwnerUserId(request, userId)){  

            action.accept(ErrorCode.ERROR_CODE_NO_PERMISSION_FAILED);
        }
    }
    
}
