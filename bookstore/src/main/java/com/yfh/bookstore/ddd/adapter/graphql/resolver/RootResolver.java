package com.yfh.bookstore.ddd.adapter.graphql.resolver;

import com.yfh.bookstore.ddd.adapter.graphql.queryparam.BookParams;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class RootResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    
    public String version(){
        return "ver 0.0.1";
    }

    public String dummy(){
        return null;
    }
}
