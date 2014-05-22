package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility {
    private int population;
    private Set<RefrigeratorTypeAndCount> refrigerators;

    public Facility() {
        refrigerators = new HashSet<RefrigeratorTypeAndCount>();
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public void addRefrigerator(RefrigeratorTypeAndCount refrigerator) {
        refrigerators.add(refrigerator);
    }

    public int getPopulation() {
        return population;
    }
}
