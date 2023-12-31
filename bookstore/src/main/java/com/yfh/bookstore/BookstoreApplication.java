package com.yfh.bookstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"com.yfh.bsecurity.mapper", "com.yfh.bookstore.ddd.infrastructure.mapper"})
public class BookstoreApplication {

	public static void main(String[] args) {
		
		//setDubboCachePath();
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static void setDubboCachePath(){
		final String DUBBO_CACHE_PATH = "C:/Users/yufenghua/.dubbo/bookstore";
        System.setProperty("dubbo.meta.cache.filePath", DUBBO_CACHE_PATH);
        System.setProperty("dubbo.mapping.cache.filePath",DUBBO_CACHE_PATH);
	}

}
