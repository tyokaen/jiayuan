package Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import User.LibraryDbOpenHelper;

/**
 * Created by jiayuan on 2017/04/16.
 */

public class Provider_Good_dao {
    private LibraryDbOpenHelper helper;
    private SQLiteDatabase db;
    public Provider_Good_dao(Context context){
        helper=new LibraryDbOpenHelper(context);
    }
    public void addPro_Goods(Provider_Goods provider_goods){
        db=helper.getWritableDatabase();
        db.execSQL("insert into  t_PROVIDER_GOODS (ID,Style,Price,Address) values(?,?,?,?)",new Object[]{provider_goods.get_P_ID(),provider_goods.get_P_Style0()
                ,provider_goods.get_P_Price(),provider_goods.get_P_Address()
        });
    }
    public ArrayList<Provider_Goods> findPro_Goods(String string){
        ArrayList<Provider_Goods> list=null;
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from t_PROVIDER_GOODS ",null);
        if(cursor!=null){
            list=new ArrayList<Provider_Goods>();
            while(cursor.moveToNext()){
                String ID=cursor.getString(cursor.getColumnIndex("ID"));
                String style=cursor.getString(cursor.getColumnIndex("Style"));
                String price=cursor.getString(cursor.getColumnIndex("Price"));
                String address=cursor.getString(cursor.getColumnIndex("Address"));
                list.add(new Provider_Goods(ID,style,price,address));
            }
        }
        return list;
    }
}
