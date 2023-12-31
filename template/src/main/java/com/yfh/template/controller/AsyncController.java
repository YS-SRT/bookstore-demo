package com.yfh.template.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.yfh.template.service.AsyncService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AsyncController {

     @Autowired
     private AsyncService asyncService;


     @GetMapping("callable")
     public Callable<String> callService(){
         log.info("start callService... ");
         Callable<String> callable = asyncService::asyncResult;
         log.info("finish callService... ");
         return callable;
     }

     @GetMapping("webasynctask")
     public WebAsyncTask<String> asyncTaskService(){
         log.info("start asyncTaskService... ");
         WebAsyncTask<String> asyncTask = new WebAsyncTask<>(asyncService::asyncResult);
         log.info("finish asyncTaskService... ");
         return asyncTask;
     }

     @GetMapping("deferred")
     public DeferredResult<String> deferredService(){
         log.info("start deferredService... ");
         DeferredResult<String> deferredResult = new DeferredResult<>(6000l);
         deferredResult.onCompletion(new Runnable() {

            @Override
            public void run() {

                log.info("deferredResult.onCompletion... ");
                
            }
             
         });

         CompletableFuture.supplyAsync(asyncService::asyncResult)
            .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));

         log.info("finish deferredService... ");
         return deferredResult;
     }

    
}
