package ru.mts.media.platform.umc.api.gql;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public String serialize(
            @NotNull Object dataFetcherResult,
            @NotNull GraphQLContext graphQLContext,
            @NotNull Locale locale
    ) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalDateTime localDateTime) {
            return localDateTime.format(FORMATTER);
        }
        throw new CoercingSerializeException("Expected LocalDateTime, got: " + dataFetcherResult.getClass());
    }

    @Override
    public LocalDateTime parseValue(
            @NotNull Object input,
            @NotNull GraphQLContext graphQLContext,
            @NotNull Locale locale
    ) throws CoercingParseValueException {
        if (input instanceof String string) {
            try {
                return LocalDateTime.parse(string, FORMATTER);
            } catch (Exception e) {
                throw new CoercingParseValueException("Invalid LocalDateTime format: " + input);
            }
        }
        throw new CoercingParseValueException("Expected String, got: " + input.getClass());
    }

    @Override
    public LocalDateTime parseLiteral(
            @NotNull Value<?> input,
            @NotNull CoercedVariables variables,
            @NotNull GraphQLContext graphQLContext,
            @NotNull Locale locale
    ) throws CoercingParseLiteralException {
        if (input instanceof StringValue stringValue) {
            String rawValue = stringValue.getValue();
            try {
                return LocalDateTime.parse(rawValue, FORMATTER);
            } catch (Exception e) {
                throw new CoercingParseLiteralException("Invalid LocalDateTime format: " + rawValue);
            }
        }
        throw new CoercingParseLiteralException("Expected StringValue, got: " + input.getClass());
    }
}
