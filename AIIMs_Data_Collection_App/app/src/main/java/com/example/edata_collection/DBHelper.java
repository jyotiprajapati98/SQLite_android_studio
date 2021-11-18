package com.example.edata_collection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AIIMs_data.sqlite";
    public static final String PATIENTS_TABLE_NAME = "patients";
    public static final String PATIENTS_COLUMN_ID = "id";

    public static final String PATIENTS_COLUMN_DATE = "date";
    public static final String PATIENTS_COLUMN_GENDER = "gender";
    public static final String PATIENTS_COLUMN_AGE = "age";
    public static final String PATIENTS_COLUMN_HEIGHT = "height";
    public static final String PATIENTS_COLUMN_WEIGHT= "weight";
    public static final String PATIENTS_COLUMN_FOODTYPE = "foodtype";
    public static final String PATIENTS_COLUMN_HEALTHSTATUS = "healthstatus";
    public static final String PATIENTS_COLUMN_GENETICDISORDERS = "genetic_disease";
    public static final String PATIENTS_COLUMN_ALLERGY= "allergy";
    public static final String PATIENTS_COLUMN_CASTE= "caste";
    public static final String PATIENTS_COLUMN_SUGAR= "sugar";
    public static final String PATIENTS_COLUMN_HBLEVEL= "hblevel";

    public static final String PATIENTS_COLUMN_FrontLEFTEYE = "FLE_image";
    public static final String PATIENTS_COLUMN_LEFTEYE = "left_eye";
    public static final String PATIENTS_COLUMN_RIGHTEYE = "right_eye";
    public static final String PATIENTS_COLUMN_NAILBED = "nail_bed";
    public static final String PATIENTS_COLUMN_CBCREPORT = "cbc_report";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table patients " +
                        "(id integer primary key, date text, gender text, age text, height text, " +
                        "weight text,foodtype text, healthstatus text, genetic_disease text, allergy text, " +
                        "caste text, sugar text, hblevel text, " +
                        "FLEimage BLOB, FREimage BLOB, LLEimage BLOB, LREimage BLOB, ULEimage BLOB, UREimage BLOB, Nailbedimage BLOB," +
                        "cbcP1 BLOB, cbcP2 BLOB, cbcP3 BLOB, cbcP4 BLOB, cbcP5 BLOB)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS patients");
        onCreate(db);
    }

    public boolean insertpatients(String date, String gender, String age, String height, String weight, String foodtype, String healthstatus, String genetic_disease, String allergy,
                                  String caste, String sugar, String hblevel, byte[] FLEimage,byte[] FREimage,byte[] LLEimage, byte[] LREimage,byte[] ULEimage, byte[] UREimage,
                                  byte[] Nailbedimage,
                                  byte[] cbcp1, byte[] cbcp2, byte[] cbcp3, byte[] cbcp4, byte[] cbcp5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("gender",gender);
        contentValues.put("age", age);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        contentValues.put("foodtype", foodtype);
        contentValues.put("healthstatus", healthstatus);
        contentValues.put("genetic_disease", genetic_disease);
        contentValues.put("allergy", allergy);
        contentValues.put("caste", caste);
        contentValues.put("sugar", sugar);
        contentValues.put("allergy", allergy);
        contentValues.put("hblevel", hblevel);
        contentValues.put("FLEimage",FLEimage);
        contentValues.put("FREimage",FREimage);
        contentValues.put("LLEimage",LLEimage);
        contentValues.put("LREimage",LREimage);
        contentValues.put("ULEimage",ULEimage);
        contentValues.put("UREimage",UREimage);
        contentValues.put("Nailbedimage",Nailbedimage);
        contentValues.put("cbcP1",cbcp1);
        contentValues.put("cbcP2",cbcp2);
        contentValues.put("cbcP3",cbcp3);
        contentValues.put("cbcP4",cbcp4);
        contentValues.put("cbcP5",cbcp5);
        /*
        contentValues.put("FREimage",FREimage);
        contentValues.put("LLEimage",LLEimage);
        contentValues.put("LREimage",LREimage);
        contentValues.put("LLEimage",ULEimage);
        contentValues.put("LREimage",UREimage);
        contentValues.put("Nailbedimage",Nailbedimage);
        contentValues.put("cbcP1",cbcp1);
        contentValues.put("cbcP2",cbcp2);
        contentValues.put("cbcP3",cbcp3);
        contentValues.put("cbcP4",cbcp4);
        contentValues.put("cbcP5",cbcp5);

         */
        db.insert("patients", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from patients where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PATIENTS_TABLE_NAME);
        return numRows;
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("patients",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from patients", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PATIENTS_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }


}