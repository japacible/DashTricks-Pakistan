package com.dashtricks.pakistan.app.General;

/**
 * Created by Donohue on 5/7/14.
 */
public class Refrigerator implements Comparable<Refrigerator>{
    private String name;
    private double volume;
    private PowerSource ps;
    private int age; // measured in years
    private boolean working;
    private boolean fixable;

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

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setState(boolean working, boolean fixable) {
        this.working = working;
        this.fixable = fixable;
    }

    public boolean isWorking() {
        return working;
    }

    public boolean isFixable() {
        return fixable;
    }

    public int compareTo(Refrigerator ref) {
        if(volume != ref.volume){
            return (int)(volume - ref.volume);
        } else {
            return ps.get() - ref.ps.get();
        }
    }
}
