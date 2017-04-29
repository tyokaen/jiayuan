package Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import User.LibraryDbOpenHelper;

/**
 * Created by jiayuan on 2016/11/19.
 */

public class ProviderDao {
    private LibraryDbOpenHelper helper;
    private SQLiteDatabase db;
    public ProviderDao(Context context){
        helper=new LibraryDbOpenHelper(context);
    }
    public void add(provider p){
        db=helper.getWritableDatabase();
        db.execSQL("insert into t_Provider(Id,Password,Mailadress,Image) values(?,?,?,?)",new Object[]{p.getID(), p.getPassword1(),p.getMailAdress(),p.getImage()});
    }
    public ArrayList<provider> find(){
        ArrayList<provider> list=null;
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from t_Provider",null);
        if(cursor!=null){
            list=new ArrayList<provider>();
            while(cursor.moveToNext()){
                String zhanghao=cursor.getString(cursor.getColumnIndex("Id"));
                String password=cursor.getString(cursor.getColumnIndex("Password"));
                String mailadress=cursor.getString(cursor.getColumnIndex("Mailadress"));
                byte[] image=cursor.getBlob(cursor.getColumnIndex("image"));
                list.add(new provider(zhanghao,password,mailadress,image));
            }
        }
        return list;
    }
}
