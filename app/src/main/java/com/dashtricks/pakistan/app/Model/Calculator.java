package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;

/**
 * Created by Donohue on 5/7/14.
 */
public class Calculator {
    private static final int WEEKS_PER_YEAR = 52;
    private static final int WEEKS_OF_COLD_DILUANT = 4;

    public static double computeVolume(Facility f, ImmunizationPlan ip) {
        return baseVaccineVolume(f, ip) + baseDiluantVolume(f, ip);
    }

    private static double baseVaccineVolume(Facility f, ImmunizationPlan ip) {
        double yearsVolume = yearsVaccineVolume(f, ip);
        return (yearsVolume * (f.getWeeksBetweenDelivery() / WEEKS_PER_YEAR )) +
               (yearsVolume * (f.getWeeksOfReserve() / WEEKS_PER_YEAR ));
    }

    private static double yearsVaccineVolume(Facility f, ImmunizationPlan ip) {
        return f.getPopulation() * ip.getDosePerPopulation() *
               ip.getVolumePerDose() * (1.0 / (1.0 - ip.getWasteFactor()));
    }

    private static double baseDiluantVolume(Facility f, ImmunizationPlan ip) {
        return yearsDiluantVolume(f, ip) * (WEEKS_OF_COLD_DILUANT / WEEKS_PER_YEAR );
    }

    private static double yearsDiluantVolume(Facility f, ImmunizationPlan ip) {
        return f.getPopulation() * ip.getDosePerPopulation() * ip.getDiluantVolumePerDose()
                * (1.0 / (1.0 - ip.getDiluantWasteFactor()));

    }
}
