package com.example.axisimski.note2as;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class SaveList {

    public void saveList(Context context, List list){

        SharedPreferences sharedPreferences=context.getApplicationContext().
                getSharedPreferences("LIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String jsonNotes =gson.toJson(list);
        editor.putString("jsonNotes", jsonNotes);
        editor.apply();
    }

}
