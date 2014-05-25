package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.dashtricks.pakistan.app.R;

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
        myWebView.loadUrl("file:///android_asset/www/pakmap.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.visualization, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
