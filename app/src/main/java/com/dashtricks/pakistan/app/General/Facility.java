package com.dashtricks.pakistan.app.General;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility implements Iterable<Refrigerator>{
    public static final int ADMIN_DEPTH = 1;
    private int weeksOfReserve;

    private String name;
    private int facId;
    private String subdis;
    private double currentCapacity; // no direct set. Updated by adding refrigerators
    private double requiredCapacity; // Should ONLY be set with a value returned by the Calculator
    private double amountShortBy;
    private double percentDeficient;
    private int population;
    private Set<PowerSource> powerSources;
    private Set<Refrigerator> refrigerators;
    private int adminRegion;
    private int weeksBetweenDelivery;

    // All these things
    public Facility(String name, int facId, Set<PowerSource> ps, int adminRegion, SQLiteDatabase db) {
	    this.name = name;
	    this.facId = facId;
	    this.powerSources = ps;
        this.adminRegion = adminRegion;

        int lookup = adminRegion;
        Cursor c = null; // fitting that I name this variable for the subsequent code style
        int i = 0;
        do{
            String selector = "SELECT * FROM AdminHierarchy WHERE NodeID=" + lookup;
            c = db.rawQuery(selector, null); // look up the next facility in the chain
            if(c.moveToFirst()) {
                lookup = Integer.parseInt(c.getString(4)); // parent's NodeID
            }
            i++;
        } while(i < ADMIN_DEPTH); // hacky do-while in order to force compilation

        subdis = c.getString(1); // correct level name

        refrigerators = populateRefrigerators(db);

        for(Refrigerator r : refrigerators) {
            if(r.isWorking()){
                currentCapacity += r.getVolume();
            }
        }
    //    this.weeksBetweenDelivery = weeksBetweenDelivery;
    //    this.weeksOfReserve = keepsReserve ? 4 : 0;
    }

// Read from the database
    private Set<Refrigerator> populateRefrigerators(SQLiteDatabase db) {
        Set<Refrigerator> refrigerators = new HashSet<Refrigerator>();
        String query = "SELECT * FROM Refrigerators WHERE FacilityID=" + facId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Refrigerator r = new Refrigerator();
                r.setUniqueId(Integer.parseInt(cursor.getString(0)));
                r.setAge(Integer.parseInt(cursor.getString(4)) - Refrigerator.CURRENT_YEAR);
                // Sum of +4 volume and -20 volume. Each refrigerator should have at most one nonzero of the two
                r.setVolume(Integer.parseInt(cursor.getString(14)) + Integer.parseInt(cursor.getString(15)));
                // Avoid using lookup table. 1 is a working refrigerator, 2 and 3 are not working
                r.setWorking(Integer.parseInt(cursor.getString(6)) == 1);
                r.setPs(PowerSource.ELECTRICITY); // needs two more layers of lookup, and this only
                                                  // matters for refrigerators we ourselves allocate
                refrigerators.add(r);
            } while (cursor.moveToNext());
        }
    return refrigerators;
    }

    public double getAmountShortBy() {
        return amountShortBy;
    }

    public double getPercentDeficient() {
        return percentDeficient;
    }

    public String getName() {
    return name;
    }
    
    public int getFacId() {
    return facId;
    }

    public boolean canUseSource(PowerSource p) {
    return powerSources.contains(p);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void addRefrigerator(Refrigerator refrigerator) {
        refrigerators.add(refrigerator);
        if(refrigerator.isWorking()){
            currentCapacity += refrigerator.getVolume();
        }
    }

    public int getPopulation() {
        return population;
    }

    // No setter method because this is calculated based on refrigerators
    public double getCurrentCapacity() {
        return currentCapacity;
    }

    // Done because Calculator imports Facility, and circular dependencies are ugly
    public void setRequiredCapacity(double rc){
        requiredCapacity = rc;
        amountShortBy = rc - currentCapacity;
        percentDeficient = (1 - currentCapacity/requiredCapacity) * 100;
    }

    public double getRequiredCapacity() {
    return requiredCapacity;
    }

    public String getSubdis() {
        return subdis;
    }

    @Override
    public Iterator<Refrigerator> iterator() {
        return refrigerators.iterator();
    }

    public int getWeeksBetweenDelivery() {
        return weeksBetweenDelivery;
    }

    public int getWeeksOfReserve() {
        return weeksOfReserve;
    }

    public int getAdminRegion() {
        return adminRegion;
    }
}
