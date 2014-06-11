package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.dashtricks.pakistan.app.R;
import com.dashtricks.pakistan.app.Utilities.WebAppInterface;

public class VisualizationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.loadUrl("file:///android_asset/www/simulatemap.html");

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.visualizationFragmentContainer);

        if(fragment == null) {
            fragment = new OverallStatsFacilitiesFragment();
            fm.beginTransaction()
                    .add(R.id.visualizationFragmentContainer, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visualization, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            case R.id.action_next:
                Intent i = new Intent(this, VisualizationBeforeAfterActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
