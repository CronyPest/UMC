package ru.mts.media.platform.umc.api.gql;

import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@Profile("!migrator")
public class GraphqlSpringConfig implements RuntimeWiringConfigurer {

    @Override
    public void configure(RuntimeWiring.Builder builder) {
        builder.scalar(dateTimeScalar());
    }

    @Bean
    public GraphQLScalarType dateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("Java 8 LocalDateTime as scalar")
                .coercing(new LocalDateTimeCoercing())
                .build();
    }
}
