package com.yfh.order.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme; 

@Configuration
public class SpringDocConfig {
    private static final String SECURITY_SCHEME_NAME = "BSAuth";

    @Bean
    public OpenAPI registerOpenApi(){
        return new OpenAPI()
                   .info(new Info().title("Order API")
                                   .description("订单服务 API")
                                   .version("v 1.0.0"))
                   .externalDocs(new ExternalDocumentation()
                                   .description("在线文档")
                                   .url("http://"))
                   .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                   .components(new Components()
                                   .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                                                                 .name(SECURITY_SCHEME_NAME)
                                                                                 .type(SecurityScheme.Type.HTTP)
                                                                                 .scheme("bearer")
                                                                                 .bearerFormat("JWT")));
                   

    }

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
        .group("public")
        .pathsToMatch("/oauth/**")
        .packagesToScan("com.yfh.order.ddd.adapter.web")
        .build();
    }

    @Bean
    public GroupedOpenApi protectedApi(){
        return GroupedOpenApi.builder()
        .group("protected")
        .pathsToMatch("/auth/**")
        .packagesToScan("com.yfh.order.ddd.adapter.web")
        .build();
    }
}
