package com.allitov.writer.impl;

import com.allitov.options.AppOption;
import com.allitov.writer.AbstractDataWriter;

public class StringDataWriter extends AbstractDataWriter {

    private static final String FILE_NAME = "strings.txt";

    public StringDataWriter(String filePath, String filePrefix) {
        super(FILE_NAME, filePath, filePrefix);
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public void printStatistics() {
        if (statsOption == AppOption.SHORT_STATS) {
            System.out.println("String Statistics: \nsize:" + stats.size());
        } else if (statsOption == AppOption.FULL_STATS) {
            System.out.printf(
                    "String Statistics: \nsize: %d\nmin len: %d;\nmax len: %d;\n",
                    stats.size(),
                    stats.stream().mapToInt(String::length).min().orElse(0),
                    stats.stream().mapToInt(String::length).max().orElse(0));
        }
    }
}
