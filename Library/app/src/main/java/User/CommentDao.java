package User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by jiayuan on 2016/10/03.
 */
public class CommentDao {
    private LibraryDbOpenHelper libraryDbOpenHelper;
    private SQLiteDatabase db;
    public CommentDao(Context context){

        libraryDbOpenHelper=new LibraryDbOpenHelper(context);
    }
   public void add(Comment c){
       db=libraryDbOpenHelper.getWritableDatabase();
       db.execSQL("insert into t_Comment(comment) values(?)",new Object[]{c.getComment()});

   }
    public ArrayList<Comment> find(){
        ArrayList<Comment> list=null;
        db=libraryDbOpenHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select *from t_Comment",null);
        if(cursor!=null){
            list=new ArrayList<Comment>();
            while(cursor.moveToNext()){
                String string=cursor.getString(cursor.getColumnIndex("comment"));
                list.add(new Comment(string));
            }
        }
         return list;
    }
}
