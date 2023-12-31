package com.yfh.bsecurity.security;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yfh.bsecurity.config.BSecurityTokenProperties;
import com.yfh.bsecurity.token.JwtTokenService;

import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Resource
    private BSecurityTokenProperties tokenProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
             throws ServletException, IOException {   
          
        try {
            
            String method = request.getMethod();
            String contextPath = request.getRequestURI();
            String action = contextPath.substring(contextPath.lastIndexOf("/") + 1);
            String token = request.getHeader(tokenProperties.getHeaderName()).trim();

            if (!Objects.isNull(token)) {
               token = StringUtils.startsWithIgnoreCase(token, tokenProperties.getHeaderValuePrefix())? token.substring(
                       tokenProperties.getHeaderValuePrefix().length()).trim():token;

               JwtClaims body = null;
               if("POST".equalsIgnoreCase(method) && tokenProperties.getRefreshMethod().equalsIgnoreCase(action)){

                  body = jwtTokenService.verifyToken(token, tokenProperties.getRefreshExpirationSeconds());
                  request.setAttribute("bescurity.token.verifiedClaims", body);

               }else{

                  body = jwtTokenService.verifyToken(token, tokenProperties.getAccessExpirationSeconds());
               }
               
               if(body != null){ //verified pass 
                  request.setAttribute("bsecurity.token.userId", body.getClaimValue("userId"));
                  request.setAttribute("bsecurity.token.loginName", body.getClaimValue("loginName"));

                  
                  Object userTypeObj = body.getClaimValue("userType");
                  String loginName = body.getClaimValueAsString("loginName");

                  List<GrantedAuthority> authorities = null;
                  if(userTypeObj != null && userTypeObj.toString().matches("^[-\\+]?[\\d]*$")){
                    authorities = AuthorityBuilder.defaultBuilder.build(Integer.parseInt(userTypeObj.toString()));
                  }
                  // set authentication
                  UsernamePasswordAuthenticationToken authenticationToken =
                                 new UsernamePasswordAuthenticationToken(loginName, null, authorities);

                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
            }

            

        } catch (Exception e) {

            log.error(e.getMessage());

        }finally{

            filterChain.doFilter(request, response);
        }

    }
    
        
    
    
}
