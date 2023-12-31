package com.yfh.admin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {


//     // @Bean
//     // public PasswordEncoder passwordEncoder(){
//     //     return new BCryptPasswordEncoder();
//     // }


//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity.csrf().disable()
//                     .cors()
//                     .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                     .and().authorizeRequests().antMatchers("/").authenticated()
//                     .and().formLogin().defaultSuccessUrl("/applications");
                    
//     }

// }

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                    .cors()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests().antMatchers("/").authenticated()
                    .and().formLogin().defaultSuccessUrl("/applications");
        
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

}