package com.yfh.template.service;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    
    @Async
    private String asyncString(){
        try {
            Thread.sleep(2000l);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
           
        return  "private-async-string";
    }


    @Async
    public String asyncResult(){

        try {

            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
    
        return "Async-Result";
    }
    

    public String wrapAsyncResult(){

        return this.asyncResult();

        //return this.asyncString();

        // boolean isAop = AopUtils.isAopProxy(AsyncService.class);//是否是代理对象；
        // boolean isCglib = AopUtils.isCglibProxy(AsyncService.class);  //是否是CGLIB方式的代理对象；
        // boolean isJdk = AopUtils.isJdkDynamicProxy(AsyncService.class);  //是否是JDK动态代理方式的代理对象；
        
        // AsyncService proxy = (AsyncService) AopContext.currentProxy();
        // return proxy.asyncResult();

    }
}
