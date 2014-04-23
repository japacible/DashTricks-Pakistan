package com.dashtricks.pakistan.app;

import java.lang.Math;public class ImmunizationPlan {
    // OUTLINE OF THE PROCESS TO CALCULATE VACCINE VOLUMES
    // FOR THE VACCINE SUPPLY CHAIN:
    // "7 parameters for peak volume"
    // Many of these will probably turn into callbacks or other lookups
    private int totalPopulation;
    private double percentTargetPopulation;
    private int deliveriesPerYear;
    private double percentWastedVaccine;
    private double percentStockReserve;
    private double volumePerDose;
    private int dosesPerSeries;

    public ImmunizationPlan(int totalPopulation, double percentTargetPopulation, int deliveriesPerYear, double percentWastedVaccine, double percentStockReserve, int volumePerDose, int dosesPerSeries) {
        this.totalPopulation = totalPopulation;
        this.percentTargetPopulation = percentTargetPopulation;
        this.deliveriesPerYear = deliveriesPerYear;
        this.percentWastedVaccine = percentWastedVaccine;
        this.percentStockReserve = percentStockReserve;
        this.volumePerDose = volumePerDose;
        this.dosesPerSeries = dosesPerSeries;
    }

    public double getPeakVolume() {
        double basePeakVolume = getYearlyVolume() / deliveriesPerYear;
        double reserveVolume = basePeakVolume * percentStockReserve;
        return basePeakVolume + reserveVolume + getDiluantVolume();
    }

    private double getDiluantVolume() {
        //Is there an established way we will be told how much diluant needs to be held at a time?
        return 0;
    }

    private double getYearlyVolume() {
        return volumePerSeries() * populationToBeImmunized() / percentWastedVaccine;
    }

    private double volumePerSeries() {
        return volumePerDose * dosesPerSeries;
    }

    private int populationToBeImmunized() {
        return (int) Math.ceil(totalPopulation * percentTargetPopulation);
    }
}
