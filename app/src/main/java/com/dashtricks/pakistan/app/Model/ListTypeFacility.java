package com.dashtricks.pakistan.app.Model;

import java.util.UUID;

/**
 * Created by Dan on 5/19/2014.
 */
public class ListTypeFacility {

    private int capacitySortPosition;
    private String facilityName;
    private int currentCapacity;
    private int requiredCapacity;

    // Currently using position to create different facilities.
    // TODO: Fix this automation once we get real facility data from the backend
    public ListTypeFacility(int position) {
        capacitySortPosition = position;
        facilityName = (capacitySortPosition + 1) + ". Punjab Main";
        currentCapacity = capacitySortPosition + 2;
        requiredCapacity = capacitySortPosition + 5;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public int getCapacitySortPosition() {
        return capacitySortPosition;
    }

    @Override
    public String toString() {
        return facilityName;
    }
}
