package com.allitov.writer.impl;

import com.allitov.writer.AbstractDataWriter;

public class FloatDataWriter extends AbstractDataWriter {

    private static final String FILE_NAME = "floats.txt";

    public FloatDataWriter(String filePath, String filePrefix) {
        super(FILE_NAME, filePath, filePrefix);
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
}
