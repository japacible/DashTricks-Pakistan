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

    /**
     * Return the json String so the webview can use it
     *
     * @param json String
     * @return String json object
     */
    @JavascriptInterface
    public String getJson(String json) {
        return ""; // TODO: Replace this with Dov's stuff
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
    public String getDefaultMapsJson() {
        return ModelDriver.requirementsAsJSON(null, null);
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
        return ModelDriver.requirementsAsJSON(fs, ips);
    }

    /**
     * Gets json for punjab bar graph
     */
    @JavascriptInterface
    public String getPunjabBarGraphJson() {
      return JsonObjects.PUNJAB; 
    }

    /**
     * Gets json for urgent need bar graph
     */
    @JavascriptInterface
    public JSONObject getUrgentBarChartCategories() {
        //String text = "[ {'Okay':1523, 'Needs Attention':605} ]";
        String text = "[ {'Okay', 'Needs Attention'} ]";

        JSONObject jsonar = null;

        try {
            jsonar = new JSONObject(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonar;
    }

    /**
     * Gets json
     */
    @JavascriptInterface
    public JSONObject getUrgentBarChartData() {
        String text = "[ {1523, 605} ]";

        JSONObject jsonar = null;

        try {
            jsonar = new JSONObject(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonar;
    }

    /**
     * Gets json for urgent need bar graph
     */
    @JavascriptInterface
    public String getUrgentBarChartCategoriesString() {
        return "[ {'Okay', 'Needs Attention'} ]";
    }

    /**
     * Gets json
     */
    @JavascriptInterface
    public String getUrgentBarChartDataString() {
        return "[ {1523, 605} ]";
    }

    /**
     * Gets json for fridge age bar graph
     */
    @JavascriptInterface
    public String getFridgeAgeBarGraphJson() {
      return JsonObjects.FRIDGE_AGE;
    }
}
