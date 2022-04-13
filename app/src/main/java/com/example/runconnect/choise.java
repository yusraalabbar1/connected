package com.example.runconnect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class choise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);
        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),register.class);

                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),registernonactive.class);

                startActivity(intent);
            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}