package com.dashtricks.pakistan.app.Model;

import android.util.Log;

import java.util.UUID;

/**
 * Created by Dan on 5/19/2014.
 */
public class ListTypeFacility {
    private int capacitySortPosition;
    private String facilityName;
    private String facilityID;
    private int currentCapacity;
    private int requiredCapacity;
    private int population;
    private double percentageCapacity;
    private String powerSource1;
    private String powerSource2;
    private String powerSource3;

    // Currently using position to create different facilities.
    // TODO: Fix this automation once we get real facility data from the backend
    public ListTypeFacility(int position) {
        capacitySortPosition = position;
        facilityName = getRandomFacilityName(capacitySortPosition);
        facilityID = "AZ" + capacitySortPosition*3 + "OB" + capacitySortPosition*4;
        currentCapacity = capacitySortPosition + 2;
        requiredCapacity = capacitySortPosition + 5;
        population = 30792;
        percentageCapacity = ((double)currentCapacity)/((double)requiredCapacity) * ((double)10);
        powerSource1 = "Electricity";
        powerSource2 = "Gas";
        powerSource3 = "Kerosene";
    }

    public ListTypeFacility(int position, double capacityPercent) {
        capacitySortPosition = position;
        facilityName = getRandomFacilityName(capacitySortPosition);
        facilityID = "AZ" + capacitySortPosition*3 + "OB" + capacitySortPosition*4;
        currentCapacity = capacitySortPosition + 2;
        requiredCapacity = capacitySortPosition + 5;
        population = 30792;
        percentageCapacity = capacityPercent;
        powerSource1 = "Electricity";
        powerSource2 = "Gas";
        powerSource3 = "Kerosene";
    }

    public String getFacilityName() { return facilityName; }

    public String getFacilityID() {
        return facilityID;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public int getCapacitySortPosition() {
        return capacitySortPosition;
    }

    public int getPopulation() {
        return population;
    }

    public double getPercentageCapacity() {
        return percentageCapacity;
    }

    public String getPowerSource1() {
        return powerSource1;
    }

    public String getPowerSource2() {
        return powerSource2;
    }

    public String getPowerSource3() {
        return powerSource3;
    }

    @Override
    public String toString() {
        return facilityName;
    }

    // Temporary method to get random facility names while backend
    // syncs up with front end
    private String getRandomFacilityName(int i) {
        int num = (int)(Math.random()*(i*10)) % 52;
        String[] facilityNames = {"BHU HALLOKI", "NISHTE EPI CENTER",
                "MUZANG HOSPITAL", "CD SANDA", "CB DISPENSARY WALTON", "BHU CHAPA", "BHU GHAWIND",
                "BHU LEADR", "SAMANABAD HOSPITAL", "RHC Mianwali Qureshian", "District Vaccine Store",
                "District Vaccine Store\n", "RHC Sihala\n", "District EPI Store\n", "District Vaccine Store RY Khan\n",
                "BHU Dara Shamas", "RHC Mianwali Qureshian", "BHU Kot karam Khan", "BHU GKM Jhak",
                "RHC Bhong", "BHU Badli Sharif", "BHU Adem Wali",
                "BHU Fatehpur Panjabian", "BHU Malik Pur", "BHU Ghazipur", "BHU Ghose Pur",
                "BHU","BHU Muhammad Daha","Tehsil Epi Store Liqatpur RYKhan","BHU 45 A","BHU 25A",
                "BHU Ck 1A Doshakha","RHC Tranda Saway Khan","BHU Sonak","BHU Gulshan Dara",
                "BHU Chak Abbas","BHU Khair PurK hadali","Epi Center Chok Paltanistan","Epi Center 36 G",
                "MCH Center", "Epi Center Sheikh Zeyed","RHC Pacca Laran","BHU Haroon Abad","BHU Havaili Gharib Shah",
                "BHU Arif Baloch","BHU Goth Mahi","Chak 186 P","RHC Sehja","BHU Bahishti","RHC Feroza",
                "CHAK 186 7R","BHU Roti Sharif"};

        return facilityNames[num];
    }

    public static String getRandomFacilityNameOther(int i) {
        int num = i % 10;
        String[] facilityNames = {"Relway Hospital", "Dispensary Akal Garh", "Disp Imam Bara",
                "MMC Mangtayal", "BHU Sukhoo", "TB Hospital EID Gah", "RHC Mandra", "TB Hospital EID Gah",
                "Tehsil EPI Store", "RHC Nawa Kot"};

        return facilityNames[num];
    }
}
