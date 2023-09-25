package ru.nsu.boxberger.shift_task.service;

import java.util.List;

public interface IntervalService {
    /**
     * Merges and saves intervals according to the specified kind.
     *
     * @param kind      The type of intervals (digits, letters).
     * @param intervals A list of intervals represented as lists of strings [start, end].
     */
    void mergeAndSaveIntervals(String kind, List<List<String>> intervals);

    /**
     * Finds and returns the string representation of the minimum interval for the specified kind.
     *
     * @param kind      kind The type of intervals (digits, letters).
     * @return          The string representation of the minimum interval in the format "[start, end]".
     */
    String findMinInterval(String kind);
}
