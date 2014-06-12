package com.dashtricks.pakistan.app.General;

import android.app.Application;

import com.dashtricks.pakistan.app.Utilities.DataAccessor;
import com.dashtricks.pakistan.app.Utilities.DatabaseSlurper;
import com.dashtricks.pakistan.app.Utilities.ExcelToDatabaseConverter;

import java.io.File;

public class TheApplication extends Application {
    private ExcelToDatabaseConverter ecc;
    private File excelFile;
    private DataAccessor da;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public ExcelToDatabaseConverter getEcc() {
        return ecc;
    }

    public File getExcelFile() {
        return excelFile;
    }

    public DataAccessor getDa() {
        return da;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
        ecc = new ExcelToDatabaseConverter(this, excelFile);
    }

    public void setDa(DataAccessor da) {
        this.da = da;
    }

    public void possiblySlurp() {
        new DatabaseSlurper().execute(ecc);
    }
}
