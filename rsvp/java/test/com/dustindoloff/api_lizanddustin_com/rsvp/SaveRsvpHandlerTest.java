// Copyright (c) 2017 Dustin Doloff
// Licensed under Apache License v2.0

package com.dustindoloff.api_lizanddustin_com.rsvp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class SaveRsvpHandlerTest {
    @Mock
    private AmazonDynamoDB mockAmazonDynamoDB;

    @Spy
    private SaveRsvpHandler spySaveRsvpHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(mockAmazonDynamoDB).when(spySaveRsvpHandler).getAmazonDynamoDB();
    }

    @Test
    public void testGetCurrentTimeIso() {
        final String time = spySaveRsvpHandler.getCurrentTimeIso();
        Assert.assertTrue("Invalid time format: " + time,
                Pattern.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z", time));
    }

    @Test
    public void testDataMap() {
        final String currentTime = "time";
        final String dynameTableName = "dyanamo table name";

        doReturn(currentTime).when(spySaveRsvpHandler).getCurrentTimeIso();
        doReturn(dynameTableName).when(spySaveRsvpHandler)
                .getEnv(SaveRsvpHandler.DYNAMO_DB_TABLE_NAME_ENV_VARIABLE);

        final Map<String, String> dataMap = new HashMap<String, String>() {{
            // Real
            put("name", "bob");
            put("attendance", "will");
            // Fake
            put("stripped", "value");
            put("", "");
            put(null, null);
        }};
        spySaveRsvpHandler.handle(dataMap, null);

        verify(mockAmazonDynamoDB).putItem(eq(dynameTableName),
                                           eq(new HashMap<String, AttributeValue>() {{
                                               put("name", new AttributeValue("bob"));
                                               put("attendance", new AttributeValue("will"));
                                               put("created", new AttributeValue(currentTime));
                                            }}));
    }
}
