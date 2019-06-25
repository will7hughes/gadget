package com.whughes.gadget.util;

import android.content.Context;

import net.minidev.json.JSONValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class JSONConverter {
    private int rawID;

    public JSONConverter(int rawID) {
        this.rawID = rawID;
    }

    public JSONObject getJsonObject(Context context) {
        //Create input stream
        InputStream inputStreamObject = context.getResources().openRawResource(rawID);

        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            //returns the json object
            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //if something went wrong, return null
        return null;
    }

    public String getEscapedString(String jsonString) {
        return JSONValue.escape(jsonString);
    }

    public String getString(Context context) {
        InputStream is = context.getResources().openRawResource(rawID);

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();
        return jsonString;
    }
}
