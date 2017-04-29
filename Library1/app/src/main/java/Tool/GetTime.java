package Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiayuan on 2017/04/22.
 */

public class GetTime {
    private static  GetTime getTime=new GetTime();
    private  GetTime(){};
    public  static  GetTime getTimeInstance(){
        return getTime;

    }
    public String getTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
       String string= simpleDateFormat.format(date);
        return string;


    }

}
