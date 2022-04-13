package com.example.runconnect;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class registernonactive extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registernonactive);
        TextView myAwesomeTextView = (TextView)findViewById(R.id.registerIdnonactive);

        String stringOutput = "";
        Uri uriCallLogs = Uri.parse("content://call_log/calls");
        Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
        cursorCallLogs.moveToFirst();


        SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("h:mm a");
        // SimpleDateFormat sdf_dur = new SimpleDateFormat("KK:mm:ss");
        if(cursorCallLogs.getCount()>0){
        do {
            @SuppressLint("Range") String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
            @SuppressLint("Range") String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
            @SuppressLint("Range") int stringDuration = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
            @SuppressLint("Range") int stringType = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
            /************************************/
            @SuppressLint("Range") String stringDate = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));
            String dateString = sdf_date.format(new Date(Long.parseLong(stringDate)));
            String timeString = sdf_time.format(new Date(Long.parseLong(stringDate)));

            if(stringDuration==0 && stringType==2){
                stringOutput=stringOutput+" Number: " + stringNumber
                        + "\n Name: " + stringName
                        + "\n Date: " + dateString
                        + "\n Time: " + timeString
                        + "\n"+ " non active xx"+"\n\n\n";
            }


        }while (cursorCallLogs.moveToNext());
        System.out.println(stringOutput);
        myAwesomeTextView.setText(stringOutput);}
        else{
            myAwesomeTextView.setText("not found numbers");
        }
    }


}