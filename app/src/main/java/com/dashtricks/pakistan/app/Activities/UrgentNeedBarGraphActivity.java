package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dashtricks.pakistan.app.R;

public class UrgentNeedBarGraphActivity extends Activity
        implements UrgentNeedBarGraphFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_need_bar_graph);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        myWebView.loadUrl("file:///android_asset/www/urgentNeed.html");
        myWebView.setWebViewClient(new MyWebViewClient());

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.urgentNeedFragmentContainer);

        if(fragment == null) {
            fragment = new PunjabExpandableFacilityListFragment(); // TODO Change
            fm.beginTransaction()
                    .add(R.id.urgentNeedFragmentContainer, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.urgent_need_bar_graph, menu);
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

    @Override
    public void onFragmentInteraction(String id) {

    }


    private class MyWebViewClient extends WebViewClient {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url != null) {
          Log.d("hi", "yes url");
          // create bundle of parameters to pass in
          Bundle bundle = new Bundle();
          bundle.putString("clicked", url);

          // switch out fragment
          FragmentManager fm = getFragmentManager();
          FragmentTransaction ft = fm.beginTransaction();
 
          Fragment fragment = fm.findFragmentById(R.id.urgentNeedFragmentContainer);
          fragment.setArguments(bundle);

          if (fragment == null) {
            fragment = new PunjabExpandableFacilityListFragment();
            ft.replace(R.id.urgentNeedFragmentContainer, fragment);
            ft.commit();
          }
        } else {
            Log.d("hi", "nope");
        }
          return true;
      }
    }
}
