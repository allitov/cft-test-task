package com.allitov.options;

import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.PrintWriter;

@Getter
public class AppOptionsContainer {

    private static AppOptionsContainer INSTANCE;
    private final CommandLine options;

    private AppOptionsContainer(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(AppOption.PATH_OPTION.getOption())
                .addOption(AppOption.PREFIX_OPTION.getOption())
                .addOption(AppOption.APPEND_FILE.getOption());

        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(AppOption.SHORT_STATS.getOption())
                .addOption(AppOption.FULL_STATS.getOption());
        options.addOptionGroup(optionGroup);

        try {
            this.options = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            printHelp(options);
            throw e;
        }
    }

    public static void init(String[] args) {
        try {
            INSTANCE = new AppOptionsContainer(args);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static AppOptionsContainer getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("AppOptionsContainer has not been initialized yet.");
        }

        return INSTANCE;
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        formatter.printUsage(
                pw,
                100,
                "java -jar cft-test-task-1.0-SNAPSHOT-jar-with-dependencies.jar [options] file1 file2 ..."
        );
        formatter.printOptions(pw, 100, options, 2, 5);
        pw.close();
    }
}
