package com.example.axisimski.note2as;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int deletePos=-999;
    private ListView noteLV;
    private ArrayList<String> listOfNotes=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();
    private CheckPermissions checkPermissions=new CheckPermissions();
    private SaveToTextFile saveToTextFile=new SaveToTextFile();
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions.checkPermissions(getApplicationContext(), MainActivity.this);

        sp=getSharedPreferences("Settings", Context.MODE_PRIVATE);
        listOfNotes=loadList.loadList(getApplicationContext());
        noteLV=findViewById(R.id.note_lv);
        adapter=new ArrayAdapter<>(this,
                R.layout.single_line_lv, listOfNotes);
        noteLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        userInput();

    }//end onCreate()


    public void onRestart(){
        listOfNotes=loadList.loadList(getApplicationContext());
        saveList.saveList(getApplicationContext(), listOfNotes);
        adapter=new ArrayAdapter<>(this,
                R.layout.single_line_lv, listOfNotes);
        noteLV.setAdapter(adapter);
        super.onRestart();
    }
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem addNote=menu.findItem(R.id.item_add);
        addNote.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
                return false;
            }
        });

        MenuItem saveToText=menu.findItem(R.id.item_save);
        saveToText.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //Case multiple files
                if(!sp.getBoolean("singleFile", false)){
                    for(int i=0;i<listOfNotes.size();i++){
                        String filetitle="";
                        filetitle=listOfNotes.get(i);

                        if(filetitle.length()<=8){
                            filetitle=filetitle.substring(0,filetitle.length()-1);
                            filetitle="_"+filetitle;
                        }
                        if(filetitle.length()>8){
                            filetitle=filetitle.substring(0,7);
                            filetitle="_"+filetitle;
                        }
                        saveToTextFile.save("ASNotes"+Integer.toString(i)+filetitle, listOfNotes.get(i), getApplicationContext());
                    }
                }
                //Case single file
                else{
                    String allNotes="";
                    for(int i=0;i<listOfNotes.size();i++){
                        allNotes=allNotes+"\n"+listOfNotes.get(i);
                    }
                    saveToTextFile.save("ASNotes", allNotes, getApplicationContext());
                }

                return false;
            }
        });

        MenuItem settingsItem=menu.findItem(R.id.item_settings);
        settingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return false;
            }
        });

        MenuItem deleteItem=menu.findItem(R.id.item_delete);
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(deletePos!=-999){
                    listOfNotes.remove(deletePos);
                    saveList.saveList(getApplicationContext(), listOfNotes);
                    adapter.notifyDataSetChanged();
                    deletePos=-999;
                }
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
    private void userInput(){
        noteLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, NoteActivity.class);
               intent.putExtra("Position", position);
               startActivity(intent);
             }
        });


        noteLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                deletePos=position;
                return false;
            }
        });

    }

}
