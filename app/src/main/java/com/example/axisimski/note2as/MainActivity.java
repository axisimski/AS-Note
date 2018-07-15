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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView noteLV;
    private ArrayList<String> listOfNotes=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private EditText input;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteLV=findViewById(R.id.note_lv);
        input=findViewById(R.id.editText);

       /* SimpleDateFormat formatter = new SimpleDateFormat("dd/MM", Locale.getDefault());
        Date date = new Date();*/

       // listOfNotes.add(formatter.format(date)+"Hello");
        listOfNotes.add("World");

        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listOfNotes);

        noteLV.setAdapter(adapter);

        noteLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });

        saveList.saveList(getApplicationContext(), listOfNotes);
        listOfNotes=loadList.loadList(getApplicationContext());

    }//end onCreate()



    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem addNote=menu.findItem(R.id.item_add);
        addNote.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                listOfNotes.add(input.getText().toString());
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);

                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }


}
