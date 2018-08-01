package com.example.axisimski.note2as;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private Button save_btn;
    private EditText input_edt;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();
    private ArrayList<String> listOfNotes=new ArrayList<>();
    private SaveToTextFile saveToTextFile=new SaveToTextFile();
    SharedPreferences sp;
    private int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        save_btn=findViewById(R.id.btn_save);
        input_edt=findViewById(R.id.edt_input);
        sp=getSharedPreferences("Settings", Context.MODE_PRIVATE);

        listOfNotes=loadList.loadList(getApplicationContext());
        pos=getIntent().getIntExtra("Position", -1);

        if(pos!=-1) {
            input_edt.setText(listOfNotes.get(pos));
        }


    }//end onCreate();

    private void saveNote(int pos){

        @SuppressLint
                ("SimpleDateFormat") SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateStr=" "+formatter.format(date)+" ";

        if(!sp.getBoolean("addDate",false)){
            dateStr="";
        }

        if(pos==-1) {
            listOfNotes.add(input_edt.getText().toString()+dateStr);
            saveList.saveList(getApplicationContext(), listOfNotes);
            pos = listOfNotes.size();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
        }else{
            listOfNotes.set(pos, input_edt.getText().toString()+dateStr);
            saveList.saveList(getApplicationContext(), listOfNotes);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
        }
    }//end saveNote();

    private void userInput(final int pos){

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(pos);
            }
        });
    }//end userInput()

    public void onBackPressed(){
        saveNote(pos);
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
        saveList.saveList(getApplicationContext(), listOfNotes);
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);

        MenuItem save=menu.findItem(R.id.item_save);
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onBackPressed();
                return false;
            }
        });

        MenuItem save_to_text=menu.findItem(R.id.item_save_to_file);
        save_to_text.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String filetitle="";
                filetitle=listOfNotes.get(pos);

                if(filetitle.length()<=8){
                    filetitle=filetitle.substring(0,filetitle.length()-1);
                    filetitle="_"+filetitle;
                }
                if(filetitle.length()>8){
                    filetitle=filetitle.substring(0,7);
                    filetitle="_"+filetitle;
                }

                saveToTextFile.save(filetitle, listOfNotes.get(pos),getApplicationContext());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}//end class{}
