package com.dashtricks.pakistan.app.Model;

/**
 * Created by Dan on 6/2/2014.
 */
public class OverallFacilitiesStatistics {

    private int numberOfFacilities;
    private int facilitiesNeedOfAttention;
    private String population;
    private String populationCovered;
    private String populationCoveredPercentage;

    public OverallFacilitiesStatistics() {
        numberOfFacilities = 350;
        facilitiesNeedOfAttention = 98;
        population = "179,000,000";
        populationCovered = "120,000,000";
        populationCoveredPercentage = "67%";

    }

    public int getNumberOfFacilities() {
        return numberOfFacilities;
    }

    public int getFacilitiesNeedOfAttention() {
        return facilitiesNeedOfAttention;
    }

    public String getPopulation() {
        return population;
    }

    public String getPopulationCovered() {
        return populationCovered;
    }

    public String getPopulationCoveredPercentage() {
        return populationCoveredPercentage;
    }
}

