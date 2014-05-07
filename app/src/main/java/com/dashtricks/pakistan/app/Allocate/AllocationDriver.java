package com.dashtricks.pakistan.app.allocate;

import com.dashtricks.pakistan.app.general.RefrigeratorTypeAndCount;
import com.dashtricks.pakistan.app.model.VolumeRequirement;
import com.dashtricks.pakistan.app.model.VolumeRequirementBuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class AllocationDriver {
    public static Set<VolumeRequirement> allocate(Set<VolumeRequirement> beforeAllocation, Set<RefrigeratorTypeAndCount> rtacs) {
        //TODO Hmmm the whole VR and VRbuilder thing didn't work out as well as I thought it would
        // Should be aggressivly refactored. The important consideration (and reason I did it this way)
        // is that we can't destroy / overwrite the "beforeAllocation" results. Best way might just
        // be to deep copy the set at the beginning and update freely from there

        Prioritizer p = new NaivePrioritizer(beforeAllocation);
        while (!rtacs.isEmpty()) {
            VolumeRequirementBuilder current = p.remove();
            current.subract(getAppropriateRefrigerator(current, rtacs)); // TODO should be modifying the facility and not the volume requirement
            p.add(current); // TODO will need to deal with facilities that can't be allocated to
        }

        return p.done();
    }

    private static double getAppropriateRefrigerator(VolumeRequirementBuilder current, Set<RefrigeratorTypeAndCount> rtacs) {
        // current will matter in the future for this.
        Iterator<RefrigeratorTypeAndCount> ittr = rtacs.iterator();
        RefrigeratorTypeAndCount rtac = ittr.next();
        if (rtac.getCount() == 1) {
            ittr.remove();
        }

        rtac.setCount(rtac.getCount() - 1);
        return rtac.getType().getVolume();
    }
}
