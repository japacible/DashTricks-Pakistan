package com.dashtricks.pakistan.app.Utilities;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// One big utility class that contains the ability to convert stuff to json as need be
// Kind of also the data accessor
// Constructed with and updated by a Facilities (forgive the singular/plural mismatch)
// That represents all facilities
public class DataAccessor {
    private static final int AGE_GROUP_CONST = 5;

    private Gson g;
    private Facilities fs;
    private Map<String, List<Facility>> subdisToFacs;
    ExcelToDatabaseConverter ecc;

    // Must be called before anything else gets called
    public DataAccessor(Facilities fs){
        this.fs = fs;
        subdisToFacs = separateFacilities(fs);
        g = new Gson();
    }

    // Call on changes to the facilities,
    // i.e. adding new refrigerators or creating new immunization plans
    public void UpdateFacilities(Facilities fs){
        this.fs = fs;
        subdisToFacs = separateFacilities(fs);
    }

    public static String getDistrict(Facility f){
        return f.getSubdis(); // hashing, pipelining, and another layer of indirection
    }

    public int getNumFacilities(){
        return fs.getNumFacilities();
    }

    public int getNumFacilities(String district){
        return (subdisToFacs.containsKey(district)) ? subdisToFacs.get(district).size() : -1;
    }

    public List<Facility> getFacilities(String district){
        return subdisToFacs.get(district);
    }

    public double getAmountDeficient(String district){
        double shortage = 0;
        for(Facility f : subdisToFacs.get(district)){
            shortage += f.getAmountShortBy();
        }
        return shortage;
    }

    public double getPercentDeficient(String district){
        double actualCapacity = 0;
        double requiredCapacity = 0;

        // don't count over capacity, as a facility's surplus capacity shouldn't help this metric
        for(Facility f : subdisToFacs.get(district)){
            requiredCapacity += f.getRequiredCapacity();
            actualCapacity += Math.min(f.getRequiredCapacity(), f.getCurrentCapacity());
        }

        requiredCapacity = (requiredCapacity == 0) ? 1.0 : requiredCapacity; // avoid divide by 0

        return (1 - (actualCapacity / requiredCapacity)) * 100;
    }

    public double getAmountDeficient(Facility f){
        return f.getAmountShortBy();
    }

    public double getPercentDeficient(Facility f){
        return f.getPercentDeficient();
    }

    public String FacilityToJson(Facility f) {
        return g.toJson(f);
    }

//    Want to map from a sub-district name to a collection of facilities
//    Iterate over all facilities and add them to the appropriate place in the map
//    Wrap everything in square brackets, separate with commas
//    Hot damn, this is some C like java
    public String facilitiesToJson() {
        StringBuilder sb = new StringBuilder();

//        Beginning of container
        sb.append('{');

        for(String subdis : subdisToFacs.keySet()) {

            List<Facility> l = subdisToFacs.get(subdis);
            // subdistrict object entry

            StringBuilder append = sb.append(String.format("\"%s\":{\"num_facilities\":%d,\"facilities\":[", l.size(), subdis));
            for(Facility f : l) {
                sb.append(g.toJson(f));
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1); // remove the trailing comma
            sb.append(']'); // close the sub district
            sb.append(',');
        }

//        End of container
        sb.deleteCharAt(sb.length() - 1); // remove the trailing comma
        sb.append('}');
        return sb.toString();
    }

    private Map<String, List<Facility>> separateFacilities(Facilities fs) {
        Map<String, List<Facility>> ret = new HashMap<String, List<Facility>>();

        for(Facility f : fs){
            if(ret.containsKey(f.getSubdis())) {
                ret.get(f.getSubdis()).add(f);
            } else {
                ret.put(f.getSubdis(), new ArrayList<Facility>());
            }
        }
        return ret;
    }

    public String refrigeratorToJson(Refrigerator r){
        return g.toJson(r);
    }

    public int numDeficientFacilities() {
        int n = 0;

        for(Facility f : fs){
            if(f.getPercentDeficient() > 0.0) {
                n++;
            }
        }

        return n;
    }

    public List<Facility> getDeficientFacilities(){
        List<Facility> fl = new LinkedList<Facility>();

        for(Facility f : fs){
            if(f.getPercentDeficient() > 0.0) {
                fl.add(f);
            }
        }

        return fl;
    }

    // Group refrigerators into age groups
    // A refrigerator in group n is between the ages of
    // n * AGE_GROUP_CONST and (n + 1) * AGE_GROUP_CONST - 1
    public Map<Integer, List<Refrigerator>> createFridgeAgeGroups(){
        List<Refrigerator> refs = new ArrayList<Refrigerator>(500); // There 462 fridges in the sheet

        for(Facility f : fs) {
            for (Refrigerator r : f) {
                refs.add(r);
            }
        }

        return partitionRefrigerators(refs);
    }

    private Map<Integer, List<Refrigerator>> partitionRefrigerators(List<Refrigerator> refs) {
        Map<Integer, List<Refrigerator>> ret = new TreeMap<Integer, List<Refrigerator>>();

        for(Refrigerator r : refs) {
            int ageGroup = r.getAge() / AGE_GROUP_CONST;
            if(ret.containsKey(ageGroup)){
                ret.get(ageGroup).add(r);
            } else {
                LinkedList<Refrigerator> ageList= new LinkedList<Refrigerator>();
                ageList.add(r);
                ret.put(ageGroup, ageList);
            }
        }

        return ret;
    }

    public String getDistrictHeatNumberAsJson(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(Facility f : fs) {
            sb.append("{");
            sb.append("\"Region\":\"");
            sb.append(f.getAdminRegion());
            sb.append("\",");
            sb.append("\"heatNumber\":");
            sb.append((int)f.getPercentDeficient());
            sb.append("},");
        }

        sb.deleteCharAt(sb.length() - 1); // get rid of trailing comma
        sb.append("]");
        return sb.toString();
    }
}