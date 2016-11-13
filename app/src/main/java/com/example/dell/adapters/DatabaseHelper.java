package com.example.dell.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 10/24/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String db="foodApp";
    private static final String table="cart";

    public DatabaseHelper(Context context) {
        super(context,db,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,QTY INTEGER,PRICE INTEGER)");
        db.close();
    }
    public void onCreate() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("create table IF NOT EXISTS "+table+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,QTY INTEGER,PRICE INTEGER)");
    }
    public void onUpdated(String input1,String input2) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATA",input1);
        db.update(table, cv, "ID='"+input2+"'", null);
    }

    public Cursor onView(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table+" order by ID desc",null);
        return res;
    }
    public void onDelete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.rawQuery("delete from "+table+" where ID='"+id+"'",null);
        db.execSQL("delete from "+table+" where ID='"+id+"'");
        //res1.close();
    }
    public long onInsert(String itemName,String qty,String price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put("NAME",itemName);
        contentvalue.put("QTY",Integer.parseInt(qty));
        contentvalue.put("PRICE",Integer.parseInt(price));
        long result=db.insert(table,null,contentvalue);
      //  if(result==-1)
        //    return false;
        //else
        db.close();
            return result;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table);
        onCreate(db);
       
    }
    public void onAppStop() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("drop table if exists "+table);
        db.close();
    }

}

