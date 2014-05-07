package com.dashtricks.pakistan.app.general;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class ImmunizationPlans implements Iterable<ImmunizationPlan> {
    private Set<ImmunizationPlan> immunizationPlans;

    public ImmunizationPlans() {
        immunizationPlans = new HashSet<ImmunizationPlan>();
    }

    public void add(ImmunizationPlan ip) {
        immunizationPlans.add(ip);
    }

    @Override
    public Iterator<ImmunizationPlan> iterator() {
        return immunizationPlans.iterator();
    }
}
