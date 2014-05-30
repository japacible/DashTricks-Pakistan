package com.dashtricks.pakistan.app.Utilities;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

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
}