package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facility;

/**
 * Created by Donohue on 5/7/14.
 */
public class VolumeRequirementBuilder {
    private double sum;
    private Facility facility;

    public VolumeRequirementBuilder(Facility f) {
        sum = 0;
        facility = f;
    }

    public void add(double i) {
        sum += i;
    }

    public void subract(double i) {
        sum -= i;
    }

    public VolumeRequirement resolve() {
        return new VolumeRequirement(facility, sum);
    }

    public Facility getFacility() {
        return facility;
    }
}
