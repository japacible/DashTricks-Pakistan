package com.dashtricks.pakistan.app.Utilities;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.General.TheApplication;
import com.dashtricks.pakistan.app.Model.ModelDriver;

/**
 * Created by japacible on 5/29/14.
 */
public class WebAppInterface {
    private Context mContext;
    private TheApplication app;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c, TheApplication application) {
        mContext = c;
        app = application;
    }

    /**
     * Return the json String so the webview can use it
     *
     * @param json String
     * @return String json object
     */
    @JavascriptInterface
    public String getJson(String json) {
        return "";
    }

    @JavascriptInterface
    public String facilitiesToJson(){
        return app.getDa().facilitiesToJson();
    }

    @JavascriptInterface
    public String getDistrictHeatNumberAsJson(){
        return app.getDa().getDistrictHeatNumberAsJson();
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
    public String getCustomMapsJson(Facilities fs, Iterable<ImmunizationPlan> ips) {
        return ModelDriver.requirementsAsJSON(fs, ips);
    }

    /**
     * Gets json for punjab bar graph
     */
    public String getPunjabBarGraphJson() {
      return JsonObjects.PUNJAB; 
    }

    /**
     * Gets json for urgent need bar graph
     */
    public String getUrgentNeedBarGraphJson() {
      return JsonObjects.URGENT_NEED;
    }

    /**
     * Gets json for fridge age bar graph
     */
    public String getFridgeAgeBarGraphJson() {
      return JsonObjects.FRIDGE_AGE;
    }

}
