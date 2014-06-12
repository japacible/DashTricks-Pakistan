package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dashtricks.pakistan.app.General.TheApplication;
import com.dashtricks.pakistan.app.R;
import com.dashtricks.pakistan.app.Utilities.ExcelToDatabaseConverter;
import com.dashtricks.pakistan.app.Utilities.FileDialog;

import java.io.File;

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Import file to process
     */
    public void importColdChainData(View view) {
        File mPath = new File(String.valueOf(Environment.getExternalStorageDirectory()));
        FileDialog fileDialog = new FileDialog(this, mPath);
        final SettingsActivity sa = this;

        // We only want to deal with excel spreadsheets
        fileDialog.setFileEndsWith(".xls");

        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
            public void fileSelected(File file) {
                Log.d(getClass().getName(), "user selected file " + file.toString());
                TheApplication theApp = (TheApplication)getApplication();
                theApp.setExcelFile(file);
                theApp.possiblySlurp();
            }
        });

        fileDialog.showDialog();
    }

    /**
     * Placeholder for @InsaneFisherman
     * Automatic excel processing for in-app xls file
     */
    public void autoImportSpreadsheet(View view) {
        File file = new File("/sdcard/Download/Pakistan_Sample.xls");
        ExcelToDatabaseConverter e2db = new ExcelToDatabaseConverter(this, file);
    }

}
