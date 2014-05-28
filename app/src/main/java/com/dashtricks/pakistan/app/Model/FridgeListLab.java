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

    private void FridgeListSetup() {
        for(int i = 1; i <= 100; i++) {
            FridgeAgeData fad = new FridgeAgeData(i);
            mFacilities.add(fad);
        }
    }

}
