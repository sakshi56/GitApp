package com.example.myapplication;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DBname="Mydb";
    public static final int DBversion=5;
    public static final String TABLE_NAME = "Adminm";
//    public static final String COLUMN_ID = "Admin";
    public static final String name= "name";
    public static final String gmail = "gmail";
    public static final String password= "password";
    public static final String contact= "contact";
    public static final String date="date";
    private Context mContext;


    public DatabaseHelper(Context context){
        super(context,DBname,null,DBversion);
    mContext=context;
    }
@Override
public void onCreate(SQLiteDatabase db){
   db.execSQL("create table " + TABLE_NAME + " ( " + name + " VARCHAR, " + gmail + " VARCHAR, " + password + " VARCHAR, " + contact + " VARCHAR, " + date + " VARCHAR);");

//    String adminTable ="Create TABLE Adminm( name VARCHAR, gmail VARCHAR, password VARCHAR,contact VARCHAR)";
//db.execSQL(adminTable);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
