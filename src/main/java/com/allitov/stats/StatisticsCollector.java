package com.allitov.stats;

public interface StatisticsCollector{

    void addValue(String value);
    void printStatistics();
}
