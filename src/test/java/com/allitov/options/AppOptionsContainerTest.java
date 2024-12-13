package com.allitov.options;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppOptionsContainerTest {

    @Test
    @DisplayName("Test all file names reading")
    void readAllFileNamesTest() {
        String[] args = new String[] {"file1", "file2", "file3"};
        AppOptionsContainer.init(args);

        assertEquals(args.length, AppOptionsContainer.getInstance().getOptions().getArgList().size());
    }

    @Test
    @DisplayName("Test file prefix reading")
    void readFilePrefixTest() {
        String[] args = new String[] {"-p", "prefix_"};
        AppOptionsContainer.init(args);

        assertEquals(args[1], AppOptionsContainer.getInstance().getOptions().getOptionValue("p"));
    }

    @Test
    @DisplayName("Test exception throwing when no file prefix provided")
    void throwExceptionWhenNoPrefixProvided() {
        String[] args = new String[] {"-p"};

        assertThrows(RuntimeException.class, () -> AppOptionsContainer.init(args));
    }

    @Test
    @DisplayName("Test file path reading")
    void readFilePathTest() {
        String[] args = new String[] {"-o", "/path/"};
        AppOptionsContainer.init(args);

        assertEquals(args[1], AppOptionsContainer.getInstance().getOptions().getOptionValue("o"));
    }

    @Test
    @DisplayName("Test exception throwing when no file path provided")
    void throwExceptionWhenNoPathProvided() {
        String[] args = new String[] {"-o"};

        assertThrows(RuntimeException.class, () -> AppOptionsContainer.init(args));
    }

    @Test
    @DisplayName("Test '-s' option reading")
    void readShortStatsOptionTest() {
        String[] args = new String[] {"-s"};
        AppOptionsContainer.init(args);

        assertTrue(AppOptionsContainer.getInstance().getOptions().hasOption("s"));
    }

    @Test
    @DisplayName("Test '-f' option reading")
    void readFullStatsOptionTest() {
        String[] args = new String[] {"-f"};
        AppOptionsContainer.init(args);

        assertTrue(AppOptionsContainer.getInstance().getOptions().hasOption("f"));
    }

    @Test
    @DisplayName("Test exception throwing when '-s' and '-f' are given")
    void throwExceptionWhenAllStatsOptionsGivenTest() {
        String[] args = new String[] {"-s", "-f"};

        assertThrows(RuntimeException.class, () -> AppOptionsContainer.init(args));
    }

    @Test
    @DisplayName("Test '-a' option reading")
    void readFileAppendOptionTest() {
        String[] args = new String[] {"-a"};
        AppOptionsContainer.init(args);

        assertTrue(AppOptionsContainer.getInstance().getOptions().hasOption("a"));
    }
}
