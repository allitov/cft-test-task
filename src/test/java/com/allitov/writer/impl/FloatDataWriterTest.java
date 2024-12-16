package com.allitov.writer.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloatDataWriterTest {

    private FloatDataWriter writer;

    @BeforeEach
    void setUp() {
        writer = new FloatDataWriter("./", "prefix_", true, null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.", "-1.0", "1.528535047E-25"})
    @DisplayName("Test data type validation")
    void dataTypeValidationTest(String data) {
        assertTrue(writer.isValid(data));
    }
}
