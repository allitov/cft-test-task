package com.allitov.writer.impl;

import com.allitov.writer.AbstractDataWriter;

public class IntegerDataWriter extends AbstractDataWriter{

    private static final String FILE_NAME = "integers.txt";

    public IntegerDataWriter(String filePath, String filePrefix) {
        super(FILE_NAME, filePath, filePrefix);
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
}
