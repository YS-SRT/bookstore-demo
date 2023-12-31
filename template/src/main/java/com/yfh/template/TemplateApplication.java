package com.yfh.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@EnableAspectJAutoProxy(exposeProxy = true)
public class TemplateApplication{

    public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(TemplateApplication.class, args);
		ConfigurableEnvironment env = appContext.getEnvironment();
		MutablePropertySources sources = env.getPropertySources();
		Binder binder =  Binder.get(env);
		String result = binder.bind("spring.bind.test.property", String.class).orElse("loaderror");
		System.out.print(result);

		EnvironmentPostProcessor processor = new EnvironmentPostProcessor();
		
	}
}
