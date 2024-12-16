package com.allitov.writer.impl;

import com.allitov.options.AppOption;
import com.allitov.writer.AbstractDataWriter;

public class FloatDataWriter extends AbstractDataWriter {

    private static final String FILE_NAME = "floats.txt";

    public FloatDataWriter(String filePath, String filePrefix, AppOption statsOption) {
        super(FILE_NAME, filePath, filePrefix, statsOption);
    }

    @Override
    public boolean isValid(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void printStatistics() {
        if (statsOption == AppOption.SHORT_STATS) {
            System.out.println("Float Statistics: \nsize:" + stats.size());
        } else if (statsOption == AppOption.FULL_STATS) {
            System.out.printf(
                    "Float Statistics: \nsize: %d\nmin: %f;\nmax: %f;\nsum: %f;\navg: %f;\n",
                    stats.size(),
                    stats.stream().mapToDouble(Double::parseDouble).min().orElse(0),
                    stats.stream().mapToDouble(Double::parseDouble).max().orElse(0),
                    stats.stream().mapToDouble(Double::parseDouble).sum(),
                    stats.stream().mapToDouble(Double::parseDouble).average().orElse(0));
        }
    }
}
