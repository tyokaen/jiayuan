package Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import User.LibraryDbOpenHelper;

/**
 * Created by jiayuan on 2017/04/15.
 */

public class FurnitureDao {
    private LibraryDbOpenHelper helper;
    private SQLiteDatabase db;
    public FurnitureDao(Context context){
        helper=new LibraryDbOpenHelper(context);
    }
    public void addFurniture(Furniture furniture){
        db=helper.getWritableDatabase();
        db.execSQL("insert into t_Furniture(Style,Price,Address,Time) values(?,?,?,?)",new Object[]{furniture.get_F_Style0(),
                furniture.get_F_Price(),furniture.get_F_Address(),furniture.get_F_Time()});
    }
    public ArrayList<Furniture> findFurniture(){
        ArrayList<Furniture> list=null;
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from t_Furniture",null);
        if(cursor!=null){
            list=new ArrayList<Furniture>();
            while(cursor.moveToNext()){
                String style=cursor.getString(cursor.getColumnIndex("Style"));
                String price=cursor.getString(cursor.getColumnIndex("Price"));
                String address=cursor.getString(cursor.getColumnIndex("Address"));
                String time=cursor.getString(cursor.getColumnIndex("Time"));
                list.add(new Furniture(style,price,address,time));
            }
        }
        return list;
    }
}
