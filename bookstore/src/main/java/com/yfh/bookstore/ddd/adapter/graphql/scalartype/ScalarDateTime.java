package com.yfh.bookstore.ddd.adapter.graphql.scalartype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class ScalarDateTime {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
 
    private static final String DESCRIPTION = "Date scalar: " + PATTERN;
 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(PATTERN);

    public static final GraphQLScalarType Date = GraphQLScalarType.newScalar()
            .name("Date")
            .description(DESCRIPTION)
            .coercing(new Coercing<LocalDateTime, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    
                    return DATE_FORMAT.format((TemporalAccessor) Objects.requireNonNull(dataFetcherResult));
                }

                @Override
                public LocalDateTime parseValue(Object input) {
                    
                    return LocalDateTime.parse(String.valueOf(Objects.requireNonNull(input)), DATE_FORMAT);
                }

                @Override
                public LocalDateTime parseLiteral(Object input) {
                    return LocalDateTime.parse(String.valueOf(Objects.requireNonNull(input)), DATE_FORMAT);
                }
            })
            .build();

    
}
