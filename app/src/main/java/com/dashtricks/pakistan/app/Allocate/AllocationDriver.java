package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class AllocationDriver {

    /**
     *
     * @param fs facilities
     * @param toAllocate type of refrigerator to number to allocate
     * @return facility to type and number allocated
     */
    public static Map<Facility, Map<Refrigerator, Integer>> allocate(Facilities fs, Map<Refrigerator, Integer> toAllocate) {
        Prioritizer prioritizer = new NaivePrioritizer(fs);
        toAllocate = new HashMap<Refrigerator, Integer>(toAllocate);

        while ( !(prioritizer.done() || toAllocate.isEmpty())) {
            Facility currentFacility = prioritizer.next();
            double shortage = currentFacility.getRequiredCapacity() - prioritizer.currentTotalCapacity();

            Refrigerator grantedRefrigerator = getAppropriateRefrigerator(currentFacility, shortage, toAllocate.keySet());

            if (grantedRefrigerator != null) {
                toAllocate.put(grantedRefrigerator, toAllocate.get(grantedRefrigerator) - 1);
                if (toAllocate.get(grantedRefrigerator) == 0) {
                    toAllocate.remove(grantedRefrigerator);
                }

                prioritizer.add(grantedRefrigerator);
            } else {
                prioritizer.leave();
            }
        }

        return prioritizer.result();

    }

    private static Refrigerator getAppropriateRefrigerator(Facility currentFacility, double shortage, Set<Refrigerator> available) {
        Refrigerator bestFit = null;
        for (Refrigerator r : available) {
            /* TODO it's probably really important to institute some type of "canUse" function.
            if (!currentFacility.canUseRefrigerator(r)) {
                continue;
            }
            */
            if (bestFit == null) {
                bestFit = r;
                continue;
            }

            if ((r.getVolume() > shortage && r.getVolume() < bestFit.getVolume())
                    || (r.getVolume() < shortage && r.getVolume() > bestFit.getVolume())) {
                bestFit = r;
            }
        }
        return bestFit;
    }
}
