package com.dashtricks.pakistan.app.Allocate;


import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;

import java.sql.Ref;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class NaivePrioritizer implements Prioritizer {
    private Map<Facility, Map<Refrigerator, Integer>> allocated;
    private PriorityQueue<Facility> priority;
    private Facility lastReturned;

    public NaivePrioritizer(Facilities fs) {
        allocated = new HashMap<Facility, Map<Refrigerator, Integer>>();
        priority = new PriorityQueue<Facility>(fs.size(), new Comparator<Facility>() {

            // Decides comparison based only off of percent capacity
            @Override
            public int compare(Facility facility, Facility facility2) {
                double newCapacity1 = facility.getCurrentCapacity() + getAddedCapacity(allocated.get(facility));
                double newCapacity2 = facility2.getCurrentCapacity() + getAddedCapacity(allocated.get(facility2));
                return Double.compare(newCapacity1 / facility.getRequiredCapacity(),
                                      newCapacity2 / facility.getRequiredCapacity());
            }
        });
    }

    @Override
    public Facility next() {
        if (lastReturned != null) {
            priority.add(lastReturned);
        }
        lastReturned = priority.remove();
        return lastReturned;
    }

    @Override
    public void add(Refrigerator adding) {
        if (!allocated.containsKey(lastReturned)) {
            allocated.put(lastReturned, new HashMap<Refrigerator, Integer>());
        }
        Map<Refrigerator, Integer> currentAllocation = allocated.get(lastReturned);

        if (!currentAllocation.containsKey(adding)) {
            currentAllocation.put(adding, 0);
        }

        currentAllocation.put(adding, currentAllocation.get(adding) + 1);
    }

    @Override
    public void leave() {
        lastReturned = null;
    }

    @Override
    public boolean done() {
        return priority.isEmpty();
    }

    @Override
    public double currentTotalCapacity() {
        return lastReturned.getCurrentCapacity() + getAddedCapacity(allocated.get(lastReturned));
    }

    private double getAddedCapacity(Map<Refrigerator, Integer> refrigeratorIntegerMap) {
        double sum = 0.0;
        for (Refrigerator r : refrigeratorIntegerMap.keySet()) {
            sum += r.getVolume() * refrigeratorIntegerMap.get(r);
        }
        return sum;
    }

    @Override
    public Map<Facility, Map<Refrigerator, Integer>> result() {
        return allocated;
    }
}
