package com.dashtricks.pakistan.app.general;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facilities implements Iterable<Facility> {
    private Set<Facility> facilities;

    public Facilities() {
        facilities = new HashSet<Facility>();
    }

    public void add(Facility facility) {
        facilities.add(facility);
    }

    @Override
    public Iterator<Facility> iterator() {
        return facilities.iterator();
    }
}
