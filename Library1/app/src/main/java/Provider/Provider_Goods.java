package Provider;

/**
 * Created by jiayuan on 2017/04/16.
 */

public class Provider_Goods {
    private String ID;
    private String style;
    private String price;
    private String address;
    public Provider_Goods(String ID, String style,String price,String address){
        this.ID=ID;
        this.style=style;
        this.price=price;
        this.address=address;
    }
    public String get_P_ID(){
        return ID;
    }
    public String get_P_Style0(){
        return style;
    }
    public String get_P_Price(){
        return price;
    }
    public String get_P_Address(){
        return address;
    }
}
