package com.dashtricks.pakistan.app.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToDatabaseConverter extends SQLiteOpenHelper{
    Workbook w;
    String[] tablesAndFields;
    Map<String, List<ContentValues>> tableContents;

   /*
    * Open the spreadsheet, construct table creation strings, read in data and formulate insertions
    */
    public ExcelToDatabaseConverter(Context context, String name, File wbfile) {
        super(context, name, null, 1); // don't care about the last two fields
        tableContents = new HashMap<String, List<ContentValues>>();
        try {
            w = Workbook.getWorkbook(wbfile);
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        Sheet[] sheets = w.getSheets();
        tablesAndFields = new String[sheets.length];

        for(int i = 0; i < sheets.length; i++) {
            Sheet s = sheets[i];
            String table = s.getName();
            String entriesAndTypes = parseSheetTypes(s);
            String[] entries = getColumns(s);
            tablesAndFields[i] = entriesAndTypes;
            List<ContentValues> rows =  new ArrayList<ContentValues>(20);

            Log.d("Reading excel data", "at sheet " + table);

            for (int j = 1; j < s.getRows(); j++) {
                ContentValues cv = new ContentValues();

                Log.d("Reading excel data", "on line " + j);

                for(int k = 0; k < s.getColumns(); k++) {
                    CellType ct = s.getCell(j,k).getType();
                    Cell c = s.getCell(k,j);

                    Log.d("Reading excel data", c.getContents());

                    if(c.getContents().matches("\\d+")) { // I got 99 problems
                        cv.put(entries[k], Integer.parseInt(c.getContents()));
                    } else {
                        cv.put(entries[k], c.getContents());
                    }
                }
                rows.add(cv);
            }

            tableContents.put(table, rows);
        }
    }

    private String[] getColumns(Sheet s) {
        String[] cols = new String[s.getColumns()];

        for(int i = 0; i < s.getColumns(); i++) {
            cols[i] = s.getCell(i, 0).getContents();
        }

        return cols;
    }

    @Override
    /*
     * Given the database,
     * insert all that stuff made during the constructor
     * */
    public void onCreate(SQLiteDatabase db) {
        for(String s : tablesAndFields){
            db.execSQL(s);
        }
        for(String table : tableContents.keySet()) {
            for(ContentValues c : tableContents.get(table)) {
                db.insert(table, null, c);
            }
        }
    }

    /*
     * Given a sheet,
     * return a string of the format "n1 CLASS1, n2 CLASS2, n3 CLASS3"
     * used for constructing tables based on sheets
     * */
    private String parseSheetTypes(Sheet s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.getColumns(); i++){
            sb.append(s.getCell(i, 0).getContents());
            sb.append(" ");
            sb.append(parseCellType(s.getCell(i, 1)));
            sb.append(", ");
        }

        /* remove trailing comma and space */
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /*
     * Given a cell with some data,
     * return a string that describes the best fit SQLite storage class
     * */
    private String parseCellType(Cell cell) {
        CellType ct = cell.getType();

        if(ct == CellType.NUMBER) {
            String s = cell.getContents();
            try{
                Integer.parseInt(s);
                return "INTEGER";
            } catch (NumberFormatException nfe1) {
                try{
                    Double.parseDouble(s);
                    return "REAL";
                } catch (NumberFormatException nfe2){
                    return "TEXT";
                }
            }
        } else {
            return "TEXT";
        }
    }

    //    Currently, we don't change the database at all after reading stuff in
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}