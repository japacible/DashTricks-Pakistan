package com.dashtricks.pakistan.app;

import java.lang.Override;import java.lang.String;import java.util.List;

/**
 * A supplier is a facility that distributes vaccines to other facilities
 */
public class Supplier extends Facility {

    private List<Facility> consumerFacilities;

    public Supplier(String facilityId, String location, Facility supplier, List<Facility> consumerFacilities) {
        super(facilityId, location, supplier);
        this.consumerFacilities = consumerFacilities;
    }

    @Override
    public int getPopulation() {
        int sum = 0;
        for (Facility f : consumerFacilities) {
            sum += f.getPopulation();
        }
        return sum;
    }

    @Override
    public List<Facility> getConsumerFacilities() {
        return consumerFacilities;
    }
}
