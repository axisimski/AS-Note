package com.example.axisimski.note2as;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class CheckPermissions {

    public void checkPermissions(Context context, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, Context context){

        switch (requestCode){
            case 1000:

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(context, "Granted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context,"Permission not granted", Toast.LENGTH_SHORT).show();
        }

    }
}
