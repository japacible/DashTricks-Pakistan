package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import com.dashtricks.pakistan.app.General.TheApplication;
import com.dashtricks.pakistan.app.R;
import com.dashtricks.pakistan.app.Utilities.WebAppInterface;

public class VisualizationBeforeAfterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization_before_after);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView leftWebView = (WebView) findViewById(R.id.webviewBefore);
        leftWebView.getSettings().setJavaScriptEnabled(true);
        leftWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        leftWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        leftWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        leftWebView.addJavascriptInterface(
                new WebAppInterface(this, (TheApplication) getApplication()), "Android");
        leftWebView.loadUrl("file:///android_asset/www/beforeSimulate.html");

        WebView rightWebView = (WebView) findViewById(R.id.webviewAfter);
        rightWebView.getSettings().setJavaScriptEnabled(true);
        rightWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        rightWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        rightWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        rightWebView.addJavascriptInterface(
                new WebAppInterface(this, (TheApplication) getApplication()), "Android");
        rightWebView.loadUrl("file:///android_asset/www/afterSimulate.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visualization_before_after, menu);
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
                Intent i = new Intent(this, VisualizationActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}