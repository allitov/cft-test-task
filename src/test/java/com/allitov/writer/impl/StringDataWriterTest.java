package com.allitov.writer.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringDataWriterTest {

    private StringDataWriter writer;

    @BeforeEach
    void setUp() {
        writer = new StringDataWriter("./", "prefix_", true, null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"string", "22", "0.002"})
    @DisplayName("Test data type validation")
    void dataTypeValidationTest(String data) {
        assertTrue(writer.isValid(data));
    }
}
