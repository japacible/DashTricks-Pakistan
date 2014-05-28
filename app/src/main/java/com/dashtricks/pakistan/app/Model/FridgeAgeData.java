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

    public FridgeAgeData(int i) {
        this.facilityName = getRandomFacilityName((int)(Math.random()*10));
        this.refrigeratorModel = getRandomFridgeModel(i);
        this.province = "Punjab";
        this.yearMade = 1975 + (int)(Math.random()*29);
        this.age = 2014 - yearMade;
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

    private String getRandomFacilityName(int i) {
        int num = i % 10;
        String[] facilityNames = {"MILITARY HOSPITAL RAWALPINDI", "BHU HALLOKI", "NISHTE EPI CENTER",
                "MUZANG HOSPITAL", "CD SANDA", "CB DISPENSARY WALTON", "BHU CHAPA", "BHU GHAWIND",
                "BHU LEADR", "SAMANABAD HOSPITAL"};

        return facilityNames[num];
    }

    private String getRandomFridgeModel(int i) {
        int num = i % 10;
        String[] fridgeModels = {"MK 074", "MK 302", "HBD-286", "WF-210", "PR 25-2D Ultra", "TCW 3000", "131", "MF 214", "407A", "TCW 1990"};

        return fridgeModels[num];
    }


}
