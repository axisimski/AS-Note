package com.example.axisimski.note2as;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private RadioButton single_rb, multi_rb;
    private CheckBox date_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button save_btn = findViewById(R.id.btn_save);
        single_rb=findViewById(R.id.single_radio_btn);
        multi_rb=findViewById(R.id.multi_radio_btn);
        date_cb=findViewById(R.id.add_date_cb);
        sp=getSharedPreferences("Settings", Context.MODE_PRIVATE);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
        loadSettings();
    }

    private void saveSettings(){
        if(date_cb.isChecked()){
            sp.edit().putBoolean("addDate", true).apply();
        }

        if(!date_cb.isChecked()){
            sp.edit().putBoolean("addDate", false).apply();
        }

        if(single_rb.isChecked()){
            sp.edit().putBoolean("singleFile",true).apply();
        }

        else if(multi_rb.isChecked()){
            sp.edit().putBoolean("singleFile",false).apply();
        }

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();

    }//end saveSettings();

    private void loadSettings(){

        if(!sp.getBoolean("addDate",false)){
            date_cb.setChecked(false);
        }
        if(sp.getBoolean("addDate",false)){
            date_cb.setChecked(true);
        }
        if(sp.getBoolean("singleFile",false)){
            single_rb.setChecked(true);
        }
        if(!sp.getBoolean("singleFile",false)){
            multi_rb.setChecked(true);
        }



    }

}//end class{}
