package com.dashtricks.pakistan.app.test;

import android.test.AndroidTestCase;

import com.dashtricks.pakistan.app.Allocate.AllocationDriver;
import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.General.ImmunizationPlans;
import com.dashtricks.pakistan.app.General.PowerSource;
import com.dashtricks.pakistan.app.General.Refrigerator;
import com.dashtricks.pakistan.app.General.Refrigerators;
import com.dashtricks.pakistan.app.Model.ModelDriver;
import com.dashtricks.pakistan.app.Model.VolumeRequirement;

import java.util.HashSet;
import java.util.Set;

/**
 * Grabbed from here http://rexstjohn.com/unit-testing-with-android-studio/
 * Check there to see why I have configured things how I did, and how to
 * get your ide to play nice with test cases.
 */
public class ExampleTest extends AndroidTestCase {
    public void testBasicWorkflow() throws Exception {
        /*
        In the future Facilities, Refrigerators & ImmunizationPlans will populate itself with calls to db
        For testing & development purposes we can create and add to it manually
         */

        ImmunizationPlans ip = new ImmunizationPlans();

        ImmunizationPlan ip1 = new ImmunizationPlan();
        ImmunizationPlan ip2 = new ImmunizationPlan();


        ip1.setDosePerPopulation(.01);
        ip2.setDosePerPopulation(.1);

        ip1.setVolumePerDose(1.0);
        ip2.setVolumePerDose(2.0);

        ip1.setWasteFactor(.1);
        ip2.setWasteFactor(.2);

        ip.add(ip1);
        ip.add(ip2);

        Refrigerators r = new Refrigerators();

        Refrigerator r1 = new Refrigerator();

        r1.setUniqueId(666);
        r1.setVolume(5.0);

        r.add(r1);

        Facilities f = new Facilities();

        Set<PowerSource> pss1 = new HashSet<PowerSource>();
        pss1.add(PowerSource.ELECTRICITY);
        pss1.add(PowerSource.GAS);

        Set<PowerSource> pss2 = new HashSet<PowerSource>();
        pss2.add(PowerSource.KEROSENE);
        pss2.add(PowerSource.ELECTRICITY);

        Facility f1 = new Facility("Fac1", 20, pss1, 9, null);
        Facility f2 = new Facility("Fac2", 30, pss2, 9, null);

        f1.setPopulation(10);
        f2.setPopulation(100);

        f1.addRefrigerator(r1);
        f2.addRefrigerator(r1);

        f.add(f1);
        f.add(f2);

        /*
        There will be some additional layer of filtering before we pass the immunization plans to
        the ModelDriver. I'm not sure at what point and in what way this is best coorenated with
        the visualizer view
        */

        Set<VolumeRequirement> beforeAllocation = ModelDriver.getRequirements(f, ip);

        /*
        Again, not sure if just doing this with a set is the right way, or if we should have
        a dedicated "UnallocatedRefrigerators" object. The answer is *probably* we should have
        an object, because we might want to have an object we can make complex queries to when
        allocating refrigerators. But I'm not exactly sure how that would look
         */
        Set<Refrigerator> rtac = new HashSet<Refrigerator>();
        Set<VolumeRequirement> afterAllocation = AllocationDriver.allocate(beforeAllocation, rtac);

        // also unsure if the Set<VolumeRequirements> should be it's own class. My gut instinct is
        // yes, but I'm not convinced
        // D3.doSomePrettyMagic(beforeAllocation, afterAllocation //WOO pretty
    }
}
