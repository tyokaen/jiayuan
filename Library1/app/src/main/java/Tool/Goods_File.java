package Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Provider.Electrical;
import Provider.Furniture;
import Provider.Provider_Goods;

/**
 * Created by jiayuan on 2017/04/15.
 */

public class Goods_File {
    private static Goods_File goods_file=null;
    private  Goods_File(){};
    public  static  synchronized  Goods_File getGoods_file(){
        if(goods_file==null){
            goods_file=new Goods_File();
        }
        return goods_file;
    }
    public  void saveToCard(ArrayList<?> arrayList,File file){
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file,false));
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Furniture>  readObject(File file){
        ArrayList<Furniture>  furnitureArrayList=null;
        if(file.exists()) {

        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
               furnitureArrayList=(ArrayList<Furniture>) objectInputStream.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





        return  furnitureArrayList;
    }
    public ArrayList<Electrical> readObject1(File file){
        ArrayList<Electrical> ElectricalList=null;
        if(file.exists()) {

        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                ElectricalList=(ArrayList<Electrical>) objectInputStream.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ElectricalList;
    }
    public ArrayList<Provider_Goods>  readObject2(File file){
        ArrayList<Provider_Goods> provider_goodsArrayList=null;
        if(file.exists()) {

        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                provider_goodsArrayList=(ArrayList<Provider_Goods>) objectInputStream.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  provider_goodsArrayList;
    }

}
