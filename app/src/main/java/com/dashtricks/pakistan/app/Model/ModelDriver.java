package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.General.ImmunizationPlans;

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
