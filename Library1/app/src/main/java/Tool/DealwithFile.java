package Tool;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jiayuan on 2017/02/08.
 */
public class DealwithFile {
    File saveSearch=new File(Environment.getExternalStorageDirectory(),"search.csv");
    public void WriteToFile(String string, ArrayList<String> list, File f,ArrayList<String> list1){
        int j;
        for( j=0;j<list.size();j++){
            if(string.equals(list.get(j))){
                break;
            }
        }
        try {
            if(j==list.size()) {
                f.delete();
                saveSearch=new File(Environment.getExternalStorageDirectory(),"search.csv");
                list1.add(string);
                for(int i=list1.size()-1;i>=0;i--) {
                    FileWriter writer = new FileWriter(f, true);
                    writer.write(list1.get(i));
                    writer.write("\r\n");
                    writer.flush();
                    writer.close();
                }
            }
            else{
               String temp=list.get(j);
                String temp1=list.get(0);
                list1.set(list.size()-1,temp);
                list1.set(list.size()-j-1,temp1);
                f.delete();
                saveSearch=new File(Environment.getExternalStorageDirectory(),"search.csv");
                for(int i=list1.size()-1;i>=0;i--) {
                    FileWriter writer = new FileWriter(f, true);
                    writer.write(list1.get(i));
                    writer.write("\r\n");
                    writer.flush();
                    writer.close();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadFromFile(ArrayList<String> list,File f){
        BufferedReader reader=null;
        String result=null;
        try {
            reader=new BufferedReader(new FileReader(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((result=reader.readLine())!=null){
                list.add(result);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    }


