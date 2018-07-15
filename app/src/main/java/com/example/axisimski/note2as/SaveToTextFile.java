package com.example.axisimski.note2as;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveToTextFile {

    public void save(String filename, String content, Context context){

        filename = filename+".txt";

        File Directory = new File(Environment.getExternalStorageDirectory().
                getAbsolutePath()+"/ASNotes/");

        Directory.mkdirs();

        File file = new File(Directory
                , filename);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(context, context.getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            Toast.makeText(context, "Error~!", Toast.LENGTH_SHORT).show();
        }
    }
}
