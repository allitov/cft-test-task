package com.allitov.writer;

public interface DataWriter {

    void flush();
    void addToBuffer(String line);
}
