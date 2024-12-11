package com.allitov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    @DisplayName("app test")
    void test() {
        assertEquals(4, 2 + 2);
    }
}
