package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.dashtricks.pakistan.app.General.TheApplication;
import com.dashtricks.pakistan.app.R;
import com.dashtricks.pakistan.app.Utilities.WebAppInterface;

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
        myWebView.addJavascriptInterface(
                new WebAppInterface(this, (TheApplication)getApplication()), "Android");
        myWebView.addJavascriptInterface(new JSInterface(this), "Fragment");
        myWebView.loadUrl("file:///android_asset/www/urgentNeed.html");


        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.urgentNeedFragmentContainer);

        if(fragment == null) {
            fragment = new OverallStatsFacilitiesFragment(); // TODO Change
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

    public class JSInterface {
        private Context mContext;

        /** Instantiate the interface and set the context */
        public JSInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        // Switch out the fragment
        public void callFragment(String barName) {

            // Instantiate urgent need fragment
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = fm.findFragmentById(R.id.urgentNeedFragmentContainer);

            UrgentNeedExpandableListFragment pFrag = new UrgentNeedExpandableListFragment();
            pFrag.setState(barName);
            fragment = pFrag;

            ft.replace(R.id.urgentNeedFragmentContainer, fragment);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
