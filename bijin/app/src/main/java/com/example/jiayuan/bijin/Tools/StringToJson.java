package com.example.jiayuan.bijin.Tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jiayuan on 2017/10/28.
 */

public class StringToJson {
    static JSONObject jsonObject=null;

    public static JSONObject ToJSon(String JsonString) {
        try {
           jsonObject = new JSONObject(JsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
