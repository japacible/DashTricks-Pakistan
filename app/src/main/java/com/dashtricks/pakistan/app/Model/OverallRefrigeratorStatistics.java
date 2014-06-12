package com.dashtricks.pakistan.app.Model;

/**
 * Created by Dan on 6/2/2014.
 */
public class OverallRefrigeratorStatistics {
    private int numberOfRefrigerators;
    private int workingRefrigerators;
    private int refrigeratorsOutOfCommission;

    public OverallRefrigeratorStatistics() {
        numberOfRefrigerators = 2000;
        workingRefrigerators = 1600;
        refrigeratorsOutOfCommission = 400;
    }

    public int getNumberOfRefrigerators() {
        return numberOfRefrigerators;
    }

    public int getWorkingRefrigerators() {
        return workingRefrigerators;
    }

    public int getRefrigeratorsOutOfCommission() {
        return refrigeratorsOutOfCommission;
    }
}

