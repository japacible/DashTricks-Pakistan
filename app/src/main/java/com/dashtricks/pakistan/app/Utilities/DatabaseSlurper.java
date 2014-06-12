package com.dashtricks.pakistan.app.Utilities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import jxl.Sheet;

/**
 * Created by dov on 6/8/14.
 */
public class DatabaseSlurper extends AsyncTask<ExcelToDatabaseConverter, Long, Void> {
    private long progress;
    ExcelToDatabaseConverter privE;

    @Override
    protected Void doInBackground(ExcelToDatabaseConverter... excelToDatabaseConverters) {
        privE = excelToDatabaseConverters[0];
        slurp(privE);
        return null;
    }

    protected void onProgressUpdate(Long...update){
        update[0] = progress / privE.getSpreadsheetSize();
    }

    // Actually read data in from db
    // May get lifted somewhere else
    public void slurp(ExcelToDatabaseConverter e) {
        e.setHasData(false); //poor man's cv, basically busy wait
        progress = 0;

        SQLiteDatabase db = e.getWritableDatabase();
        for(Sheet s : e.getW().getSheets()){
            String table = s.getName();
            Log.d("EXCEL2DB", "at on table " + table);
            db.delete(table, null, null); // remove any existing data
            for(int i = 1; i < s.getRows(); i++) {
                Log.d("EXCEL2DB", "on row " + i);
                ContentValues cv = new ContentValues();
                for(int j = 0; j < s.getColumns(); j++){
                    Log.d("EXCEL2DB", "on column " + j +  " which is " + e.getTableToColumnNames().get(table)[j]);
                    String type = e.tableToTypes.get(table)[j];
                    if (type.equals("REAL")){
                        cv.put(e.getTableToColumnNames().get(table)[j], Double.parseDouble(s.getCell(j,i).getContents()));
                    } else {
                        cv.put(e.getTableToColumnNames().get(table)[j], s.getCell(j,i).getContents());
                    }
                }
                db.insert(table, null, cv);
                progress++;
            }
        }
        e.setHasData(true);

    }
}
