package com.example.axisimski.note2as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    private Button save_btn;
    private EditText input_edt;
    private SaveList saveList=new SaveList();
    private LoadList loadList=new LoadList();
    private ArrayList<String> listOfNotes=new ArrayList<>();
    private int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        save_btn=findViewById(R.id.btn_save);
        input_edt=findViewById(R.id.edt_input);

        listOfNotes=loadList.loadList(getApplicationContext());
        pos=getIntent().getIntExtra("Position", -1);

        if(pos!=-1) {
            input_edt.setText(listOfNotes.get(pos));
            userInput(pos);
        }

        if(pos==-1){
            userInput(-1);
        }


    }//end onCreate();

    private void saveNote(int pos){

        if(pos==-1) {
            listOfNotes.add(input_edt.getText().toString());
            saveList.saveList(getApplicationContext(), listOfNotes);
            pos = listOfNotes.size();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
        }else{
            listOfNotes.set(pos, input_edt.getText().toString());
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
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
        saveList.saveList(getApplicationContext(), listOfNotes);
        super.onBackPressed();
    }


}//end class{}
