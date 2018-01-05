package com.wedt.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum DaysIntervalProbability {

    FIRST_INTERVAL(0, 3, 1.0),
    SECOND_INTERVAL(3, 6, 0.8),
    THIRD_INTERVAL(6, 12, 0.6),
    FOURTH_INTERVAL(12, 24, 0.4),
    FIFTH_INTERVAL(24, 48, 0.2);

    private final int start;
    private final int end;
    private final double value;

    DaysIntervalProbability(int start, int end, double value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    private static final List<DaysIntervalProbability> intervals;

    static {
        intervals = new ArrayList<>();
        Collections.addAll(intervals, DaysIntervalProbability.values());
    }

    public static boolean betweenInclusive(int min, int max, double x) {
        return x >= min && x <= max;
    }

    public static double getDaysIntervalProbability(int number) {
        return intervals.stream()
                .filter(timeInterval -> betweenInclusive(timeInterval.start, timeInterval.end, number))
                .findFirst()
                .map(timeInterval -> timeInterval.value)
                .orElse(0.0);
    }
}
