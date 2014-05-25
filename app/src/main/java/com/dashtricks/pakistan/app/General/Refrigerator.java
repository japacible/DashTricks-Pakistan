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

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWorking(boolean working) {
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
