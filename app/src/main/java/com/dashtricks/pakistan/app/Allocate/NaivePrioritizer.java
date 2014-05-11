package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.Model.VolumeRequirement;
import com.dashtricks.pakistan.app.Model.VolumeRequirementBuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class NaivePrioritizer implements Prioritizer {
    private Set<VolumeRequirementBuilder> vrbs;

    public NaivePrioritizer(Set<VolumeRequirement> beforeAllocation) {
        vrbs = new HashSet<VolumeRequirementBuilder>();
        for (VolumeRequirement vr : beforeAllocation) {
            vrbs.add(vr.getBuilder());
        }
    }

    @Override
    public VolumeRequirementBuilder remove() {
        Iterator<VolumeRequirementBuilder> ittr = vrbs.iterator();
        VolumeRequirementBuilder ret = ittr.next();
        ittr.remove();
        return ret;
    }

    @Override
    public void add(VolumeRequirementBuilder current) {
        vrbs.add(current);
    }

    @Override
    public Set<VolumeRequirement> done() {
        Set<VolumeRequirement> ret = new HashSet<VolumeRequirement>();
        for (VolumeRequirementBuilder vbr : vrbs) {
            ret.add(vbr.resolve());
        }
        return ret;
    }
}
