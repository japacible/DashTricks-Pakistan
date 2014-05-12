package com.dashtricks.pakistan.app.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToDatabaseConverter extends SQLiteOpenHelper{
	Workbook w;
	
	public ExcelToDatabaseConverter(Context context, String name,
			CursorFactory factory, int version, String wbname) {
		super(context, name, factory, version);
		try {
			w = Workbook.getWorkbook(new File(wbname));
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
			
			db.execSQL(String.format("CREATE TABLE %s (%s)", name, entriesAndTypes));
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


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}
