package com.example.orderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //Khai báo database
    public static final String DBNAME = "Order_Food.db";

    public DBHelper(Context context) {
        super(context, "Order_Food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //Tạo bảng
        MyDB.execSQL("CREATE TABLE dbo_users(dbo_user_username TEXT, dbo_user_phonenumber TEXT PRIMARY KEY, dbo_user_password)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //Xóa bảng
        MyDB.execSQL("DROP TABLE IF EXISTS dbo_users");
    }

    public Boolean Insert_db_users(String username, String phone, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dbo_user_username", username);
        contentValues.put("dbo_user_phonenumber", phone);
        contentValues.put("dbo_user_password", password);
        long result = MyDB.insert("dbo_users", null, contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean Check_Phonenumber(String Phonenumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cusor = MyDB.rawQuery("SELECT * FROM dbo_users Where dbo_user_phonenumber = ?", new String[] {Phonenumber});
        if(cusor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean Check_Account(String Phone, String Password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cusor = MyDB.rawQuery("SELECT * FROM dbo_users Where dbo_user_phonenumber = ? AND dbo_user_password = ? ", new String[] {Phone, Password});
        if(cusor.getCount()>0){
            return true;
        }
        else
        {
            return false;
        }
    }

}
