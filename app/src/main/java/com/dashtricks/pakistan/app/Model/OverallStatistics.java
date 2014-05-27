package com.dashtricks.pakistan.app.Model;

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

    public OverallStatistics() {
        totalFacilitiesCount = 0;
        attentionFacilitiesCount = 0;
        totalPopulation = 0;
        coveredPopulation = 0;
        percentageCapacity = 0;
        totalRequiredCapacity = 0;
        totalCoveredCapacity = 0;
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