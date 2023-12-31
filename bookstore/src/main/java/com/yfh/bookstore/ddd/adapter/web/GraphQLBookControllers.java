package com.yfh.bookstore.ddd.adapter.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.kickstart.execution.GraphQLRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/graphql/book")
public class GraphQLBookControllers {

    @Autowired
    private GraphQL graphQL;
    
    @Operation(summary = "书籍GraphQL接口", description = "根据自定义条件获得书籍相关信息")
    @ApiResponse(description = "自定义获取信息", useReturnTypeSchema = true)
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public Object execute(@RequestBody GraphQLRequest request){
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                                        .query(request.getQuery())
                                        .variables(request.getVariables())
                                        .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);

        List<GraphQLError> errors = executionResult.getErrors();
        if(CollectionUtils.isEmpty(errors)){
            return executionResult;
        }

        return errors;

    } 
    
}
