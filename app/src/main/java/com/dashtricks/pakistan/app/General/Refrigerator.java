package com.dashtricks.pakistan.app.General;

/**
 * Created by Donohue on 5/7/14.
 */
public class Refrigerator {
    public static final int CURRENT_YEAR = 2014;

    int uniqueId;
    private double volume;
    private int age;
    private boolean working;

    public Refrigerator(int uniqueId, double volume, int age, boolean working) {
        this.uniqueId = uniqueId;
        this.volume = volume;
        this.age = age;
        this.working = working;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public double getVolume() {
        return volume;
    }

    public int getAge() {
        return age;
    }

    public boolean isWorking() {
        return working;
    }
}
