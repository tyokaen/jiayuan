package User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by jiayuan on 2016/09/24.
 */
public class PersonDao {
   private LibraryDbOpenHelper helper;
    private SQLiteDatabase db;
   public PersonDao(Context context){
        helper=new LibraryDbOpenHelper(context);
    }
    public void add(Person p){
        db=helper.getWritableDatabase();
        db.execSQL("insert into t_Person(Zhanghao,Password) values(?,?)",new Object[]{p.getZhanghao(), p.getPassword()});
    }
    public ArrayList<Person> find(){
        ArrayList<Person> list=null;
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from t_Person",null);
        if(cursor!=null){
            list=new ArrayList<Person>();
            while(cursor.moveToNext()){
                String zhanghao=cursor.getString(cursor.getColumnIndex("Zhanghao"));
                String password=cursor.getString(cursor.getColumnIndex("Password"));
                list.add(new Person(zhanghao,password));
            }
        }
              return list;
    }

}
