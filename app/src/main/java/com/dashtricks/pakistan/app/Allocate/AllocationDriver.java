package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.General.Refrigerator;
import com.dashtricks.pakistan.app.Model.VolumeRequirement;
import com.dashtricks.pakistan.app.Model.VolumeRequirementBuilder;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class AllocationDriver {
    public static Set<VolumeRequirement> allocate(Set<VolumeRequirement> beforeAllocation,
                                                  Set<Refrigerator> rtacs)
    {
        //TODO Hmmm the whole VR and VRbuilder thing didn't work out as well as I thought it would
        // Should be aggressivly refactored. The important consideration (reason I did it this way)
        // is that we can't destroy / overwrite the "beforeAllocation" results. Best way might just
        // be to deep copy the set at the beginning and update freely from there

        Prioritizer p = new NaivePrioritizer(beforeAllocation);
        while (!rtacs.isEmpty()) {
            VolumeRequirementBuilder current = p.remove();

            // TODO should be modifying the facility and not the volume requirement
            current.subract(getAppropriateRefrigerator(current, rtacs));

            p.add(current); // TODO will need to deal with facilities that can't be allocated to
        }

        return p.done();
    }

    private static double getAppropriateRefrigerator(VolumeRequirementBuilder current,
                                                     Set<Refrigerator> rtacs)
    {
        // current will matter in the future for this.
        Iterator<Refrigerator> ittr = rtacs.iterator();
        Refrigerator rtac = ittr.next();

        return rtac.getVolume();
    }
}
