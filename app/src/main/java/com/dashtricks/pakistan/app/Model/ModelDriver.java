package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.General.ImmunizationPlans;
import com.dashtricks.pakistan.app.Utilities.FacilityToJson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class ModelDriver {
    private static Iterable<ImmunizationPlan> lastUsedIps = null;
    private static String lastJSON = "";

    private static void setAllVolumeRequirements(Iterable<Facility> fs, Iterable<ImmunizationPlan> ips) {
        for (Facility f : fs) {
            double sum = 0.0;
            for (ImmunizationPlan ip : ips) {
                double additionalRequirement = Calculator.computeVolume(f, ip);
                sum += additionalRequirement;
            }
            f.setRequiredCapacity(sum);
        }
    }

    /**
     *
     * Fills in "required capacity" for all facilities, will only recompute if a new set of
     * immunization plans are given. If null is passed for the immunization plans the
     * default set will be used.
     * @param fs
     * @param ips
     * @return
     */
    public static String requirementsAsJSON(Facilities fs, Iterable<ImmunizationPlan> ips) {
        if (ips == null) {
            ips = getDefaultIps();
        }
        if (ips.equals(lastUsedIps)) {
            return lastJSON;
        }

        setAllVolumeRequirements(fs, ips);
        return FacilityToJson.facilitiesToJson(fs);
    }

    public static Set<ImmunizationPlan> getDefaultIps() {
        Set<ImmunizationPlan> defaults = new HashSet<ImmunizationPlan>();

        ImmunizationPlan default1 = new ImmunizationPlan();
        default1.setName("BCG");
        default1.setDosePerPopulation(.026 * .9 * 1);
        default1.setVolumePerDose(1.2 / 20);
        default1.setWasteFactor(.5);
        default1.setDiluantVolumePerDose(.7 / 20);
        default1.setDiluantWasteFactor(.5);
        defaults.add(default1);

        ImmunizationPlan default2 = new ImmunizationPlan();
        default2.setName("DTP-HepB-Hib");
        default2.setDosePerPopulation(.026 * .9 * 3);
        default2.setVolumePerDose(12.84 / 1);
        default2.setWasteFactor(.05);
        default2.setDiluantVolumePerDose(0);
        default2.setDiluantWasteFactor(.05);
        defaults.add(default2);

        ImmunizationPlan default3 = new ImmunizationPlan();
        default3.setName("Measles");
        default3.setDosePerPopulation(.026 * .9 * 2);
        default3.setVolumePerDose(2.61 / 10);
        default3.setWasteFactor(.4);
        default3.setDiluantVolumePerDose(4 / 10);
        default3.setDiluantWasteFactor(.4);
        defaults.add(default3);

        ImmunizationPlan default4 = new ImmunizationPlan();
        default4.setName("OPV");
        default4.setDosePerPopulation(.026 * .9 * 4);
        default4.setVolumePerDose(1 / 20);
        default4.setWasteFactor(.2);
        default4.setDiluantVolumePerDose(0 / 20);
        default4.setDiluantWasteFactor(.2);
        defaults.add(default4);

        ImmunizationPlan default5 = new ImmunizationPlan();
        default5.setName("PVC10");
        default5.setDosePerPopulation(.026 * .9 * 3);
        default5.setVolumePerDose(4.8 / 2);
        default5.setWasteFactor(.05);
        default5.setDiluantVolumePerDose(0 / 2);
        default5.setDiluantWasteFactor(.05);
        defaults.add(default5);

        ImmunizationPlan default6 = new ImmunizationPlan();
        default6.setName("TT");
        default6.setDosePerPopulation(.026 * .9 * 5 * 1.1);
        default6.setVolumePerDose(2.5 / 20);
        default6.setWasteFactor(.2);
        default6.setDiluantVolumePerDose(0 / 20);
        default6.setDiluantWasteFactor(.2);
        defaults.add(default6);

        return defaults;
    }
}
