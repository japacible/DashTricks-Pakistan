package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;

/**
 * Created by Donohue on 5/7/14.
 */
public class Calculator {
    private static final int MONTHS_PER_YEAR = 12;

    public static double computeVolume(Facility f, ImmunizationPlan ip) {
        return baseVaccineVolume(f, ip) + diluantVolume(f, ip);
    }

    private static double baseVaccineVolume(Facility f, ImmunizationPlan ip) {
        return f.getPopulation() * ip.getDosePerPopulation() * ip.getVolumePerDose()
                * (1.0 / (1.0 - ip.getWasteFactor())) * (1.0 / f.getDeliveriesPerYear());
    }

    private static double diluantVolume(Facility f, ImmunizationPlan ip) {
        // If deliveries to this facility are made less often than monthly, we assume that
        // only a months worth of diluant is kept cold at any given time.
        return f.getPopulation() * ip.getDosePerPopulation() * ip.getDiluantVolumePerDose()
                * (1.0 / (1.0 - ip.getDiluantWasteFactor())) * Math.max(f.getDeliveriesPerYear(), MONTHS_PER_YEAR);
    }
}
