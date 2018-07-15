package com.example.axisimski.note2as;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.BaseAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class LoadList {

    @SuppressWarnings("unchecked")
    public ArrayList<String> loadList(Context context){

        ArrayList<String> list= new ArrayList<>();
        SharedPreferences sharedPreferences=context.getApplicationContext().
                getSharedPreferences("LIST", Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String jsonNotes=sharedPreferences.getString("jsonNotes","");


        if(!jsonNotes.equals("")) {
            List tempName = gson.fromJson(jsonNotes, new TypeToken<List<String>>() {
            }.getType());
            list.clear();
            list.addAll(tempName);
        }

        return list;
    }
}
