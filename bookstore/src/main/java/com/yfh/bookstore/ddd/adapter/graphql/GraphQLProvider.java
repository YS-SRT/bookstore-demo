package com.yfh.bookstore.ddd.adapter.graphql;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.yfh.bookstore.ddd.adapter.graphql.resolver.BookResolver;
import com.yfh.bookstore.ddd.adapter.graphql.resolver.RootResolver;
import com.yfh.bookstore.ddd.adapter.graphql.scalartype.ScalarDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParser;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;

@Component
public class GraphQLProvider {
    private GraphQL graphql;

    @Resource
    private BookResolver bookResolver;

    @Resource
    private RootResolver rootResolver;

    @Bean
    public GraphQL graphQL(){
        return this.graphql;
    }

    @Bean
    public GraphQLScalarType date(){
        return ScalarDateTime.Date;
        //return ExtendedScalars.LocalTime;
    }

    @PostConstruct
    public void init(){
        GraphQLSchema graphQLSchema = SchemaParser.newParser()
                                      .file("graphql/root.graphqls")
                                      .resolvers(rootResolver)
                                      .file("graphql/book.graphqls")
                                      .resolvers(bookResolver)
                                      .scalars(date())
                                      .build().makeExecutableSchema();
        this.graphql = GraphQL.newGraphQL(graphQLSchema).build();
    }

    
}
