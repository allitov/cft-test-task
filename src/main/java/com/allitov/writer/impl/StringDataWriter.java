package com.allitov.writer.impl;

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
}
