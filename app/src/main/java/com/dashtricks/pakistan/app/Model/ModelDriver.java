package com.dashtricks.pakistan.app.model;

import com.dashtricks.pakistan.app.general.Facilities;
import com.dashtricks.pakistan.app.general.Facility;
import com.dashtricks.pakistan.app.general.ImmunizationPlan;
import com.dashtricks.pakistan.app.general.ImmunizationPlans;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class ModelDriver {
    public static Set<VolumeRequirement> getRequirements(Facilities fs, ImmunizationPlans ips) {
        Set<VolumeRequirement> ret = new HashSet<VolumeRequirement>();

        for (Facility f : fs) {
            VolumeRequirementBuilder vrb = new VolumeRequirementBuilder(f);
            for (ImmunizationPlan ip : ips) {
                vrb.add(Calculator.computeVolume(f, ip));
            }
            ret.add(vrb.resolve());
        }

        return ret;
    }
}
