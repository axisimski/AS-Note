package com.example.axisimski.note2as;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView noteLV;
    private ArrayList<String> listOfNotes=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();
    private CheckPermissions checkPermissions=new CheckPermissions();
    private SaveToTextFile saveToTextFile=new SaveToTextFile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions.checkPermissions(getApplicationContext(), MainActivity.this);

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

                String allNotes="";
                for(int i=0;i<listOfNotes.size();i++){
                    allNotes=allNotes+"\n"+listOfNotes.get(i);
                }
                saveToTextFile.save("testtest", allNotes, getApplicationContext());

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
    }

}
