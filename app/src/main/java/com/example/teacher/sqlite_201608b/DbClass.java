package com.example.teacher.sqlite_201608b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Teacher on 11/23/2018.
 */

public class DbClass extends SQLiteOpenHelper {
    private  static  final  String Db_Name="StudentInformation.db";

    public  static final String TName="Tblstudents";

    private static String CTable;

    public  static  final String ID="id";
    public  static  final String SN="SName";
    public  static  final String AD="SAddress";

    private SQLiteDatabase db;
    private Cursor cr; //for record fetch
    private ContentValues cv; //for record insert or update



    public DbClass(Context context)
    {
        super(context, Db_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CTable= "CREATE TABLE " + TName + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SN
                + " TEXT, " + AD + " TEXT)";
        sqLiteDatabase.execSQL(CTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TName);
        onCreate(sqLiteDatabase);

    }
    public  void AddStudent(String n,String a)
    {
        db=getWritableDatabase();
        cv = new ContentValues();
        cv.put(SN, n);
        cv.put(AD,a);
        db.insert(TName ,null,cv);
        db.close();

    }
    public void UpdateStudent(String n,String a,String r){
        db=getWritableDatabase();
        cv=new ContentValues();
        cv.put(SN,n);
        cv.put(AD,a);
        db.update(TName,cv,"ID=?",new String[]{r});
        db.close();

    }
    public void DeleteStudent(String r){

        db=getWritableDatabase();

        db.delete(TName,"ID=?",new String[]{r});
        db.close();

    }
    public Cursor ShowData(){

        db=getReadableDatabase();
        cr=db.rawQuery("SELECT * FROM "+TName,null);
        return cr;

    }

}
