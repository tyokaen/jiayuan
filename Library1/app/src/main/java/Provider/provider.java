package Provider;

import java.io.Serializable;

/**
 * Created by jiayuan on 2016/11/19.
 */
public class provider implements Serializable {
    private  String zhanghao;
    private  String password;
    private  String mailAdress;
    private byte[] image;
    public provider(String zhanghao,String password,String mailAdress,byte[] image){
        this.zhanghao=zhanghao;
        this.password=password;
        this.mailAdress=mailAdress;
        this.image=image;
    }
    public String getID(){
        return zhanghao;
    }
    public String getPassword1(){
        return password;
    }
    public String getMailAdress(){
        return mailAdress;
    }
    public byte[] getImage(){
        return image;
    }
}


