package com.dashtricks.pakistan.app.General;

/**
 * Created by Donohue on 5/7/14.
 */
public class Refrigerator {
    private String name;
    private double volume;
    private PowerSource ps;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setPowerSource(PowerSource ps) {
	this.ps = ps;
    }

    public PowerSource getPowerSource() {
	return ps;
    }
    
    
}
