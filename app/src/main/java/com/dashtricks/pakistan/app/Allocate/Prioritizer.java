package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public interface Prioritizer {
    // Get the next facility to allocate a refrigerator to
    public Facility next();

    // Add a new refrigerator to the facility last returned by next
    public void add(Refrigerator allocated);

    // Cease trying to allocate refrigerators to the facility last returned by next
    public void leave();

    // True if there are more facilities to allocate refrigerators to
    public boolean done();

    // Returns the total capacity of the facility last returned by next
    public double currentTotalCapacity();

    public Map<Facility, Map<Refrigerator, Integer>> result();
}