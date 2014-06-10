package com.dashtricks.pakistan.app.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToDatabaseConverter extends SQLiteOpenHelper{
    private Workbook w;
    String[] tablesAndFields;
    Map<String, String[]> tableToTypes;
    private Map<String, String[]> tableToColumnNames;
    Map<String, String> tableToEntriesAndTypes;
    //Map<String, List<ContentValues>> tableContents;
    private boolean databasePopulated;
    private static final String databaseName = "icePak_databaseTESTER4";
    private static final String existanceTestString = "SELECT name FROM sqlite_master WHERE type='table' AND name='RefrigeratorCatalog'";

    public long getSpreadsheetSize() {
        return spreadsheetSize;
    }

    private long spreadsheetSize;

   /*
    * Open the spreadsheet, construct table creation strings, read in data and formulate insertions
    */
    public ExcelToDatabaseConverter(Context context, File wbfile) {
        super(context, databaseName, null, 1); // don't care about the last two fields
        //tableContents = new HashMap<String, List<ContentValues>>();
        tableToTypes = new HashMap<String, String[]>();
        setTableToColumnNames(new HashMap<String, String[]>());
        tableToEntriesAndTypes= new HashMap<String, String>();

        try {
            setW(Workbook.getWorkbook(wbfile));
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        //if people actually try to get data from a nonpopulated
        //database, onCreate gets called, which sets it to a more correct value
        databasePopulated = true;
        tablesAndFields = new String[getW().getSheets().length];
        spreadsheetSize = 0;

        for(Sheet s : getW().getSheets()){
            spreadsheetSize += s.getRows();
            String table = s.getName();
            tableToTypes.put(table, getColumnTypes(s));
            tableToEntriesAndTypes.put(table, parseSheetTypes(s));
            getTableToColumnNames().put(table, getColumns(s));
        }
    }

    public String[] getTablesAndFields() {
        return tablesAndFields;
    }

    public void setTablesAndFields(String[] tablesAndFields) {
        this.tablesAndFields = tablesAndFields;
    }

    public Map<String, String[]> getTableToTypes() {
        return tableToTypes;
    }

    public void setTableToTypes(Map<String, String[]> tableToTypes) {
        this.tableToTypes = tableToTypes;
    }

    public Map<String, String> getTableToEntriesAndTypes() {
        return tableToEntriesAndTypes;
    }

    public void setTableToEntriesAndTypes(Map<String, String> tableToEntriesAndTypes) {
        this.tableToEntriesAndTypes = tableToEntriesAndTypes;
    }

    public boolean isDatabasePopulated() {
        return databasePopulated;
    }

    public void setDatabasePopulated(boolean databasePopulated) {
        this.databasePopulated = databasePopulated;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static String getExistanceTestString() {
        return existanceTestString;
    }

    public synchronized boolean hasData() {
        return databasePopulated;
    }

    public synchronized void setHasData(boolean b){
        databasePopulated = b;
    }

    private String[] getColumns(Sheet s) {
        String[] cols = new String[s.getColumns()];

        for(int i = 0; i < s.getColumns(); i++) {
            cols[i] = s.getCell(i, 0).getContents();
        }

        return cols;
    }

    private String[] getColumnTypes(Sheet s) {
        String[] types = new String[s.getColumns()];

        for(int i = 0;i < s.getColumns(); i++) {
            types[i] = parseCellType(s.getCell(i,1));
        }

        return types;
    }

    @Override
    /*
     * Given the database,
     * insert all that stuff made during the constructor
     * */
    public void onCreate(SQLiteDatabase db) {
        setHasData(tablesExist(db));

        if(!tablesExist(db)) {
            for(Sheet s : getW().getSheets()){
                String table = s.getName();
                String entriesAndTypes = tableToEntriesAndTypes.get(table);
                db.execSQL("DROP TABLE IF EXISTS " + table); //safety
                String tableCreator = String.format("CREATE TABLE %s(%s)", table, entriesAndTypes);
                //tablesAndFields[i] = entriesAndTypes;
                db.execSQL(tableCreator);
                Log.d("Reading excel data", "creating table thusly:\n" + tableCreator);
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
     * Currently doesn't support integers, because excel is stupid about
     * truncating trailing .0, which makes type inference a hassle
     * */
    private String parseCellType(Cell cell) {
        CellType ct = cell.getType();

        String s = cell.getContents();
        try{
            Double.parseDouble(s);
            return "REAL";
        } catch (NumberFormatException nfe2){
            return "TEXT";
        }
    }

    //    Currently, we don't change the database at all after reading stuff in
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    // Tests whether the database already exists
    private boolean tablesExist(SQLiteDatabase db){
        return db.rawQuery(existanceTestString, null).getCount() != 0;
    }

    public Workbook getW() {
        return w;
    }

    public void setW(Workbook w) {
        this.w = w;
    }

    public Map<String, String[]> getTableToColumnNames() {
        return tableToColumnNames;
    }

    public void setTableToColumnNames(Map<String, String[]> tableToColumnNames) {
        this.tableToColumnNames = tableToColumnNames;
    }
}