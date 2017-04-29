package Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import User.LibraryDbOpenHelper;

/**
 * Created by jiayuan on 2016/11/30.
 */
public class ElectricalDao {
    private LibraryDbOpenHelper helper;
    private SQLiteDatabase db;
    public ElectricalDao(Context context){
        helper=new LibraryDbOpenHelper(context);
    }
    public void addElectrical(Electrical electrical){
        db=helper.getWritableDatabase();
        db.execSQL("insert into t_Electrical(Style,Price,Address,Time) values(?,?,?,?)",new Object[]{electrical.getStyle0(), electrical.getPrice(),electrical.getAddress(),electrical.getTime()});
    }
    public ArrayList<Electrical> findElectrical(){
        ArrayList<Electrical> list=null;
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from t_Electrical",null);
        if(cursor!=null){
            list=new ArrayList<Electrical>();
            while(cursor.moveToNext()){
                String style=cursor.getString(cursor.getColumnIndex("Style"));
                String price=cursor.getString(cursor.getColumnIndex("Price"));
                String address=cursor.getString(cursor.getColumnIndex("Address"));
                String time=cursor.getString(cursor.getColumnIndex("Time"));
                list.add(new Electrical(style,price,address,time));
            }
        }
        return list;
    }
}
