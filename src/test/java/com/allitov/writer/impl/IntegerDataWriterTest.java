package com.allitov.writer.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerDataWriterTest {

    private IntegerDataWriter writer;

    @BeforeEach
    void setUp() {
        writer = new IntegerDataWriter("./", "prefix_", true, null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10000", "-10", "0"})
    @DisplayName("Test data type validation")
    void dataTypeValidationTest(String data) {
        assertTrue(writer.isValid(data));
    }
}
