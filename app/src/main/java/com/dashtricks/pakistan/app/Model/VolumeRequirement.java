package com.dashtricks.pakistan.app.model;

import com.dashtricks.pakistan.app.general.Facility;

/**
 * Created by Donohue on 5/7/14.
 */
public class VolumeRequirement {
    private final double sum;
    private final Facility facility;

    public VolumeRequirement(Facility facility, double sum) {
        this.sum = sum;
        this.facility = facility;
    }

    public double get() {
        return sum;
    }

    public Facility getFacility() {
        return facility;
    }

    public VolumeRequirementBuilder getBuilder() {
        VolumeRequirementBuilder vrb = new VolumeRequirementBuilder(facility);
        vrb.add(sum);
        return vrb;
    }
}
