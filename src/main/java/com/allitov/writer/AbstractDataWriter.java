package com.allitov.writer;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Getter
public abstract class AbstractDataWriter implements DataWriter, DataTypeValidator {

    private final File file;
    private final Queue<String> buffer = new LinkedList<>();

    public AbstractDataWriter(String fileName, String filePath, String filePrefix) {
        String name = Objects.requireNonNullElse(filePath, "./") +
                Objects.requireNonNullElse(filePrefix, "") +
                Objects.requireNonNullElse(fileName, "file.txt");
        this.file = new File(name);
    }

    @Override
    public void addToBuffer(String line) {
        buffer.add(line);
    }

    @Override
    public void flush() {
        if (buffer.isEmpty()) {
            return;
        }

        if (!file.exists()) {
            createFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            while (!buffer.isEmpty()) {
                writer.write(buffer.poll());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile() {
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
