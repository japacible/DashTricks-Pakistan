package com.dashtricks.pakistan.app.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dan on 5/19/2014.
 */
public class FridgeListLab {
    private List<FridgeAgeData> mFacilities;

    private static FridgeListLab fLab;
    private Context mAppContext;

    private FridgeListLab(Context appContext){
        mFacilities = new ArrayList<FridgeAgeData>();
        FridgeListSetup();
    }

    public static FridgeListLab get(Context c) {
        if(fLab == null) {
            fLab = new FridgeListLab(c.getApplicationContext());
        }
        return fLab;
    }

    public List<FridgeAgeData> getFridgeData() {
        return mFacilities;
    }

    public List<FridgeAgeData> FridgeListSetup() {
        List<FridgeAgeData> mfac = new ArrayList<FridgeAgeData>();
        for(int i = 1; i <= 100; i++) {
            FridgeAgeData fad = new FridgeAgeData(i);
            mfac.add(fad);
        }
        return mfac;
    }

    public static List<FridgeAgeData> getFridgeData(String yi) {
        List<FridgeAgeData> fd = new ArrayList<FridgeAgeData>();

        return fd;
    }



}
