package com.dashtricks.pakistan.app.model;

import com.dashtricks.pakistan.app.general.Facility;
import com.dashtricks.pakistan.app.general.ImmunizationPlan;

/**
 * Created by Donohue on 5/7/14.
 */
public class Calculator {
    public static double computeVolume(Facility f, ImmunizationPlan ip) {
        return baseVaccineVolume(f, ip) + diluantVolume(f, ip);
    }

    private static double baseVaccineVolume(Facility f, ImmunizationPlan ip) {
        return f.getPopulation() * ip.getDosePerPopulation() * ip.getVolumePerDose()
                * (1 / (1-ip.getWasteFactor())); // * 1/deliveries per year; still unsure on that data's exact form
    }

    private static int diluantVolume(Facility f, ImmunizationPlan ip) {
        return 0; // We still aren't sure yet exactly how we're getting diluent info...
    }
}
