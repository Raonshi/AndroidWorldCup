package com.raon.androidworldcup.Json;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class VoteJson {
    private String title;
    private String category;
    private String description;
    private int tounament;


    public void SetJson(String fileName, String _title, String _category, String _description, int _tounament){
        title = _title;
        category = _category;
        description = _description;
        tounament = _tounament;

        ConvertToJson(fileName);
    }

    void ConvertToJson(String fileName){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("title", title);
            jsonObject.put("category", category);
            jsonObject.put("description", description);
            jsonObject.put("tounament", tounament);
        }
        catch(org.json.JSONException jsonException){
            jsonException.getStackTrace();
        }

        try{
            FileWriter file = new FileWriter("/data/data/com.raon.androidworldcup/" + fileName);
            file.write(jsonObject.toString());
            System.out.println("----Json File Save Success");
        }
        catch(IOException ioException){
            ioException.getStackTrace();
        }

    }

}
