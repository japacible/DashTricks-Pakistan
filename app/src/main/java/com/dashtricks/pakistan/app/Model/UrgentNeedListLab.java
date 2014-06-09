package com.dashtricks.pakistan.app.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by japacible on 6/8/14.
 */
public class UrgentNeedListLab {
    private HashMap<String, List<ListTypeFacility>> mFacilities;

    private static UrgentNeedListLab fLab;
    private Context mAppContext;

    private UrgentNeedListLab(Context appContext){
        mFacilities = new HashMap<String, List<ListTypeFacility>>();
        DistrictToFacilitySetup();
    }

    public static UrgentNeedListLab get(Context c) {
        if(fLab == null) {
            fLab = new UrgentNeedListLab(c.getApplicationContext());
        }
        return fLab;
    }

    public HashMap<String, List<ListTypeFacility>> getFacilitiesList() {
        return mFacilities;
    }

    private void DistrictToFacilitySetup() {
        String[] districts = {"Attock", "Bahawalnagar", "Bahawalpur", "Bhakkar", "Chakwal", "Chiniot", "Dera Ghazi Khan",
                "Faisalabad", "Gujranwala", "Gujrat", "Hafizabad", "Jhang", "Jhelum", "Kasur", "Khanewal", "Khushab",
                "Lahore", "Layyah", "Lodhran", "Mandi Bahauddin", "Mianwali", "Multan", "Muzaffargarh", "Narowal",
                "Nankana Sahib", "Okara", "Pakpattan", "Rahim Yar Khan", "Rajanpur", "Rawalpindi", "Sahiwal", "Sargodha",
                "Sheikhupura", "Sialkot", "Toba Tek Singh", "Vehari"};

        for(String d: districts) {
            List<ListTypeFacility> temp = new ArrayList<ListTypeFacility>();
            for(int i = 0; i < 100; i++) {
                ListTypeFacility ltf = new ListTypeFacility((int)(Math.random()*i));
                temp.add(ltf);
            }
            mFacilities.put(d, temp);
        }
    }

    public List<ListTypeFacility> getUrgentFacilitiesList() {
        List<ListTypeFacility> ltfl = new ArrayList<ListTypeFacility>();
        for(int i = 0; i < 605; i++) {
            Random r = new Random();
            double percent = (double) r.nextInt(80);

            ListTypeFacility ltf = new ListTypeFacility((int)(Math.random()*100), (double) percent);
            ltfl.add(ltf);
        }

        return ltfl;
    }

    public List<ListTypeFacility> getOkayFacilitiesList() {
        List<ListTypeFacility> ltfl = new ArrayList<ListTypeFacility>();
        for(int i = 0; i < 1523; i++) {
            Random r = new Random();
            double percent = (double) r.nextInt(20) + 80;

            ListTypeFacility ltf = new ListTypeFacility((int)(Math.random()*100), (double) percent);
            ltfl.add(ltf);
        }

        return ltfl;
    }
}
