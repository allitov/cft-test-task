package com.allitov.reader.impl;

import com.allitov.writer.AbstractDataWriter;
import com.allitov.writer.impl.FloatDataWriter;
import com.allitov.writer.impl.IntegerDataWriter;
import com.allitov.writer.impl.StringDataWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDataReaderTest {

    private FileDataReader reader;
    private String filePath;
    private String filePrefix;

    @BeforeEach
    void setUp() {
        List<String> files = List.of("./src/test/resources/in1.txt", "./src/test/resources/in2.txt");
        filePath = "./src/test/resources/";
        filePrefix = "result_";
        List<AbstractDataWriter> writers = List.of(
                new IntegerDataWriter(filePath, filePrefix, false, null),
                new FloatDataWriter(filePath, filePrefix, false, null),
                new StringDataWriter(filePath, filePrefix, false, null)
        );
        reader = new FileDataReader(files, writers);
    }

    @Test
    @DisplayName("Test writing to a file of strings")
    void writeToStringsFileTest() {
        reader.read();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + filePrefix + "strings.txt"))) {
            int count = 0;
            while (bufferedReader.ready()) {
                bufferedReader.readLine();
                count++;
            }
            assertEquals(6, count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test writing to a file of integers")
    void writeToIntegersFileTest() {
        reader.read();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + filePrefix + "integers.txt"))) {
            int count = 0;
            while (bufferedReader.ready()) {
                bufferedReader.readLine();
                count++;
            }
            assertEquals(3, count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test writing to a file of floats")
    void writeToFloatsFileTest() {
        reader.read();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + filePrefix + "floats.txt"))) {
            int count = 0;
            while (bufferedReader.ready()) {
                bufferedReader.readLine();
                count++;
            }
            assertEquals(3, count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
