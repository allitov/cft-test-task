package com.allitov.options;

import lombok.Getter;
import org.apache.commons.cli.Option;

@Getter
public enum AppOption {

    PREFIX_OPTION("p", "prefix", "PREFIX", "Prefix for result file", false),
    PATH_OPTION("o", "path", "PATH", "Path for result file", false);

    private final Option option;

    AppOption(String shortName, String longName, String argName, String description, boolean required) {
        this.option = Option.builder(shortName)
                .longOpt(longName)
                .argName(argName)
                .desc(description)
                .hasArg()
                .required(required)
                .build();
    }
}
