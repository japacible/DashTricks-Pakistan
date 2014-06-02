package com.dashtricks.pakistan.app.General;

/**
 * Created by Donohue on 5/7/14.
 */
public class ImmunizationPlan {
    private double dosePerPopulation;
    private double volumePerDose;
    private double wasteFactor;
    private double diluantVolumePerDose;
    private double diluantWasteFactor;

    public void setDosePerPopulation(double dosePerPopulation) {
        this.dosePerPopulation = dosePerPopulation;
    }

    public double getDosePerPopulation() {
        return dosePerPopulation;
    }

    public void setVolumePerDose(double volumePerDose) {
        this.volumePerDose = volumePerDose;
    }

    public double getVolumePerDose() {
        return volumePerDose;
    }

    public void setWasteFactor(double wasteFactor) {
        this.wasteFactor = wasteFactor;
    }

    public double getWasteFactor() {
        return wasteFactor;
    }

    public double getDiluantVolumePerDose() {
        return diluantVolumePerDose;
    }

    public void setDiluantVolumePerDose(int diluantVolumePerDose) {
        this.diluantVolumePerDose = diluantVolumePerDose;
    }

    public double getDiluantWasteFactor() {
        return diluantWasteFactor;
    }

    public void setDiluantWasteFactor(double diluantWasteFactor) {
        this.diluantWasteFactor = diluantWasteFactor;
    }
}
