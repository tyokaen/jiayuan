package com.example.jiayuan.bijin.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jiayuan on 2017/10/28.
 */

public class StringToJson {
    static JSONObject jsonObject = null;

    public static JSONObject ToJSon(String JsonString) {
        try {
            jsonObject = new JSONObject(JsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getJSonArray( JSONArray array ,String target, String JSONKey) {
        JSONArray jsonArray=new JSONArray();
        try {
            jsonArray = ToJSon(target).getJSONArray(JSONKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static void getImageToken(ArrayList<String> arrayList, JSONArray jsonArray, String index) {
        String ImageToken = "sdsdsd";
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                ImageToken = jsonArray.getJSONObject(i).getString(index);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(ImageToken);
        }
    }

    public static String JsonToString(String target, String JSONKey) {
        String result = null;
        try {
            return ToJSon(target).getString(JSONKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
    public static void getToken(String arrayStr, ArrayList<String> bijinToken, String ArrayKey, String ObjectKey) {
        JSONArray jsonArray=null;
        jsonArray=getJSonArray(arrayStr,ArrayKey);
        getImageToken(bijinToken, jsonArray, ObjectKey);

    }
    */
}
