package User;

/**
 * Created by jiayuan on 2016/09/24.
 */
public class Person {
    public String zhanghao;
    public String password;
   public Person(String zhanghao,String password){
        this.zhanghao=zhanghao;
        this.password=password;
    }
    public String getZhanghao(){
        return zhanghao;
    }
    public String getPassword(){
        return password;
    }
}
