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
            FridgeAgeData fad = new FridgeAgeData(i, 4);
            mfac.add(fad);
        }
        return mfac;
    }

    public static List<FridgeAgeData> getFridgeData(String yi) {
        List<FridgeAgeData> fd = new ArrayList<FridgeAgeData>();

        if(yi.equals("0-4")) {
            for(int i = 1; i <= 24; i++) {
                FridgeAgeData fad = new FridgeAgeData(i, 4);
                fd.add(fad);
            }
        }

        if(yi.equals("4-8")) {
            for(int i = 1; i <= 45; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*2, 8);
                fd.add(fad);
            }
        }

        if(yi.equals("8-12")) {
            for(int i = 1; i <= 50; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*3, 12);
                fd.add(fad);
            }
        }

        if(yi.equals("12-16")) {
            for(int i = 1; i <= 23; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*4, 16);
                fd.add(fad);
            }
        }

        if(yi.equals("16-20")) {
            for(int i = 1; i <= 15; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*5, 20);
                fd.add(fad);
            }
        }

        if(yi.equals("20-24")) {
            for(int i = 1; i <= 33; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*6, 24);
                fd.add(fad);
            }
        }

        if(yi.equals("24-28")) {
            for(int i = 1; i <= 29; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*7, 28);
                fd.add(fad);
            }
        }

        if(yi.equals("28-32")) {
            for(int i = 1; i <= 14; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*8, 32);
                fd.add(fad);
            }
        }

        if(yi.equals("32-36")) {
            for(int i = 1; i <= 37; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*9, 36);
                fd.add(fad);
            }
        }

        if(yi.equals("36-40")) {
            for(int i = 1; i <= 21; i++) {
                FridgeAgeData fad = new FridgeAgeData(i*10, 40);
                fd.add(fad);
            }
        }

        return fd;
    }




}
