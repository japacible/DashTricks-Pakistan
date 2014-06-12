package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.TheApplication;
import com.dashtricks.pakistan.app.Utilities.DataAccessor;

/**
 * Created by japacible on 5/25/14.
 */
public class OverallStatistics {
    private int totalFacilitiesCount;
    private int attentionFacilitiesCount;
    private int totalPopulation;
    private int coveredPopulation;
    private double percentageCapacity;
    private int totalRequiredCapacity;
    private int totalCoveredCapacity;
    private DataAccessor da;

    public OverallStatistics() {
        // TODO: get data from db rather than hardcoding
        //      currently pending having the accessor functionality
        // da = new DataAccessor(); // Need Facilities with all facilities
        totalFacilitiesCount = 1923; // da.getNumFacilities();
        attentionFacilitiesCount = 249;
        totalPopulation = 179200000;
        coveredPopulation = 11284011;
        percentageCapacity = (double) totalPopulation / (double) coveredPopulation;
        totalRequiredCapacity = 110291142;
        totalCoveredCapacity = 2202119;
    }

    public int getTotalFacilitiesCount() {
        return totalFacilitiesCount;
    }

    public int getAttentionFacilitiesCount() {
        return attentionFacilitiesCount;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getCoveredPopulation() {
        return coveredPopulation;
    }

    public double getPercentageCapacity() {
        return percentageCapacity;
    }

    public int getTotalRequiredCapacity() {
        return totalRequiredCapacity;
    }

    public int getTotalCoveredCapacity() {
        return totalCoveredCapacity;
    }
}