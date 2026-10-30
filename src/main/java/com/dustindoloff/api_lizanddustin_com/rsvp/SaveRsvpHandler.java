// Copyright (c) 2017 Dustin Doloff
// Licensed under Apache License v2.0

package com.dustindoloff.api_lizanddustin_com.rsvp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SaveRsvpHandler {
    static final String DYNAMO_DB_TABLE_NAME_ENV_VARIABLE = "DYNAMO_DB_TABLE_NAME";

    private static final Set<String> ALLOWED_KEYS = new HashSet<String>() {{
        Collections.addAll(this,
            "name",
            "attendance",
            "contact-type",
            "contact-value",
            "extra",
            // Added automatically
            "created"
        );
    }};

    private String getDynamoDBTableName() {
        final String tableName = getEnv(DYNAMO_DB_TABLE_NAME_ENV_VARIABLE);
        if (tableName == null) {
            throw new RuntimeException(String.format("Missing %s environment variable",
                    DYNAMO_DB_TABLE_NAME_ENV_VARIABLE));
        }
        return tableName;
    }

    /**
     * Gets an environment variable. This is exposed to override during testing.
     * @param key The environment variable name
     * @return The environment variable or null if not found.
     */
    String getEnv(final String name) {
        return System.getenv(name);
    }

    String getCurrentTimeIso() {
        return Instant.now().toString();
    }

    AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    public void handle(final Map<String, String> saveRsvpRequest, final Context context) {
        final String tableName = getDynamoDBTableName();

        saveRsvpRequest.put("created", getCurrentTimeIso());
        final Map<String, AttributeValue> dataMap =
                saveRsvpRequest.entrySet().stream()
                        .filter(entry -> ALLOWED_KEYS.contains(entry.getKey()))
                        .collect(Collectors.toMap(
                            entry -> entry.getKey(),
                            entry -> new AttributeValue(entry.getValue())
                        ));

        final AmazonDynamoDB dynamoDbClient = getAmazonDynamoDB();

        dynamoDbClient.putItem(tableName, dataMap);
    }
}
