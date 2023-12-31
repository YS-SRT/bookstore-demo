package com.yfh.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.yfh.bsecurity.mapper", "com.yfh.register.ddd.infrastructure.mapper"})  
public class RegisterApplication{

    public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}
}
