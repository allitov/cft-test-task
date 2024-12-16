package com.allitov.writer;

import com.allitov.options.AppOption;
import com.allitov.options.AppOptionsContainer;
import com.allitov.stats.StatisticsCollector;
import lombok.Getter;
import org.apache.commons.cli.CommandLine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Getter
public abstract class AbstractDataWriter implements DataWriter, DataTypeValidator, StatisticsCollector {

    private final File file;
    private final Queue<String> buffer = new LinkedList<>();
    protected final List<String> stats = new ArrayList<>();
    protected final AppOption statsOption;

    public AbstractDataWriter(String fileName, String filePath, String filePrefix) {
        String name = Objects.requireNonNullElse(filePath, "./") +
                Objects.requireNonNullElse(filePrefix, "") +
                Objects.requireNonNullElse(fileName, "file.txt");
        this.file = new File(name);

        CommandLine options = AppOptionsContainer.getInstance().getOptions();
        if (options.hasOption(AppOption.SHORT_STATS.getOption())) {
            this.statsOption = AppOption.SHORT_STATS;
        } else if (options.hasOption(AppOption.FULL_STATS.getOption())) {
            this.statsOption = AppOption.FULL_STATS;
        } else {
            this.statsOption = null;
        }
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

    @Override
    public void addValue(String value) {
        if (statsOption != null) {
            stats.add(value);
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
