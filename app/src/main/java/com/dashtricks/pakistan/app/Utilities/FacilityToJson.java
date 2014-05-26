package com.dashtricks.pakistan.app.Utilities;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FacilityToJson {
    public static final Gson g = new Gson();

    public static String FacilityToJson(Facility f) {
        return g.toJson(f);
    }

//    Want to map from a sub-district name to a collection of facilities
//    Iterate over all facilities and add them to the appropriate place in the map
//    Wrap everything in square brackets, separate with commas
//    Hot damn, this is some C like java
    public static String facilitiesToJson(Facilities fs) {
        StringBuilder sb = new StringBuilder();

        Map<String, List<Facility>> subdisToFacs = separateFacilities(fs);
//        Beginning of container
        sb.append('{');

        for(String subdis : subdisToFacs.keySet()) {

            List<Facility> l = subdisToFacs.get(subdis);
            // subdistrict object entry
            StringBuilder append = sb.append(String.format("\"%s\":{\"facilities\":[", subdis));
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

    private static Map<String, List<Facility>> separateFacilities(Facilities fs) {
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
}