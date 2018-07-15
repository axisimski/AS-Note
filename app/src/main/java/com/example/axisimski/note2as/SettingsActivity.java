package com.example.axisimski.note2as;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sp=getSharedPreferences("Settings", Context.MODE_PRIVATE);
    private Button save_btn;
    private RadioButton single_rb, multi_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        save_btn=findViewById(R.id.btn_save);
        single_rb=findViewById(R.id.single_radio_btn);
        multi_rb=findViewById(R.id.multi_radio_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }



    private void saveSettings(){
        if(single_rb.isChecked()){

        }

        else if(multi_rb.isChecked()){

        }

        else{

        }

    }
}
