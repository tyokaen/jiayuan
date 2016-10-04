package User;

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
    static final String TABLE_PERSON="create table t_Person(Zhanghao text primary key,Password text)";
    static final String TABLE_COMMENT="create table t_Comment(comment text)";
    public LibraryDbOpenHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase database){
        database.execSQL(TABLE_PERSON);
        database.execSQL(TABLE_COMMENT);
//执行SQL语句.

    }
      public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

          //db.execSQL("DROP TABLE iF EXISTS"+Login.TBNAME);
      }

}
