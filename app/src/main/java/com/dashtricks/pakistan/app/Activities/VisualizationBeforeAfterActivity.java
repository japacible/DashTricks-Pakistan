package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.dashtricks.pakistan.app.R;
import com.dashtricks.pakistan.app.Utilities.WebAppInterface;

public class VisualizationBeforeAfterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // This does not pass anything into the webview
        WebView leftWebView = (WebView) findViewById(R.id.webviewBefore);
        leftWebView.getSettings().setJavaScriptEnabled(true);
        leftWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        leftWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        leftWebView.loadUrl("file:///android_asset/www/exploremap.html");

        // This does not pass anything into the webview
        WebView rightWebView = (WebView) findViewById(R.id.webviewAfter);
        rightWebView.getSettings().setJavaScriptEnabled(true);
        rightWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        rightWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        rightWebView.loadUrl("file:///android_asset/www/exploremap.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visualization, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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