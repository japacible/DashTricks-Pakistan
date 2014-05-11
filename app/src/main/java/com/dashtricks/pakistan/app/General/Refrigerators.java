package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Refrigerators {
    private Set<Refrigerator> refrigerators;

    public Refrigerators() {
        refrigerators = new HashSet<Refrigerator>();
    }

    public void add(Refrigerator r) {
        refrigerators.add(r);
    }
}
