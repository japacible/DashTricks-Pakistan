package com.dashtricks.pakistan.app.Utilities;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.Model.ModelDriver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by japacible on 5/29/14.
 */
public class WebAppInterface {
    private Context mContext;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * Return the json String for maps based on default
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getSimulateMapDataString() {
        return ModelDriver.requirementsAsJSON(null, null); // default
    }

    /**
     * Return the json String for maps based on default
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getExploreMapDataString() {
        return JsonObjects.EXPLORE_MAP_DATA;
    }

    /**
     * Return the json String for maps based on parameters passed in
     *
     * @param fs Facilities
     * @param ips Iterable<ImmunizationPlan>
     * @return String json object
     */
    @JavascriptInterface
    public String getCustomMapsJson(Facilities fs, Iterable<ImmunizationPlan> ips) {
        return ModelDriver.requirementsAsJSON(fs, ips); // pass in data
    }

    /**
     * Gets json for punjab bar graph
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getPunjabBarChartDataString() {

        return JsonObjects.PUNJAB_DATA;
    }

    /**
     * Gets json for punjab bar graph
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getUrgentPunjabBarChartDistrictsString() {
        return JsonObjects.PUNJAB_DISTRICTS;
    }

    /**
     * Gets json for urgent need bar graph x axis
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getUrgentBarChartCategoriesString() {
        return JsonObjects.URGENT_NEED_CATEGORIES;
    }

    /**
     * Gets json for urgent need bar graph y axis
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getUrgentBarChartDataString() {
        return JsonObjects.URGENT_NEED_DATA;
    }

    /**
     * Gets json for fridge age bar graph
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getFridgeAgeBarChartDataString() {
      return JsonObjects.FRIDGE_AGE_DATA;
    }

    /**
     * Gets json for fridge age bar graph
     *
     * @return String json object
     */
    @JavascriptInterface
    public String getFridgeAgeBarChartCategoriesString() {
        return JsonObjects.FRIDGE_AGE_CATEGORIES;
    }
}
