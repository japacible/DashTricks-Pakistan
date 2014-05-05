package com.dashtricks.pakistan.app;

import java.lang.Override;import java.lang.String;import java.util.List;

/**
 * A facility that gets vaccines from a supplier and distributes them to the local population
 */
public class Endpoint extends Facility {

    private int population;
    private Facility supplier;

    public Endpoint (String facilityId, String location, Facility supplier, int population) {
        super(facilityId, location, supplier);
        this.population = population;
        this.supplier = supplier;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    // Endpoints should never have other facilities getting resources from it
    @Override
    public List<Facility> getConsumerFacilities() {
        return null;
    }
}
