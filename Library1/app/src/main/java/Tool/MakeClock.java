package Tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * Created by jiayuan on 2017/03/14.
 */

public class MakeClock {
    ImageView imageView;
    int degree=0;
    float mLength=250*0.7f;
    Handler handler=new Handler();
    Paint paint=new Paint();
    private int  SecondDegree;
    Calendar calendar = java.util.Calendar.getInstance();
    private int hour=calendar.get(java.util.Calendar.HOUR);
    private int minute=calendar.get(java.util.Calendar.MINUTE);
    private int second=calendar.get(Calendar.SECOND);

    public Bitmap doDrawClock(int add) {
        Bitmap bitmap = doDrawCirlce();
        Canvas canvas=new Canvas(bitmap);
        Paint paint=new Paint();
        doHour(canvas,paint);
        doMinute(canvas,paint,add);
        doSecond(canvas,paint,add);
        return bitmap;
    }
    private void doHour(  Canvas canvas,Paint paint){
        paint.setStrokeWidth(10);
        canvas.save();
        canvas.rotate(hour*30+30*(minute/60f),255,255);
        canvas.drawLine(255,255,255,500-255-mLength,paint);
        canvas.restore();
    }
    private void doMinute(Canvas canvas,Paint paint,int add){
        paint.setStrokeWidth(4);
        canvas.save();
        canvas.rotate(minute * 6, 255, 255);
        canvas.drawLine(255, 255, 255, 500 - 255 - 1.1f * mLength, paint);
        canvas.restore();
        if(add%360==0&&add!=0) {
            canvas.save();
            canvas.rotate(minute * 6+6*(add/360), 255, 255);
            minute=minute+(add/360);
            canvas.drawLine(255, 255, 255, 500 - 255 - 1.1f * mLength, paint);
            canvas.restore();
        }
    }
    private void doSecond(Canvas canvas,Paint paint,int add){
        paint.setStrokeWidth(2);
        canvas.save();
        SecondDegree=second*6;
        canvas.rotate(SecondDegree+add,255,255);
        canvas.drawLine(255,255,255,500-255-1.15f*mLength,paint);
        canvas.restore();
    }
    private Bitmap doDrawCirlce() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#333333"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        canvas.drawCircle(255, 255, 250, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(255, 255, 10, paint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(255, 5, 255, 24, paint);
            canvas.rotate(30, 255, 255);
        }
        return bitmap;
    }
}
