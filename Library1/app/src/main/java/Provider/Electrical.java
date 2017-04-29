package Provider;

import java.io.Serializable;

/**
 * Created by jiayuan on 2016/11/30.
 */
public class Electrical implements Serializable {
    private String style;
    private String price;
    private String address;
    private String time;
    public Electrical(String style,String price,String address,String time){
        this.style=style;
        this.price=price;
        this.address=address;
        this.time=time;
    }
    public String getStyle0(){
        return style;
    }
    public String getPrice(){
        return price;
    }
    public String getAddress(){
        return address;
    }
    public String getTime(){return time;}
}
