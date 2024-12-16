package com.allitov.reader;

import com.allitov.options.AppOption;
import com.allitov.options.AppOptionsContainer;
import com.allitov.writer.AbstractDataWriter;
import com.allitov.writer.impl.FloatDataWriter;
import com.allitov.writer.impl.IntegerDataWriter;
import com.allitov.writer.impl.StringDataWriter;
import org.apache.commons.cli.CommandLine;

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
    private final AbstractDataWriter stringWriter;

    public FileDataReader(List<String> fileNames) {
        fileNames.forEach(file -> files.add(new File(file)));
        CommandLine options = AppOptionsContainer.getInstance().getOptions();
        writers.add(new IntegerDataWriter(
                options.getOptionValue(AppOption.PATH_OPTION.getOption()),
                options.getOptionValue(AppOption.PREFIX_OPTION.getOption())
        ));
        writers.add(new FloatDataWriter(
                options.getOptionValue(AppOption.PATH_OPTION.getOption()),
                options.getOptionValue(AppOption.PREFIX_OPTION.getOption())
        ));
        this.stringWriter = new StringDataWriter(
                options.getOptionValue(AppOption.PATH_OPTION.getOption()),
                options.getOptionValue(AppOption.PREFIX_OPTION.getOption())
        );
    }

    @Override
    public void read() {
        while (!files.isEmpty()) {
            File file = files.poll();
            readFile(file);
        }
        writers.forEach(AbstractDataWriter::flush);
        stringWriter.flush();
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
                        writer -> writer.addToBuffer(value),
                        () -> stringWriter.addToBuffer(value)
                );
    }
}
