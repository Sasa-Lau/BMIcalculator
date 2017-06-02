package com.example.windows8.bmi;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lau Jun Ning on 31-May-17.
 */

public class BMIdb extends SQLiteOpenHelper {

    public static final String dbName = "dbBMI";
    public static final String tblName = "BMI";
    public static final String colBMIid = "bmi_id";
    public static final String colWeight = "bmi_weight";
    public static final String colHeight = "bmi_height";
    public static final String colBMIValue = "bmi_value";
    Cursor resultset;

    public BMIdb(Context context){
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS BMI(bmi_id VARCHAR, bmi_weight VARCHAR, bmi_height VARCHAR, bmi_value VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF NOT EXISTS BMI");
        onCreate(db);
    }

    public int fnTotalRow()
    {
        int intRow;
        SQLiteDatabase db = this.getReadableDatabase();
        intRow = (int) DatabaseUtils.queryNumEntries(db,tblName);

        return intRow;
    }

    public void fnExecuteSql(String strSql, Context appContext)
    {
//        try{
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(strSql);
//        }catch ( Exception e)
//        {
//            Log.d("unable to run query","error!");
//        }
    }

    public Cursor fnGetAllData(){

        SQLiteDatabase db = this.getReadableDatabase();
        resultset = db.rawQuery("SELECT bmi_value FROM BMI",null);
        return resultset;
    }
}