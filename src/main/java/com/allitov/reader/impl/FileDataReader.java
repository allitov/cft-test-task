package com.allitov.reader.impl;

import com.allitov.reader.DataReader;
import com.allitov.stats.StatisticsCollector;
import com.allitov.writer.AbstractDataWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileDataReader implements DataReader {

    private final Queue<File> files = new LinkedList<>();
    private final List<AbstractDataWriter> writers = new ArrayList<>();

    public FileDataReader(List<String> fileNames, List<AbstractDataWriter> writers) {
        fileNames.forEach(file -> files.add(new File(file)));
        this.writers.addAll(writers);
    }

    @Override
    public void read() {
        while (!files.isEmpty()) {
            File file = files.poll();
            readFile(file);
        }
        writers.forEach(AbstractDataWriter::flush);
        writers.forEach(StatisticsCollector::printStatistics);
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String value = reader.readLine();
                addToWriter(value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToWriter(String value) {
        writers.stream()
                .filter(writer -> writer.isValid(value))
                .findFirst()
                .ifPresentOrElse(
                        writer -> {
                            writer.addToBuffer(value);
                            writer.addStats(value);
                            },
                        () -> System.out.println("No writer found for value: " + value)
                );
    }
}
