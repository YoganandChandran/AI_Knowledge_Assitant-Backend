package com.enterprise.aiknowledgeassistant.utils;

import lombok.Getter;

@Getter
public class AIExecutionTimer {

    private final long startTime;

    private long endTime;

    public AIExecutionTimer() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    /**
     * Returns execution time in milliseconds.
     */
    public long getExecutionTime() {

        if (endTime == 0) {
            return System.currentTimeMillis() - startTime;
        }

        return endTime - startTime;
    }

    /**
     * Returns formatted execution time.
     */
    public String getExecutionTimeWithUnit() {
        return getExecutionTime() + " ms";
    }

}