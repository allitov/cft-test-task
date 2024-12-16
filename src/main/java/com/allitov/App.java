package com.allitov;

import com.allitov.options.AppOption;
import com.allitov.options.AppOptionsContainer;
import com.allitov.reader.DataReader;
import com.allitov.reader.impl.FileDataReader;
import com.allitov.writer.AbstractDataWriter;
import com.allitov.writer.impl.FloatDataWriter;
import com.allitov.writer.impl.IntegerDataWriter;
import com.allitov.writer.impl.StringDataWriter;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AppOptionsContainer.init(args);
        CommandLine options = AppOptionsContainer.getInstance().getOptions();
        String filePath = options.getOptionValue(AppOption.PATH_OPTION.getOption());
        String filePrefix = options.getOptionValue(AppOption.PREFIX_OPTION.getOption());
        AppOption statsOption = null;
        if (options.hasOption(AppOption.SHORT_STATS.getOption())) {
            statsOption = AppOption.SHORT_STATS;
        } else if (options.hasOption(AppOption.FULL_STATS.getOption())) {
            statsOption = AppOption.FULL_STATS;
        }

        List<AbstractDataWriter> writers = List.of(
                new IntegerDataWriter(filePath, filePrefix, statsOption),
                new FloatDataWriter(filePath, filePrefix, statsOption),
                new StringDataWriter(filePath, filePrefix, statsOption)
        );
        DataReader reader = new FileDataReader(options.getArgList(), writers);
        reader.read();
    }
}
