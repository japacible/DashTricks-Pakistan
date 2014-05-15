package com.dashtricks.pakistan.app.Activities;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

import com.dashtricks.pakistan.app.R;

import java.io.File;

public class StartActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button importButton = (Button) findViewById(R.id.importBtn);
        Button vizButton = (Button) findViewById(R.id.vizBtn);

        // Show either import or visualization buttons based on whether or not app has run before
        // TODO: Change condition from hasRunBefore to hasImportedData
        SharedPreferences runCheck = getSharedPreferences("hasRunBefore", 0);
        Boolean hasRun = runCheck.getBoolean("hasRun", false);
        if (!hasRun) {
            SharedPreferences settings = getSharedPreferences("hasRunBefore", 0);
            SharedPreferences.Editor edit = settings.edit();
            edit.putBoolean("hasRun", true);
            edit.commit();

            vizButton.setVisibility(View.GONE);
            importButton.setVisibility(View.VISIBLE);
        }
        else {
            vizButton.setVisibility(View.VISIBLE);
            importButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Import file
     */
    public void importColdChainData(View view) {
        // TODO: call afilechooser
    }

    /**
     * Go to visualization flow
     */
    public void generateVisualization(View view) {
        Intent i = new Intent(this, ScenarioCreationActivity.class);
        startActivity(i);
    }

    /**
     * Open settings view
     */
    public void viewSettings(View view) {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}
