package Provider;

import java.io.Serializable;

/**
 * Created by jiayuan on 2017/04/15.
 */

public class Furniture implements Serializable {
    private String style;
    private String price;
    private String address;
    private String time;
    public Furniture(String style,String price,String address,String time){
        this.style=style;
        this.price=price;
        this.address=address;
        this.time=time;
    }
    public String get_F_Style0(){
        return style;
    }
    public String get_F_Price(){
        return price;
    }
    public String get_F_Address(){
        return address;
    }
    public String get_F_Time(){
        return time;
    }
}

