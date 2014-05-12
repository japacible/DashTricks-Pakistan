package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility {
    private String name;
    private String facId;
    private double latitude;
    private double longitude;
    private double currentCapacity; // no direct set. Updated by adding refrigerators
    private double requiredCapacity; // Should ONLY be set with a value returned by the Calculator
    private Set<PowerSource> powerSources;
    
    private int population;
    private Set<RefrigeratorTypeAndCount> refrigerators;

    // All these things 
    public Facility(String name, String facId,
		    double latitude, double longitude, Set<PowerSource> ps) {
	this.name = name;
	this.facId = facId;
	this.latitude = latitude;
	this.longitude = longitude;
	this.powerSources = ps;
	currentCapacity = 0;
	requiredCapacity = 0;
        refrigerators = new HashSet<RefrigeratorTypeAndCount>();
    }

    public String getName() {
	return name;
    }
    
    public String getFacId() {
	return facId;
    }
    
    public double getLatitude() {
	return latitude;
    }
    
    public double getLongitude() {
	return longitude;
    }

    public boolean canUseSource(PowerSource p) {
	return powerSources.contains(p);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void addRefrigerator(RefrigeratorTypeAndCount refrigerator) {
        refrigerators.add(refrigerator);
	currentCapacity += refrigerator.getType().getVolume() * refrigerator.getCount();
    }

    public int getPopulation() {
        return population;
    }

    // No setter method because this is calculated based on refrigerators
    public int getCurrentCapacity() {
	return currentCapacity;
    }

    // Done because Calculator imports Facility, and circular dependencies are ugly
    public void setRequiredCapacity(double rc){
	requiredCapacity = rc;
    }

    public double getRequiredCapacity() {
	return requiredCapacity;
    }
    
}
