package com.allitov.options;

import lombok.Getter;
import org.apache.commons.cli.Option;

@Getter
public enum AppOption {

    PREFIX_OPTION("p", "prefix", "PREFIX", "Prefix for result file", true, false),
    PATH_OPTION("o", "path", "PATH", "Path for result file", true, false),
    SHORT_STATS("s", null, "SHORT STATS", "Show short stats", false, false),
    FULL_STATS("f", null, "FULL STATS", "Show full stats", false, false),
    APPEND_FILE("a", null, "APPEND FILE", "Append data to file", false, false);

    private final Option option;

    AppOption(String shortName, String longName, String argName, String description, boolean hasArg, boolean required) {
        this.option = Option.builder(shortName)
                .longOpt(longName)
                .argName(argName)
                .desc(description)
                .hasArg(hasArg)
                .required(required)
                .build();
    }
}
