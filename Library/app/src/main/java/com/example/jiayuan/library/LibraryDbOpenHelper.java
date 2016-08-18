package com.example.jiayuan.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiayuan on 2016/07/09.
 */
public class LibraryDbOpenHelper extends SQLiteOpenHelper {
    private static final String TAG="LibraryDbOpenHelper";
    static final String DATABASE_NAME="Library.db";
    static final int DATABASE_VERSION=1;
    public LibraryDbOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){

        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase database){
        database.execSQL("create table Logintable(Zhanghao text primary key autoincrement,Password text);");
//执行SQL语句

    }
      public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

          //db.execSQL("DROP TABLE iF EXISTS"+Login.TBNAME);
      }

}
