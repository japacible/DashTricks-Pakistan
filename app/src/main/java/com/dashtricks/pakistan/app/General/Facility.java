package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility {
    private String name;
    private int facId;
    private String subdis;
    private double currentCapacity; // no direct set. Updated by adding refrigerators
    private double requiredCapacity; // Should ONLY be set with a value returned by the Calculator
    private double amountShortBy;
    private double percentDeficient;
    private int population;
    private Set<PowerSource> powerSources;
    private Set<Refrigerator> refrigerators;

    // All these things 
    public Facility(String name, int facId, Set<PowerSource> ps) {
        this.name = name;
        this.facId = facId;
        this.powerSources = ps;
        refrigerators = new HashSet<Refrigerator>();
        populateRefrigerators(refrigerators);
        for(Refrigerator r : refrigerators) {
            if(r.isWorking()){
                currentCapacity += r.getVolume();
            }
        }
    }

//    Select * from
    private void populateRefrigerators(Set<Refrigerator> refrigerators) {

    }

    public String getName() {
	return name;
    }
    
    public int getFacId() {
	return facId;
    }

    public boolean canUseSource(PowerSource p) {
	return powerSources.contains(p);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void addRefrigerator(Refrigerator refrigerator) {
        refrigerators.add(refrigerator);
        if(refrigerator.isWorking()){
            currentCapacity += refrigerator.getVolume();
        }
    }

    public int getPopulation() {
        return population;
    }

    // No setter method because this is calculated based on refrigerators
    public double getCurrentCapacity() {
	    return currentCapacity;
    }

    // Done because Calculator imports Facility, and circular dependencies are ugly
    public void setRequiredCapacity(double rc){
        requiredCapacity = rc;
        amountShortBy = rc - currentCapacity;
        percentDeficient = (1 - currentCapacity/requiredCapacity) * 100;
    }

    public double getRequiredCapacity() {
	return requiredCapacity;
    }

    public String getSubdis() {
        return subdis;
    }

}
