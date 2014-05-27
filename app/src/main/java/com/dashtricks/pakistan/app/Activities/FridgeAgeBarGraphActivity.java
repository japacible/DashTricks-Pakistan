package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.dashtricks.pakistan.app.R;

public class FridgeAgeBarGraphActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_age_bar_graph);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        myWebView.loadUrl("file:///android_asset/www/pakmap.html");

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.visualizationFragmentContainer);

        if(fragment == null) {
            fragment = new VisualizationMapFacilityFragment();
            fm.beginTransaction()
                    .add(R.id.visualizationFragmentContainer, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fridge_age_bar_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if(NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
