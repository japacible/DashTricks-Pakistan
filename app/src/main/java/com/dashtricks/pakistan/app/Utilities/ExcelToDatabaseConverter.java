package com.dashtricks.pakistan.app.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToDatabaseConverter extends SQLiteOpenHelper{
    Workbook w;
    HashMap<String,List<String>> tableToFields; // maps from a table name to the fields it has

    public ExcelToDatabaseConverter(Context context, String name, File wbfile) {
        super(context, name, null, 1); // don't care about the last two fields
        tableToFields = new HashMap<String, List<String>>();
        try {
            w = Workbook.getWorkbook(wbfile);
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    /*
     * Given the database,
     * create one table per sheet in the data
     * */
    public void onCreate(SQLiteDatabase db) {
        Sheet[] sheets = w.getSheets();

        for(Sheet s : sheets) {
            String name = s.getName();
            String entriesAndTypes = parseSheetTypes(s);

            db.execSQL(String.format("CREATE TABLE %s (%s);", name, entriesAndTypes));
            List<String> eAndT = Arrays.asList(entriesAndTypes.split(" "));
            int i = 0;

//            Keep the table name and fields, strip out the type information
            for(Iterator<String> it = eAndT.iterator(); it.hasNext();) {
                it.next();
                if(i % 2 != 0) {
                    it.remove();
                }
                i++;
            }
            tableToFields.put(name, eAndT);
            System.err.println(String.format("CREATE TABLE %s (%s);", name, entriesAndTypes));
        }
    }

//    Each sheet becomes a table
//    The topmost row became the list of fields during db creation
//    Iterate over each cell of each sheet, insert it into the right place
    public void slurp() {
        SQLiteDatabase db = this.getReadableDatabase();
        Sheet[] sheets = w.getSheets();

        for (int k = 0; k < sheets.length; k++) {
            Sheet s = sheets[k];
            String table = s.getName();
            int offset = (k <= 3) ? 2 : 1; // ugly hack because of extra row in spreadsheet

            for (int i = offset; i < s.getRows(); i++) {
                ContentValues cv = new ContentValues();
                Iterator<String> it = tableToFields.get(table).iterator();

                for (int j = 0; j < s.getColumns(); j++) {
                    //                This is why we kept the field names for this table around
                    cv.put(it.next(), s.getCell(i,j).getContents());
                }

                db.insert(table, null, cv);
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
            int offset = (i <= 3) ? 2 : 1; // ugly hack because of extra row in spreadsheet
            sb.append(s.getCell(i, 0).getContents());
            sb.append(" ");
            sb.append(parseCellType(s.getCell(i, offset)));
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