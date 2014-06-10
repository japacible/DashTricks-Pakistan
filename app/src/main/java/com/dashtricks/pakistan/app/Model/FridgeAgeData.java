package com.dashtricks.pakistan.app.Model;

/**
 * Created by Dan on 5/28/2014.
 */
public class FridgeAgeData {

    private String facilityName;
    private String refrigeratorModel;
    private String province;
    private int yearMade;
    private int age;

    public FridgeAgeData(int i, int years) {
        this.facilityName = getRandomFacilityName(i);
        this.refrigeratorModel = getRandomFridgeModel(i);
        this.province = getProvinceName(i);
        this.age = years - ((int)(Math.random()*4));
        this.yearMade = 2014 - this.age;
    }

    public int getAge() {
        return age;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getProvince() {
        return province;
    }

    public String getRefrigeratorModel() {
        return refrigeratorModel;
    }

    private String getProvinceName(int i) {
        String[] names = {"Punjab", "Sindh", "Khyber Pakhtunkhwa", "Balochistan"};
        int num = (i*2)%4;
        return names[num];
    }

    private String getRandomFacilityName(int i) {
        String[] facilityNames = {"MILITARY HOSPITAL RAWALPINDI", "BHU HALLOKI", "NISHTE EPI CENTER",
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

        int num = (i + 5) % facilityNames.length;

        return facilityNames[num];
    }

    private String getRandomFridgeModel(int i) {
        String[] fridgeModels = {"MK 074", "MK 302", "HBD-286", "WF-210", "PR 25-2D Ultra", "TCW 3000", "131", "MF 214", "407A", "TCW 1990",
                                  "RLF670B", "TCW 1990", "9144M", "ER8", "HBD-116", "Philips Tropical"};

        int num = (i + 3) % fridgeModels.length;
        return fridgeModels[num];
    }


}
