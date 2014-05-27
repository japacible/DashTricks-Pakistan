package com.dashtricks.pakistan.app.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 5/19/2014.
 */
public class ListTypeFacilityLab {
    private ArrayList<ListTypeFacility> mFacilities;

    private static ListTypeFacilityLab fLab;
    private Context mAppContext;

    private ListTypeFacilityLab(Context appContext){
        mAppContext = appContext;

        mFacilities = new ArrayList<ListTypeFacility>();
        for(int i = 0; i < 100; i++) {
            ListTypeFacility ltf = new ListTypeFacility(i);
            mFacilities.add(ltf);
        }
    }

    public static ListTypeFacilityLab get(Context c) {
        if(fLab == null) {
            fLab = new ListTypeFacilityLab(c.getApplicationContext());
        }
        return fLab;
    }

    public ArrayList<ListTypeFacility> getFacilitiesList() {
        return mFacilities;
    }

    public ListTypeFacility getListTypeFacility(int necessityPosition) {
        for (ListTypeFacility f: mFacilities) {
            if(f.getCapacitySortPosition() == necessityPosition) {
                return f;
            }
        }
        return null;
    }

    public ListTypeFacility getFacility(int position) {
        return mFacilities.get(position);
    }

}
