package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dashtricks.pakistan.app.R;

public class ExplorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exploration, menu);
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

    /**
     * Go to visualization flow
     */
    public void visualizeCurrentData(View view) {
        Intent i = new Intent(this, VisualizationActivity.class);
        startActivity(i);
    }

    /**
     * View facilities that are in dire need of fridges
     */
    public void viewDangerFacilities(View view) {
        Intent i = new Intent(this, FacilitiesListActivity.class);
        startActivity(i);
    }

    /**
     * Open facilities list view
     */
    public void viewFacilitiesList(View view) {
        Intent i = new Intent(this, FacilityListActivity.class);
        startActivity(i);
    }

    /**
     * Open overall statistics activity
     */
    public void viewOverallStatistics(View view) {
        Intent i = new Intent(this, OverallStatisticsActivity.class);
        startActivity(i);
    }

    /**
     * Open fridge age bar graph activity
     */
    public void viewFridgeAgeBarGraph(View view) {
        Intent i = new Intent(this, FridgeAgeBarGraphActivity.class);
        startActivity(i);
    }

    /**
     * Open view urgent need bar graph activity
     */
    public void viewUrgentNeedBarGraph(View view) {
        Intent i = new Intent(this, UrgentNeedBarGraphActivity.class);
        startActivity(i);
    }

    /**
     * Open Punjab bar graph activity
     */
    public void viewPunjabBarGraph(View view) {
        Intent i = new Intent(this, PunjabBarGraphActivity.class);
        startActivity(i);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_exploration, container, false);
            return rootView;
        }
    }
}
