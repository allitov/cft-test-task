package com.allitov.writer.impl;

import com.allitov.options.AppOption;
import com.allitov.writer.AbstractDataWriter;

public class IntegerDataWriter extends AbstractDataWriter {

    private static final String FILE_NAME = "integers.txt";

    public IntegerDataWriter(String filePath, String filePrefix, AppOption statsOption) {
        super(FILE_NAME, filePath, filePrefix, statsOption);
    }

    @Override
    public boolean isValid(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void printStatistics() {
        if (statsOption == AppOption.SHORT_STATS) {
            System.out.println("Integer Statistics: \nsize:" + stats.size());
        } else if (statsOption == AppOption.FULL_STATS) {
            System.out.printf(
                    "Integer Statistics: \nsize: %d\nmin: %d;\nmax: %d;\nsum: %d;\navg: %f;\n",
                    stats.size(),
                    stats.stream().mapToLong(Long::parseLong).min().orElse(0L),
                    stats.stream().mapToLong(Long::parseLong).max().orElse(0L),
                    stats.stream().mapToLong(Long::parseLong).sum(),
                    stats.stream().mapToLong(Long::parseLong).average().orElse(0L));
        }
    }
}
