package com.allitov;

import com.allitov.options.AppOptionsContainer;
import com.allitov.reader.DataReader;
import com.allitov.reader.impl.FileDataReader;

public class App {

    public static void main(String[] args) {
        AppOptionsContainer.init(args);
        DataReader reader = new FileDataReader(AppOptionsContainer.getInstance().getOptions().getArgList());
        reader.read();
    }
}
