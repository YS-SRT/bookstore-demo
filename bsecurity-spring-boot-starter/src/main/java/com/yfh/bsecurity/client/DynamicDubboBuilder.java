package com.yfh.bsecurity.client;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.yfh.common.dubbo.login.ILoginService;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class DynamicDubboBuilder {


    private ReferenceConfig<ILoginService> config;


    public DynamicDubboBuilder(@Autowired DubboConfigurationProperties dubboConfigProperties){

        config = new ReferenceConfig<>();
        config.setInterface(ILoginService.class);
        config.setCheck(false);
        config.setLazy(true);
        config.setRegistry(dubboConfigProperties.getRegistry());

    }

    public final ILoginService getInstance() throws Exception{
       return config.get();
    }
    
}
