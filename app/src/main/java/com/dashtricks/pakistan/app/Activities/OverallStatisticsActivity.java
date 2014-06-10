package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.OverallStatistics;
import com.dashtricks.pakistan.app.R;

public class OverallStatisticsActivity extends Activity {
    private TextView totalFacilitiesCount;
    private TextView attentionFacilitiesCount;
    private TextView totalPopulation;
    private TextView coveredPopulation;
    private TextView percentageCapacity;
    private TextView totalRequiredCapacity;
    private TextView totalCoveredCapacity;

    private OverallStatistics os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_statistics);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        os = new OverallStatistics();
        showStatistics();
    }

    private void showStatistics() {
        totalFacilitiesCount = (TextView) findViewById(R.id.overallStatsTotalFacilities);
        totalFacilitiesCount.setText(String.valueOf(os.getTotalFacilitiesCount()));
        
        attentionFacilitiesCount = (TextView) findViewById(R.id.overallStatsAttentionNeedFacilities);
        attentionFacilitiesCount.setText(String.valueOf(os.getAttentionFacilitiesCount()));

        totalPopulation = (TextView) findViewById(R.id.overallStatsTotalPopulation);
        totalPopulation.setText(String.valueOf(os.getTotalPopulation()));

        coveredPopulation = (TextView) findViewById(R.id.overallStatsCoveredPopulation);
        coveredPopulation.setText(String.valueOf(os.getCoveredPopulation()));

        percentageCapacity = (TextView) findViewById(R.id.percentageCapacity);
        percentageCapacity.setText(String.valueOf(os.getPercentageCapacity()));

        totalRequiredCapacity = (TextView) findViewById(R.id.totalRequiredCapacitySize);
        totalRequiredCapacity.setText(String.valueOf(os.getTotalRequiredCapacity()));

        totalCoveredCapacity = (TextView) findViewById(R.id.totalCoveredCapacitySize);
        totalCoveredCapacity.setText(String.valueOf(os.getTotalCoveredCapacity()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overall_statistics, menu);
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
