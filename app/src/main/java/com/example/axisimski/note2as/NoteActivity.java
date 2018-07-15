package com.example.axisimski.note2as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    private Button save_btn;
    private EditText input_edt;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();
    ArrayList<String> listOfNotes=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        save_btn=findViewById(R.id.btn_save);
        input_edt=findViewById(R.id.edt_input);

        listOfNotes=loadList.loadList(getApplicationContext());
        int pos=getIntent().getIntExtra("Position", 0);
        input_edt.setText(listOfNotes.get(pos));

    }//end onCreate();


}//end class{}
