package com.dashtricks.pakistan.app;

import java.lang.String;import java.util.List;

/**
 * Facility is any place that vaccines go.
 * They could be an endpoint where they server a local population
 * or they can be a distribution center that will serve various
 * other distribution centers or endpoints.
 */
public abstract class Facility {

    // TODO There are probably going to be a bunch more of these. Expand as needed.
    private String location;
    private String id;
    private Facility supplier;

    public Facility(String facilityId, String location, Facility supplier) {
        this.location = location;
        this.id = facilityId;
        this.supplier = supplier;
    }

    abstract public int getPopulation();
    abstract public List<Facility> getConsumerFacilities(); // Do we want to just adopt CS terminology and go with parent and children facilities?

    public String getLocation() {
        return location;
    }

    public boolean isSupplier() {
        return getConsumerFacilities() != null;
    }

    public boolean isConsumer() {
        return getSupplierFacility() != null;
    }

    public String getFacilityId() {
        return id;
    }

    public Facility getSupplierFacility() {
        return supplier;
    }

}
