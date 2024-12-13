package com.allitov.options;

import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@Getter
public class AppOptionsContainer {

    private static AppOptionsContainer INSTANCE;
    private final CommandLine options;

    private AppOptionsContainer(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(AppOption.PATH_OPTION.getOption())
                .addOption(AppOption.PREFIX_OPTION.getOption());
        this.options = new DefaultParser().parse(options, args);
    }

    public static void init(String[] args) {
        try {
            INSTANCE = new AppOptionsContainer(args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static AppOptionsContainer getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("AppOptionsContainer has not been initialized yet.");
        }

        return INSTANCE;
    }
}
